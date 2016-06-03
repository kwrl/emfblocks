package no.hal.emfblocks.editor.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class FXSplittingTabPane extends SplitPane {
	private static Image LEFT_ARROW, RIGHT_ARROW, ADD_DROP_DOWN;

	/**
	 * The tab add handler is responsible for adding a new tab when the add
	 * button is clicked.
	 */
	public static interface TabAddHandler {
		/**
		 * Called when the user presses the add tab button. The implementation
		 * is responsible for creating the tab, and calling
		 * {@link FXSplittingTabPane#addNode(int, String, Node, boolean, boolean)}
		 */
		public void addTab(FXSplittingTabPane thePane, int location, ActionEvent buttonEvt);
	}

	/**
	 * The tab add handler can veto or respond to the delete event for a tab.
	 */
	public static interface TabDeleteHandler {
		/**
		 * Called when the tab is about to be closed. The implementation may
		 * consume the event to prevent the tab from being deleted.
		 */
		public void deleteTab(FXSplittingTabPane thePane, TabPane containerPane, Tab deletingTab, Event e);
	}

	/**
	 * The tab rearrangement callback is notified when the location of a tab
	 * changes due to a user action. Note that this may affect the positions of
	 * any or all other tabs.
	 */
	public static interface TabRearrangementCallback {
		/** Called when the tab has been moved. */
		public void tabMoved(FXSplittingTabPane thePane, TabPane targetTabPane, int tabIndex, Tab movedTab);
	}

	static {
		try (InputStream s = FXSplittingTabPane.class.getResourceAsStream("leftArrow.png")) {
			if (s == null)
				throw new FileNotFoundException();
			LEFT_ARROW = (new Image(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (InputStream s = FXSplittingTabPane.class.getResourceAsStream("rightArrow.png")) {
			if (s == null)
				throw new FileNotFoundException();
			RIGHT_ARROW = (new Image(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (InputStream s = FXSplittingTabPane.class.getResourceAsStream("addDropDown.png")) {
			if (s == null)
				throw new FileNotFoundException();
			ADD_DROP_DOWN = (new Image(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int UID_COUNTER = 0;

	private ObjectProperty<Tab> dragTab = new ObjectPropertyBase<Tab>(null) {
		@Override
		public Object getBean() {
			return FXSplittingTabPane.this;
		}

		@Override
		public String getName() {
			return "Drag Tab";
		}
	};
	private IntegerProperty dragDir = new SimpleIntegerProperty(0);

	private final TabAddHandler tabAddHandler;
	private final TabDeleteHandler tabDeleteHandler;
	private final TabRearrangementCallback tabRearrangementCallback;
	private long uid;
	private DataFormat dataFormat;
	private ArrayList<Pair<TabPane, StackPane>> children;
	private EventHandler<? super Event> focusDefaulter;

	/**
	 * 
	 * @param tabAddHandler
	 *            Defines what happens when the add button is pressed. If null,
	 *            there will be no add button.
	 * @param tabDeleteHandler
	 *            Can redefine what happens when the close button of a closeable
	 *            tab is pressed.
	 * @param tabRearrangementCallback
	 *            Can respond to tabs being moved by the user.
	 * @param focusDefaulter
	 *            If the focused component is removed from the scene, this
	 *            object can preemptively change the focus to something else,
	 *            e.g. a dummy node, to prevent JavaFX from handing the focus
	 *            over to a random node.
	 */
	public FXSplittingTabPane(TabAddHandler tabAddHandler, TabDeleteHandler tabDeleteHandler,
			TabRearrangementCallback tabRearrangementCallback, EventHandler<? super Event> focusDefaulter) {
		super();
		uid = (UID_COUNTER++);
		dataFormat = new DataFormat("dragTab" + uid);
		this.focusDefaulter = focusDefaulter;
		this.tabAddHandler = tabAddHandler;
		this.tabRearrangementCallback = tabRearrangementCallback;
		this.tabDeleteHandler = tabDeleteHandler;
		children = new ArrayList<>();
		getItems().addListener(new ListChangeListener<Object>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Object> ch) {
				while (ch.next()) {
					for (Object c : ch.getAddedSubList()) {
						StackPane sp = (StackPane) c;
						TabPane tp = (TabPane) sp.getChildrenUnmodifiable().get(0);
						children.add(new Pair<TabPane, StackPane>(tp, sp));
					}
					for (Object c : ch.getRemoved()) {
						Iterator<Pair<TabPane, StackPane>> it = children.iterator();
						boolean found = false;
						while (it.hasNext() && !found) {
							if (it.next().getValue() == c) {
								it.remove();
								found = true;
							}
						}
						if (!found)
							throw new IllegalStateException(c + " was not in the list of children...");
					}
				}
			}
		});
		createAndAddTabPane();
	}

	private TabPane createAndAddTabPane() {
		TabPane tp = new TabPane();
		StackPane sp = new StackPane();
		StackPane.setAlignment(tp, Pos.CENTER);
		sp.getChildren().add(tp);

		if (tabAddHandler != null) {
			tp.getStylesheets().add("put_me_out_of_my_misery");
			Button addButton = new Button();
			addButton.onActionProperty().set((a) -> {
				tabAddHandler.addTab(this, locationOf(tp), a);
			});
			ImageView im = new ImageView(ADD_DROP_DOWN);
			addButton.setGraphic(new Label("Add", im));
			addButton.setPrefWidth(64);
			addButton.setStyle("-fx-base: #b6e7c9;");

			StackPane.setAlignment(addButton, Pos.TOP_LEFT);
			sp.getChildren().add(addButton);
		}
		getItems().add(sp);
		return tp;
	}

	/**
	 * Create a new tab for some content node.
	 * 
	 * @param location
	 *            The index of the tab pane that the tab should be added to.
	 * @param insertionIndex
	 *            The index where the node's tab should be inserted into the tab
	 *            pane. If out of bounds, it will be added to the end.
	 * @param name
	 *            The name of the new tab.
	 * @param n
	 *            The content node.
	 * @param closeable
	 *            Whether to allow closing the new tab. To specify closing
	 *            behaviour, pass a {@link TabDeleteHandler} to the constructor
	 *            for {@link FXSplittingTabPane}.
	 * @param autoSelect
	 *            Whether to automatically select the new tab.
	 * @return The tab that was created.
	 */
	public Tab addNode(int location, int insertionIndex, String name, Node n, boolean closeable, boolean autoSelect) {
		while (location >= children.size())
			createAndAddTabPane();
		TabPane container = children.get(location).getKey();

		Tab newTab = createTab(name, n, closeable);
		if (insertionIndex < 0 || insertionIndex > container.getTabs().size())
			insertionIndex = container.getTabs().size();
		container.getTabs().add(insertionIndex, newTab);
		if (autoSelect)
			container.getSelectionModel().select(newTab);
		return newTab;
	}

	/**
	 * Remove the tab for the given content node. Returns the tab that was
	 * removed, or null if none.
	 */
	public Tab removeNode(Node n) {
		for (int i = 0; i < children.size(); i++) {
			Pair<TabPane, StackPane> tp = children.get(i);
			for (int j = 0; j < tp.getKey().getTabs().size(); j++) {
				Tab t = tp.getKey().getTabs().get(j);
				if (n == t.getContent()) {
					tp.getKey().getTabs().remove(j);
					if (tp.getKey().getTabs().size() == 0) {
						removeImminent(tp.getKey());
						if (!getItems().remove(tp.getValue()))
							throw new IllegalStateException(tp.getKey() + " is not in items!!");
					}
					return t;
				}
			}
		}
		return null;
	}

	public Label getLabel(Tab t) {
		return (Label) ((StackPane) t.getGraphic()).getChildren().get(0);
	}

	private Tab createTab(String name, Node n, boolean closeable) {
		final Tab tab = new Tab();
		final Label label = new Label(name);
		final StackPane pan = new StackPane(label);
		// Note: See getLabel(Tab) before changing this
		tab.setGraphic(pan);
		tab.setContent(n);
		tab.setClosable(closeable);
		if (tabDeleteHandler != null) {
			tab.onCloseRequestProperty().set((e) -> {
				tabDeleteHandler.deleteTab(this, children.get(locationOf(tab)).getKey(), tab, e);
			});
		}
		tab.onClosedProperty().set((e) -> {
			// Oh for fuck's sake!
			// The generic Event does not expose which tabpane it was removed
			// from
			// => we must check all of them
			for (int i = 0; i < children.size(); i++) {
				Pair<TabPane, StackPane> tp = children.get(i);
				if (tp.getKey().getTabs().size() == 0) {
					removeImminent(tp.getKey());
					if (!getItems().remove(tp.getValue()))
						throw new IllegalStateException(tp.getKey() + " is not in items!!!");
					break;// Safe concurrent modification.
				}
			}
		});

		label.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = FXSplittingTabPane.this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.put(dataFormat, "NULL");
				db.setContent(content);
				dragTab.set(tab);
				event.consume();
			}
		});

		label.setOnDragOver(event -> {
			if (event.getDragboard().hasContent(dataFormat))
				event.acceptTransferModes(TransferMode.MOVE);
			updateDir(pan, label, event);
			event.consume();
		});
		label.setOnDragEntered(event -> {
			updateDir(pan, label, event);
		});

		label.setOnDragDone((event) -> {
			dragTab.set(null);
			dragDir.set(0);
		});
		label.setOnDragExited(event -> {
			updateDir(pan, label, event);
		});

		label.setOnDragDropped(event -> {
			if (event.getDragboard().hasContent(dataFormat)) {
				Tab mv = dragTab.get();
				TabPane source = mv.getTabPane();
				TabPane target = tab.getTabPane();
				int targetIndex = 0;
				for (Tab t : target.getTabs()) {
					if (t == tab)
						break;
					targetIndex++;
				}
				if (dragDir.get() == 1)
					targetIndex++;

				int sourceIndex = 0;
				for (Tab t : source.getTabs()) {
					if (t == mv)
						break;
					sourceIndex++;
				}
				if (source == target && sourceIndex < targetIndex)// note: <,
																	// not <=
																	// because
																	// ==
																	// implies
																	// no
																	// action(see
																	// below)
																	// and may
																	// lead to
																	// negative
																	// index
					targetIndex--;// Target index will shift due to the remove

				if (source == target && sourceIndex == targetIndex)
					return;// No need to do anything

				removeImminent(source);
				source.getTabs().remove(sourceIndex);

				target.getTabs().add(targetIndex, mv);
				if (source.getTabs().size() == 0)
					remove(source);

				tabRearrangementCallback.tabMoved(this, target, targetIndex, mv);
			}
		});

		label.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					int location = locationOf(tab);
					if (location == -1) {
						new Throwable(name + " was clicked but doesn't seem to exist").printStackTrace();
						return;
					}
					Pair<TabPane, StackPane> containerP = children.get(location);
					removeImminent(containerP.getKey());
					containerP.getKey().getTabs().remove(tab);

					int newLocation = 1 - location;
					TabPane newContainer;
					if (newLocation >= children.size())
						newContainer = createAndAddTabPane();
					else
						newContainer = children.get(newLocation).getKey();
					newContainer.getTabs().add(tab);
					if (containerP.getKey().getTabs().size() == 0) {
						removeImminent(containerP.getKey());
						if (!getItems().remove(containerP.getValue()))
							throw new IllegalStateException(containerP.getKey() + " is not in items!!");
					}
					tabRearrangementCallback.tabMoved(FXSplittingTabPane.this, newContainer, -1, tab);
				}
			}
		});
		return tab;
	}

	private void removeImminent(TabPane subject) {
		// I fail to express my disappointment.
		if (subject.isFocused() && focusDefaulter != null)
			focusDefaulter.handle(new ActionEvent());
	}

	private void remove(TabPane tp) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getKey() == tp) {
				removeImminent(tp);
				getItems().remove(children.get(i).getValue());
				return;
			}
		}
		throw new IllegalStateException(tp + " is not in items!");
	}

	private void updateDir(StackPane pan, Label label, DragEvent event) {
		if (event.getDragboard().hasContent(dataFormat)) {
			boolean remove, add;
			int dir;
			if (event.getEventType() == DragEvent.DRAG_EXITED) {
				remove = true;
				add = false;
				dir = 0;
			} else {
				dir = (event.getX() >= label.getWidth() / 2 ? 1 : -1);
				if (event.getEventType() == DragEvent.DRAG_ENTERED) {
					remove = false;
					add = true;
				} else {
					add = (dir != dragDir.get());
					remove = add;
				}
			}
			if (remove) {
				pan.getChildren().clear();
				pan.getChildren().add(label);
			}
			if (add) {
				// Visual
				Image i;
				Pos o;
				if (dir == 1) {
					i = RIGHT_ARROW;
					o = Pos.CENTER_RIGHT;
				} else {
					assert dir == -1;
					i = LEFT_ARROW;
					o = Pos.CENTER_LEFT;
				}
				ImageView im = new ImageView(i);
				im.setMouseTransparent(true);
				StackPane.setAlignment(im, o);
				pan.getChildren().add(im);
				dragDir.set(dir);
			}
		}
	}

	protected int locationOf(TabPane tp) {
		int location = 0;
		for (Pair<TabPane, StackPane> p : children) {
			if (p.getKey() == tp)
				return location;
			location++;
		}
		return -1;
	}

	protected int locationOf(Tab tab) {
		int location = 0;
		for (Pair<TabPane, StackPane> p : children) {
			for (Tab t : p.getKey().getTabs()) {
				if (t == tab)
					return location;
			}
			location++;
		}
		return -1;
	}

	public Tab moveNode(Node n, int toTabPaneIndex, int toTabIndex) {
		for (int i = 0; i < children.size(); i++) {
			Pair<TabPane, StackPane> source = children.get(i);
			for (int j = 0; j < source.getKey().getTabs().size(); j++) {
				Tab t = source.getKey().getTabs().get(j);
				if (n == t.getContent()) {
					if (toTabPaneIndex < 0)
						toTabPaneIndex = 0;
					while (toTabPaneIndex >= children.size())
						createAndAddTabPane();
					Pair<TabPane, StackPane> target = children.get(toTabPaneIndex);

					if (toTabPaneIndex == i && toTabIndex == j)// Shortcut
						return t;

					source.getKey().getTabs().remove(j);
					if (toTabIndex < 0 || toTabIndex > target.getKey().getTabs().size())
						toTabIndex = target.getKey().getTabs().size();
					target.getKey().getTabs().add(toTabIndex, t);

					if (source.getKey().getTabs().size() == 0) {
						removeImminent(source.getKey());
						if (!getItems().remove(source.getValue()))
							throw new IllegalStateException(source.getKey() + " is not in items!!");
					}
					return t;
				}
			}
		}
		return null;
	}

	public int getNumTabPanes() {
		return children.size();
	}

	public TabPane getTabPane(int index) {
		return children.get(index).getKey();
	}
}
