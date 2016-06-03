package no.hal.emfblocks.cuteditor;

import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.ModifiableObservableListBase;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import no.hal.emfblocks.UndoManager;
import no.hal.emfblocks.contour.BitmapContour;
import no.hal.emfblocks.hierarchy.JavaClassNode;
import no.hal.emfblocks.hierarchy.TypeHierarchy;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.Property;

public class CutEditor {
	public static final boolean LOG = true;

	public final ModifiableObservableListBase<TypeNodeEditor> types;
	private AnchorPane contentPane;

	public enum Tool {
		BRUSH, BUCKET;
	}

	public final Property<Tool> currentTool = new Property<CutEditor.Tool>(Tool.BRUSH);

	public final ObjectProperty<Editor> currentView = new ObjectPropertyBase<Editor>() {
		@Override
		public Object getBean() {
			return CutEditor.this;
		}

		@Override
		public String getName() {
			return "Current Editor View";
		}

	};
	final Referencer referencer = new ReferenceSet();
	private VBox rootPane;
	public final Property<Short> tabZoom = new Property<Short>((short) 8);

	public final Property<String> positionText = new Property<String>(String.class, null);

	public final TypeHierarchy hierarchy;
	public final UndoManager undoManager;

	/**
	 * Create a new cut editor for the given type hierarchy.
	 * 
	 * Creating two simultaneous cut editors for the same hierarchy will result
	 * in undefined behaviour!
	 * 
	 * @param stage
	 *            The stage that this cut editor will reside in. If non-null,
	 *            then the Cut editor will attach a window closing listener to
	 *            the stage, so that it can save any cut edits before the
	 *            program closes. If this is not a practical setup, then the
	 *            application should call {@link #dispose()} before the cut
	 *            editor is lost.
	 * @param h
	 *            The hierarchy that will be edited.
	 */
	public CutEditor(TypeHierarchy h) {
		this.hierarchy = h;
		undoManager = new UndoManager(LOG, 50, null);

		this.types = new ModifiableObservableListBase<TypeNodeEditor>() {
			ArrayList<TypeNodeEditor> internalList = new ArrayList<TypeNodeEditor>();

			@Override
			public TypeNodeEditor get(int index) {
				return internalList.get(index);
			}

			@Override
			public int size() {
				return internalList.size();
			}

			@Override
			protected void doAdd(int arg0, TypeNodeEditor arg1) {
				internalList.add(arg0, arg1);
			}

			@Override
			protected TypeNodeEditor doRemove(int arg0) {
				return internalList.remove(arg0);
			}

			@Override
			protected TypeNodeEditor doSet(int arg0, TypeNodeEditor arg1) {
				return internalList.set(arg0, arg1);
			}

		};

		for (int i = 0; i < h.getNumValues(); i++) {
			types.add(new TypeNodeEditor(this, h.getValue(i)));
		}

		MenuBar menubar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem exitItem = new MenuItem("Quit");
		exitItem.onActionProperty().set(e -> {
			System.exit(0);
		});
		fileMenu.getItems().add(exitItem);
		menubar.getMenus().add(fileMenu);

		contentPane = new AnchorPane();

		currentView.addListener((prop, oldVal, newVal) -> {
			if (oldVal != newVal) {
				if (oldVal != null)
					contentPane.getChildren().remove(oldVal.getUI());
				if (newVal != null) {
					AnchorPane.setBottomAnchor(newVal.getUI(), 0.0);
					AnchorPane.setTopAnchor(newVal.getUI(), 0.0);
					AnchorPane.setLeftAnchor(newVal.getUI(), 0.0);
					AnchorPane.setRightAnchor(newVal.getUI(), 0.0);
					contentPane.getChildren().add(newVal.getUI());
				}
			}
		});

		currentView.set(new DrawingBoard(this));

		rootPane = new VBox();
		VBox.setVgrow(menubar, Priority.NEVER);
		rootPane.getChildren().add(menubar);
		VBox.setVgrow(contentPane, Priority.ALWAYS);
		rootPane.getChildren().add(contentPane);

		rootPane.onKeyPressedProperty().set((e) -> {
			if (e.getEventType() == KeyEvent.KEY_PRESSED && e.isControlDown()) {
				if (e.getCode() == KeyCode.Z)
					undoManager.undo();
				else if (e.getCode() == KeyCode.Y)
					undoManager.redo();
			}
		});
	}

	public void dispose() {
		hierarchy.saveCuts();
	}

	public Pane getUI() {
		return rootPane;
	}

	/**
	 * Create a new type node and its editor with the given name. This will not
	 * add it to the list.
	 */
	TypeNodeEditor createTypeNodeEditor(String nodeName) throws IllegalArgumentException {
		try {
			short w = hierarchy.connectorWidth.get();
			short h = hierarchy.connectorHeight.get();
			JavaClassNode newNode;
			newNode = TypeHierarchy.toNode(Class.forName(nodeName));

			for (TypeNodeEditor n : types) {
				if (n.myType.getKey().equals(newNode.getKey()))
					throw new IllegalArgumentException("The class " + nodeName + " has already been added.");
			}
			// TODO this is wrong: Node should be added to type hierarchy and
			// the hierarchy should assign the contour
			newNode.setContour(new BitmapContour(newNode, new byte[w * h], w, h));
			TypeNodeEditor newType = new TypeNodeEditor(this, newNode);
			return newType;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("No such class: " + nodeName);
		}
	}

	/**
	 * Increment zoom by the indicated number of powers of two(e.g. -1 for
	 * halving the canvas size, 1 for doubling). The zoom level is not recorded
	 * in the undo record.
	 */
	public void zoom(int incr) {
		tabZoom.set((short) Math.min(64, Math.max(1, tabZoom.get() * Math.pow(2, incr))));
	}

	public void updateLabelColors(TypeNodeEditor selectedNode) {
		for (TypeNodeEditor t : types) {
			t.updateLabelColor(selectedNode);
		}
	}
}
