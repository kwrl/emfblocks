package no.hal.emfblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.WeakHashMap;

import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.hierarchy.TypeHierarchy;
import no.hal.emfblocks.javafx.FXPointerArrow;
import no.hal.emfblocks.javafx.ActionSource;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.javafx.fxml.WPaneUI;
import no.hal.emfblocks.slots.DropTargetHighlighter;
import no.hal.emfblocks.slots.Slot;
import no.hal.emfblocks.slots.DropTargetHighlighter.Highlight;
import no.hal.emfblocks.slots.DropTargetHighlighter.InvalidDropTargetException;
import no.hal.emfblocks.util.data.pfield.PField;
import no.hal.emfblocks.util.data.pfield.PFieldList;
import no.hal.emfblocks.util.data.pfield.PFieldSingle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;

public class WRoot implements WNode, WSingleFieldContainer<WNode>, EventHandler<Event>
{
	public static double DRAG_THRESHOLD = 8;

	protected interface ReturnTarget
	{
		public void returnBlock(WBlock b);
	}
	
	protected final TypeHierarchy typeHierarchy;
	protected PFieldList<WNode> children = new PFieldList<WNode>(this, WNode.class);
	
	protected Pane rootPane;
	protected Parent contentPane;
	protected HashMap<WPointerBlock, FXPointerArrow> connectorArrows = new HashMap<>();
	
	protected boolean pressToDrop;
	protected DragStartMode plannedMode;
	protected ReturnTarget dragCancel;
	public final PFieldSingle<WBlock> dragging = new PFieldSingle<WBlock>(this, WBlock.class);
	protected Scale dragScaler;
	private WPane dragPane = null;
	protected Point2D scrollPosTmp = null;
	protected Point2D dragOffset = null;
	protected Point2D dragStart = null;
	protected WBlock clickedBlock = null;
	
	protected DropTargetHighlighter[] highlights = null;
	protected DropTargetHighlighter.Highlight currentHighlight = null;
	/**DnD*/
	protected Object dragKey;
	
	public final ActionSource modificationListeners = new ActionSource();
	
	protected final TransactionManager transactionMan;
	protected final WeakHashMap<Object, Boolean> doNotConsumeMouseEvents = new WeakHashMap<Object, Boolean>();

	public WRoot(TypeHierarchy typeHierarchy)
	{
		this(typeHierarchy, null);
	}
	public WRoot(TypeHierarchy typeHierarchy, TransactionManager transactionMan)
	{
		this.typeHierarchy = typeHierarchy;
		this.transactionMan = transactionMan;
	}
	/**Called whenever a new {@link WPane} is created.*/
	void childPaneCreated(WPane p)
	{
		assert !children.contains(p) : "Duplicate add: "+p;
		children.add(p);
	}
	/**Called when a previously added {@link WPane}'s UI is created*/
	void childPaneUICreated(WPane p)
	{
		Node ui = p.getUI();
		//Note: listeners on root pane is insufficient because
		//there is a discontinuity in the hierarchy at tabpanes.
		//This is terrible design, but we just have to deal.
		registerListeners(ui, false);
	}
	/**
	 * Register the root as the listener for mouse pressed, released and dragged events on the given node. This allows blocks under the node to be selected, dragged and dropped.
	 * @param ui The node on which to listen for mouse events.
	 * @param doNotConsumeMouseEvents If true, the node will be allowed to steal focus. If false, the block under the node will always be focused when this node is touched.
	 */
	public void registerListeners(Node ui, boolean doNotConsumeMouseEvents)
	{
		ui.addEventHandler(MouseEvent.ANY, this);
		/*ui.onMousePressedProperty().set(this);
		ui.onMouseDraggedProperty().set(this);
		ui.onMouseReleasedProperty().set(this);
		ui.onDragDetectedProperty().set(this);*/
		
		ui.onZoomProperty().set(this);
		ui.onScrollProperty().set(this);
		
		if(doNotConsumeMouseEvents)
			this.doNotConsumeMouseEvents.put(ui, Boolean.TRUE);
	}
	public void installContentPane(Parent p)
	{
		this.contentPane = p;
		fxmlSearch(p);
	}
	/**Search p's subtree for instances of {@link WPaneUI}. If they are found, their {@link WPane}s are spawned and added to this root.*/
	private void fxmlSearch(Node p)
	{
		if(p instanceof WPaneUI)
			((WPaneUI) p).getModel(this);
		else if(p instanceof Parent)//Note: WPanes in WPanes not supported..
		{
			for(Node c : ((Parent) p).getChildrenUnmodifiable())
			{
				fxmlSearch(c);
			}
			
			((Parent) p).getChildrenUnmodifiable().addListener(new ListChangeListener<Node>()
			{
				@Override
				public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Node> c)
				{
					while(c.next())
					{
						if(c.wasAdded())
						{
							for(Node n : c.getAddedSubList())
								fxmlSearch(n);
						}
					}
				}	
			});
		}
	}

	protected void createRootPane()
	{
		if(contentPane == null)
			throw new IllegalStateException("WRoot cannot automatically create content pane: Add one using installContentPane() first!");
		rootPane = new Pane(){@Override
			protected void layoutChildren()
		{
			//Only contentPane has a fixed location on this pane
			for(Node c : getChildren())
			{
				if(c == contentPane)
					contentPane.resizeRelocate(0, 0, getWidth(), getHeight());
				else
					c.autosize();
			}
			for(FXPointerArrow a : connectorArrows.values())
				a.updateIfDirty();
		}};
		rootPane.getChildren().add(contentPane);
		
		//Note: Mouse listeners are added to both rootPane and child panes.
		//This should be redundant. However:
		//1. There is a discontinuity at tabpanes which prevents events
		//   from being delivered through it.
		//2. The child panes sometimes become insensitive to user input if
		//   their layout changes as a result of a user action. 
		registerListeners(rootPane, false);
		
		//Delayed add of pre-existing arrows
		for(FXPointerArrow a : connectorArrows.values())
			rootPane.getChildren().addAll(a.getNodes());
	}
	public Parent getContentPane()
	{
		return contentPane;
	}
	@Override
	public Pane getInternalUI() {
		return getUI();
	}
	@Override
	public Pane getUI()
	{
		if(rootPane == null)
			createRootPane();
		return rootPane;
	}
	
	public void click(WBlock b, double xOffset, double yOffset, double absX, double absY, DragStartMode dsm)
	{
		if(clickedBlock == null)
		{
			clickedBlock = b;
			plannedMode = dsm;
			dragOffset = new Point2D(xOffset, yOffset);
			dragStart = new Point2D(absX, absY);
			b.getUI().requestFocus();
		}
	}

	private void stickyDragStart(WBlock srcBlock, DragStartMode dsm,
		double absX, double absY)
	{
		//not needed for sticky operations:
		//scrollPosTmp, dragStart, dragPane
		clickedBlock = srcBlock;
		plannedMode = dsm;
		dragOffset = new Point2D(0, 0);
		srcBlock.getUI().requestFocus();
		
		pressToDrop = true;
		dragStart(srcBlock, absX, absY);
	}
	
	private void dragStart(WBlock b, double absX, double absY)
	{
		WBlock origB = b;
		final WSingleFieldContainer<? super WBlock> retContainer = b.blockContainer().get();
		assert (dragging.isEmpty());
		try 
		{
			if(plannedMode == DragStartMode.CREATE_POINTER && b.isPaletteBlock())
				throw new IllegalArgumentException(origB+" is a palette block. Palette blocks cannot have line connections.");
			
			if(plannedMode == DragStartMode.CREATE_POINTER)
			{
				b = b.makePointerBlock();
				if(b == null)
					throw new IllegalArgumentException(origB+" cannot have line connections...");
				if(transactionMan != null)
					transactionMan.prepareTransaction(this);
				dragCancel = (blok -> {blok.dispose();});
			}
			else if(b.isPaletteBlock() || plannedMode == DragStartMode.COPY_BLOCK)
			{
				b = b.copy();
				assert b != null : "copy() cannot return null!";
				if(transactionMan != null)
					transactionMan.prepareTransaction(this);
				dragCancel = (blok -> {blok.dispose();});
			}
			else //MOVE_BLOCK
			{
				final int retIndex = retContainer.getChildren().indexOf(b);
				final int retX = b.getX();
				final int retY = b.getY();
				if(transactionMan != null)
					transactionMan.prepareTransaction(this);
				dragCancel = (blok -> 
				{
					try 
					{
						retContainer.getChildren().insert(retIndex, blok);
						if(!(retContainer instanceof Slot))
							blok.setLocation(retX, retY);
					}
					catch(IllegalArgumentException e)
					{
						System.err.println("Warning: Block "+blok+" could not be returned to its original location!");
						//The block could not be returned. This is likely an error in the application, but we must recover.
						e.printStackTrace();
						blok.dispose();
					}
				});
				retContainer.getChildren().remove(b);
			}
			//Need to add before getting UI(why?)
			dragging.set(b);
			assert b.blockContainer().get() == this : "Dropped block has the wrong parent: "+b.blockContainer().get();
			Pane ui = b.getUI();
			double s = (retContainer instanceof WTransformablePane) ? ((WTransformablePane) retContainer).getScale() : 1.0;
			dragScaler = new Scale(s, s);
			ui.getTransforms().add(dragScaler);
			//The z-ordering scheme is fragile. 
			//The block ui should be in front of content pane and highlights(added below), but behind arrows. 
			rootPane.getChildren().add(1, ui);
			ui.requestFocus();
			b.setLocation((int)(absX-dragOffset.getX()*s), (int) (absY-dragOffset.getY()*s));
			
			Slot[] slots = getTargetSlots();
			ArrayList<DropTargetHighlighter> highlightsList = new ArrayList<>();
			for(int i = 0; i<slots.length; i++)
			{
				try
				{
					//TODO this patterns is dumb and the check should be static.
					highlightsList.add(new DropTargetHighlighter(this, slots[i], b));
				}
				catch(InvalidDropTargetException e)
				{
					//Don't care
				}
			}
			highlights = highlightsList.toArray(new DropTargetHighlighter[highlightsList.size()]);
			
			selectHighlight(absX-dragOffset.getX()*s, absY-dragOffset.getY()*s, s);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("Can't do "+plannedMode+" because "+e.getMessage());
			e.printStackTrace();
			clearDragState();
			if(transactionMan != null && transactionMan.isTransactionInProgress())
				transactionMan.cancelTransaction(this, false);
		}
	}
	private void clearDragState()
	{
		if(!dragging.isEmpty())
			dragging.remove();
		dragScaler = null;
		dragPane = null;
		dragOffset = null;
		dragCancel = null;
		highlights = null;
		plannedMode = null;
		clickedBlock = null;
		currentHighlight = null;
		pressToDrop = false;
	}

	private Slot[] getTargetSlots()
	{
		Queue<Slot> sq = new LinkedList<>();
		putTargetSlots(sq);
		return sq.toArray(new Slot[sq.size()]);
	}
	@Override
	public void putTargetSlots(Queue<Slot> sq)
	{
		for(WNode c : children)
		{
			if(FXUtil.isVisible(c.getUI()))
				c.putTargetSlots(sq);
		}
	}
	
	public void drag(MouseEvent event, double absX, double absY)
	{			
		if(dragging.isEmpty())//Drag not yet started
		{
			if(clickedBlock != null)
			{
				double dist = Math.sqrt(Math.pow(absX-dragStart.getX(), 2)+Math.pow(absY-dragStart.getY(), 2));
				if(dist > DRAG_THRESHOLD)
				{
					pressToDrop = false;
					dragStart(clickedBlock, absX, absY);
				}
			}
			else if(event.getButton() == MouseButton.MIDDLE || event.getButton() == MouseButton.PRIMARY && dragPane != null && dragPane instanceof WTransformablePane)
			{
				WTransformablePane tPane = ((WTransformablePane)dragPane);
				tPane.getUI().setCursor(Cursor.MOVE);
				tPane.translate(absX-scrollPosTmp.getX(), absY-scrollPosTmp.getY());
				scrollPosTmp = new Point2D(absX, absY);
			}
		}
		else
		{
			WPane hoverPane = getPaneAt(absX, absY);
			double s = (hoverPane instanceof WTransformablePane) ? ((WTransformablePane) hoverPane).getScale() : 1.0; 
			dragScaler.setX(s);
			dragScaler.setY(s);
			int bx = (int)(absX-dragOffset.getX()*s);
			int by = (int)(absY-dragOffset.getY()*s);
			dragging.get().setLocation(bx, by);
			selectHighlight(bx, by, s);
		}
	}
	private void selectHighlight(double x, double y, double scaleFactor)
	{
		final double SNAP_THRESHOLD = 40*scaleFactor;
		double hTabX, hTabY, vTabX, vTabY;
		Highlight choice;
		Type tabType = dragging.get().tabType().get();
		if(tabType == null)
		{
			choice = null;// Cannot snap 
		}
		else
		{
			hTabX = x-scaleFactor*(typeHierarchy.connectorWidth.get());
			//x,y is the block location, so we must add the tab offsets
			hTabY = y+scaleFactor*(FXBlock.TAB_OFFSET);
			vTabX = x+scaleFactor*(FXBlock.TAB_OFFSET);//Compensate for rotation...
			vTabY = y-scaleFactor*(typeHierarchy.connectorWidth.get());
		
			Highlight closest = null;
			double minDist = Double.MAX_VALUE;
			for(DropTargetHighlighter highlighter : highlights)
			{
				double tx, ty;
				if(highlighter.isHorizontal())
				{
					tx = hTabX;
					ty = hTabY;
				}
				else
				{
					tx = vTabX;
					ty = vTabY;
				}
				Highlight h = highlighter.getClosestHighlight(tx, ty);
				//Manhattan distance
				double dist = Math.abs(h.getX()-tx)+Math.abs(h.getY()-ty);
				if(dist < minDist)
				{
					minDist = dist;
					closest = h;
				}
			}
			if(minDist < SNAP_THRESHOLD)
				choice = closest;
			else
				choice = null;
		}
		if(choice != currentHighlight)
		{
			if(currentHighlight != null)
				currentHighlight.setHover(false);
			currentHighlight = choice;
			if(currentHighlight != null)
				currentHighlight.setHover(true);
		}
	}
	
	public void drop(MouseEvent event, double absX, double absY)
	{
		if(dragPane != null && dragPane instanceof WTransformablePane)
		{
			dragPane.getUI().setCursor(Cursor.DEFAULT);
		}
		
		if(!dragging.isEmpty())
		{
			//Remember the value BEFORE removing it
			WBlock dragBlock = dragging.get();
			FXBlock blkUI = dragBlock.getUI();
			boolean hadTransform = blkUI.getTransforms().remove(dragScaler);
			assert hadTransform;
			//Remove the UI from the root pane
			rootPane.getChildren().remove(blkUI);
			//Force repaint because JavaFX is an ass
			FXUtil.forceRepaint(rootPane);
			
			//Remove needs to happen before being added to the appropriate pane
			dragging.remove();
			
			//Find out where to put the block
			WSingleFieldContainer<WBlock> target;
			if(currentHighlight != null)
				target = currentHighlight.getSlot();
			else
				target = getPaneAt(absX, absY);
			
			BlockDropAction action = (target == null?BlockDropAction.CANCEL:target.accepts(dragBlock));
			if(action == BlockDropAction.CANCEL)
			{
				dragCancel.returnBlock(dragBlock);
				if(transactionMan != null)
					transactionMan.cancelTransaction(this, false);
			}
			else if(action == BlockDropAction.ACCEPT)
			{
				try {
					if(target instanceof Slot)
					{
						if(((Slot) target).isMultiSlot())
						{
							target.getChildren().insert(currentHighlight.getIndex(), dragBlock);
						}
						else
						{
							WBlock oldBlock = target.getChildren().get();
							if(oldBlock != null)
							{
								oldBlock.eject();
							}
							target.getChildren().set(dragBlock);
						}
					}
					else
					{
						double s = (target instanceof WTransformablePane) ? ((WTransformablePane) target).getScale() : 1.0;
						target.getChildren().add(dragBlock);
						dragBlock.getUI().requestFocus();
						Point2D v = FXUtil.fromTo(rootPane, target.getInternalUI(), new Point2D(absX-dragOffset.getX()*s, absY-dragOffset.getY()*s));
						dragBlock.setLocation((int)v.getX(), (int)v.getY());
					}
					assert dragBlock.blockContainer().get() == target : "Dropped block has the wrong parent: "+dragBlock.blockContainer().get();
					if(transactionMan != null)
						transactionMan.completeTransaction(this, "Dragged block"+dragBlock);
				}
				catch(IllegalArgumentException e)
				{
					System.err.println("Could not drop "+dragBlock+" on "+target+" because "+e.getMessage());
					dragCancel.returnBlock(dragBlock);
					if(transactionMan != null)
						transactionMan.cancelTransaction(this, false);
				}
			}
			else
			{
				dragBlock.dispose();
				if(transactionMan != null)
					transactionMan.completeTransaction(this, "Delete block: "+dragBlock);
			}
			//Null out dragOffset after everything else because it is used by other calculations
		}
		if(highlights != null)
		{
			for(DropTargetHighlighter h : highlights)
			{
				h.destroy();
			}
		}
		clearDragState();
	}
	private WPane getPaneAt(double absX, double absY)
	{
		//Need to return the panes in the correct z-order, which is not obvious.
		//Start by mapping candidate WPanes by their UIs
		HashMap<Node, WPane> wpanesByUI = new HashMap<>();
		for(WNode p : children)
		{
			if(p instanceof WPane)
				wpanesByUI.put(p.getUI(), (WPane)p);
		}
		//Now, we need to select the candidate which is deepest in the scenegraph.
		//If multiple candidates are at the same depth, then they need to be selected by their position in the parent's child list.
//		Queue<Triplet<Node, Integer, Integer>> bfsQueue = new LinkedList<>(); 
//		bfsQueue.offer(new Triplet<>(rootPane, 0, 0));
//		while(!bfsQueue.isEmpty())
//		{
//			Triplet<Node, Integer, Integer> current = bfsQueue.poll();
//			if (FXUtil.isVisible(current.a) && FXUtil.fromTo(current.a, rootPane, current.a.getBoundsInLocal()).contains(absX, absY))
//			{
//				Triplet<WPane, Integer, Integer> mapValue = wpanesByUI.get(current.a); 
//				if(mapValue != null)
//				{
//					mapValue.b = current.b;
//					mapValue.c = current.c;
//				}
//				else
//				{
//					
//				}
//			}
//			//If it was not contained, then WPanes in this subtree will have depths left as -1.
//		}
		return wpanesByUI.get(findTopmost(rootPane, wpanesByUI, absX, absY));
		
	}
	/**Returns the topmost pane in the given subtree which is also a key in the given hashmap and contains the given position.*/
	private Node findTopmost(Node subtree, HashMap<Node, ?> keyset, double absX, double absY)
	{
		//Only subtrees that contain the given point need to be considered.
		if (FXUtil.isVisible(subtree) && FXUtil.fromTo(subtree, rootPane, subtree.getBoundsInLocal()).contains(absX, absY))
		{
			//If this subtree is one of the targets, return it.
			if(keyset.containsKey(subtree))
				return subtree;
			else if(subtree instanceof Parent) //Otherwise, traverse
			{
				//Iterate children in reverse order, considering the topmost one first.
				ObservableList<Node> list = ((Parent) subtree).getChildrenUnmodifiable();
				for(int i = list.size()-1; i >= 0; i--)
				{
					Node cand = list.get(i);
					Node p = findTopmost(cand, keyset, absX, absY);
					if(p != null)
						return p;
				}
			}
		}
		return null;
	}

	@Override
	public WContainer<WRoot> getParent()
	{
		return null;
	}
	@Override
	public PField<WNode> getChildren()
	{
		return children;
	}
	
	public TypeHierarchy getTypeHierarchy()
	{
		return typeHierarchy;
	}
	
	@Override
	public WRoot getRoot()
	{
		return this;
	}
	
	@Override
	public BlockDropAction accepts(WNode dragBlock)
	{
		return BlockDropAction.ACCEPT;
	}
	
	@Override
	public void handle(Event gevt)
	{
		if (gevt instanceof ZoomEvent) {
			ZoomEvent event = (ZoomEvent) gevt;
			Point2D scenePt = new Point2D(event.getSceneX(), event.getSceneY());
			Point2D rootPt = rootPane.sceneToLocal(scenePt);
			double x = rootPt.getX();
			double y = rootPt.getY();
			WPane p = getPaneAt(x, y);
			if (p != null && p instanceof WTransformablePane) {
				System.out.println("Zoom gesture("+event.getZoomFactor()+")");
				WTransformablePane tPane = ((WTransformablePane)p);
				tPane.scale(event.getZoomFactor());
				event.consume();
			}
		}
		else if (gevt instanceof ScrollEvent) {
			ScrollEvent event = (ScrollEvent) gevt;
			Point2D scenePt = new Point2D(event.getSceneX(), event.getSceneY());
			Point2D rootPt = rootPane.sceneToLocal(scenePt);
			double rootX = rootPt.getX();
			double rootY = rootPt.getY();
			WPane p = getPaneAt(rootX, rootY);
			if(p != null && p instanceof WTransformablePane) {				
				WTransformablePane tPane = ((WTransformablePane)p);
				if (event.getTouchCount() == 0) { // Mouse wheel => zoom
					tPane.scale(1+event.getDeltaY()/400);
				} else { //Multi touch => scrolling
					tPane.translate(event.getDeltaX(), event.getDeltaY());
				}
				event.consume();
			}
		} else if (gevt instanceof MouseEvent) {
			MouseEvent event = (MouseEvent) gevt;
			//Note: JavaFX has bugs that cause the real scene position to be different
			//from using the local-to-scene transform on event.getX()/getY().
			//Fortunately, we can get the real scene position directly, and it is not bugged.
			Point2D scenePt = new Point2D(event.getSceneX(), event.getSceneY());
			Point2D rootPt = rootPane.sceneToLocal(scenePt);
			double x = rootPt.getX();
			double y = rootPt.getY();
			
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				FXUtil.destroyCurrentContextMenu();
				if (pressToDrop) {
					drop(event, x, y);
				} else {
					scrollPosTmp = new Point2D(x, y);
					dragPane = getPaneAt(scrollPosTmp.getX(), scrollPosTmp.getY());
					WBlock blk = getBlockAt(dragPane, scrollPosTmp);
					if (blk == null) {
						defaultFocus();
					} else {
						Point2D p = blk.getUI().sceneToLocal(scenePt);
						
						DragStartMode mode = getDragMode(event);						
						click(blk, p.getX(), p.getY(), x, y, mode);
					}
				}
			} else if (!pressToDrop && event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				// Right button release on a block before dragging triggers the context menu.
				if (dragging.isEmpty() && clickedBlock != null && event.getButton() == MouseButton.SECONDARY) {
					final WBlock srcBlock = clickedBlock;
					ArrayList<MenuItem> items = new ArrayList<MenuItem>();
					
					Label label = new Label(srcBlock.getName());
					label.textFillProperty().set(Color.GRAY);
					CustomMenuItem labelItem = new CustomMenuItem(label, false);
					items.add(labelItem);
					
					items.add(new SeparatorMenuItem());
					
					MenuItem ptrItem = new MenuItem("Create pointer block (right button drag)");
					ptrItem.onActionProperty().set((e) -> { stickyDragStart(srcBlock, DragStartMode.CREATE_POINTER, x, y); });
					items.add(ptrItem);
					MenuItem copyItem = new MenuItem("Duplicate (Ctrl+left button drag)");
					copyItem.onActionProperty().set((e) -> { stickyDragStart(srcBlock, DragStartMode.COPY_BLOCK, x, y); });
					items.add(copyItem);
					
					MenuItem deleteItem = new MenuItem("Delete");
					deleteItem.onActionProperty().set((e) -> 
					{
						if(transactionMan != null)
							transactionMan.prepareTransaction(this);
						srcBlock.blockContainer().get().getChildren().remove(srcBlock); srcBlock.dispose();
						if(transactionMan != null)
							transactionMan.completeTransaction(this, "Deleted "+srcBlock);
					});
					items.add(deleteItem);
					
					ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[items.size()]));
					FXUtil.contextMenuCreated(menu);
					menu.show(srcBlock.getUI(), Side.TOP, 0, 0);
					EventHandler<ActionEvent> menuDestroyer = (a) -> { FXUtil.destroyCurrentContextMenu(); };
					srcBlock.getDestructionListeners().addListener(menuDestroyer);
					menu.setOnHidden((e) -> {srcBlock.getDestructionListeners().removeListener(menuDestroyer);});
				}
				//Always drop, to avoid stuff sticking to the mouse while the menu is open
				drop(event, x, y);
			}
			else if ((event.getEventType() == MouseEvent.MOUSE_DRAGGED || event.getEventType() == MouseEvent.MOUSE_MOVED) && plannedMode != DragStartMode.USE_TYPE) {
				drag(event, x, y);
			} else if (event.getEventType() == MouseEvent.DRAG_DETECTED && plannedMode == DragStartMode.USE_TYPE) {
				//Obtain type chip
				Type t = clickedBlock.tabType().get();
				if (t != null) {
					Dragboard db = clickedBlock.getUI().startDragAndDrop(TransferMode.MOVE);
					db.setDragViewOffsetX(typeHierarchy.connectorWidth.get()/2);
					db.setDragViewOffsetY(typeHierarchy.connectorHeight.get()/2);
			        db.setDragView(t.getRawType().getContour().tabImage().get());
			        /* Put a string on a dragboard */
			        ClipboardContent content = new ClipboardContent();
			        Object key = t.getRawType().getKey();
			        dragKey = key;
			        content.putString(key.toString());
			        db.setContent(content);
				}
				clearDragState();
			}

			if (doNotConsumeMouseEvents.get(event.getSource()) != Boolean.TRUE) {
				event.consume(); // This is necessary to prevent other FX objects from stealing focus but it may create other problems.
			}
		}
	}

	private DragStartMode.Classifier dragModeHelper = new DefaultDragModeHelper();

	protected DragStartMode getDragMode(MouseEvent event) {
		return dragModeHelper.getDragMode(event);
	}

	@Override
	public WBlock getBlockAt(Point2D localPoint)
	{
		WPane pane = getPaneAt(localPoint.getX(), localPoint.getY());
		if(pane == null)
			return null;
		return getBlockAt(pane, localPoint);
	}
	
	public WBlock getBlockAt(WPane pane, Point2D localPoint)
	{
		Node ui = pane.getInternalUI();
		Point2D panePoint = ui.sceneToLocal(rootPane.localToScene(localPoint.getX(), localPoint.getY()));
		return pane.getBlockAt(panePoint);
	}
	public void addPointerBlockArrow(WPointerBlock p)
	{
		if(connectorArrows.containsKey(p))
			new Throwable("Warning: "+p+" already added").printStackTrace();
		
		FXPointerArrow a = new FXPointerArrow(p);
		if(rootPane != null)
			rootPane.getChildren().addAll(a.getNodes());
		connectorArrows.put(p, a);
	}
	
	public void removePointerBlockArrow(WPointerBlock p)
	{
		FXPointerArrow a = connectorArrows.remove(p);
		if(a == null)
			new Throwable("Warning: "+p+" already removed or not added").printStackTrace();
		if(rootPane != null)
			rootPane.getChildren().removeAll(a.getNodes());
	}
	
	public Object getDragKey()
	{
		return dragKey;
	}
	
	public void defaultFocus() {
		rootPane.requestFocus();
	}
	/**Returns the current {@link TransactionManager}, or null if the root was initialized without one.*/
	public TransactionManager getTransactionManager()
	{
		return transactionMan;
	}
}
