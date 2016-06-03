package no.hal.emfblocks.cuteditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.hal.emfblocks.cuteditor.CutEditor.Tool;
import no.hal.emfblocks.hierarchy.TypePrimitive;
import no.hal.emfblocks.javafx.FXUtil;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyListener;

public class DrawingBoard implements Editor {
	public interface Clear {
		Clear THIS = new Clear() {
			@Override
			public boolean shouldBeCleared(TypePrimitive s, TypePrimitive c) {
				return c == s;
			}

			@Override
			public String getDescription(TypePrimitive myType) {
				return "Clear " + myType;
			}
		}, PARENTS = new Clear() {
			@Override
			public boolean shouldBeCleared(TypePrimitive s, TypePrimitive c) {
				return c.isParentOf(s);
			}

			@Override
			public String getDescription(TypePrimitive myType) {
				return "Clear parents of " + myType;
			}
		}, ALL = new Clear() {
			@Override
			public boolean shouldBeCleared(TypePrimitive s, TypePrimitive c) {
				return true;
			}

			@Override
			public String getDescription(TypePrimitive myType) {
				return "Clear all";
			}
		};

		public boolean shouldBeCleared(TypePrimitive selectedType, TypePrimitive candidateType);

		public String getDescription(TypePrimitive myType);
	}

	private static final short MAX_SIZE = 64;
	SplitPane splitPane;
	VBox leftPane, rightPane;
	ScrollPane leftScroller;
	StackPane contentPane;
	ListView<TypeNodeEditor> thumbList;
	HBox listControls;

	private Referencer referencer = new ReferenceSet();

	CutEditor editor;
	private Button addButton;
	private Button removeButton;
	private Button sizeButton;
	private Label positionLabel;

	public DrawingBoard(CutEditor editor) {
		this.editor = editor;

		thumbList = new ListView<>(editor.types);
		thumbList.setCellFactory(new Callback<ListView<TypeNodeEditor>, ListCell<TypeNodeEditor>>() {
			@Override
			public ListCell<TypeNodeEditor> call(ListView<TypeNodeEditor> param) {
				return new ListCell<TypeNodeEditor>() {
					private final Referencer anonReferencer = new ReferenceSet();

					@Override
					protected void updateItem(TypeNodeEditor item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
							setGraphic(null);
						} else {
							ImageView icon = new ImageView();
							icon.imageProperty().bind(item.myType.getContour().tabImage());
							Label l = new Label(item.getDisplayName(), icon);
							FXUtil.setBackground(l, item.labelcolor.get());
							item.labelcolor.addListener(anonReferencer, (prop, oldVal, newVal) -> {
								FXUtil.setBackground(l, newVal);
							});
							setGraphic(l);
						}
					}
				};
			}
		});
		leftScroller = new ScrollPane(thumbList);
		leftScroller.setFitToHeight(true);
		leftScroller.setFitToWidth(true);

		addButton = new Button("Add");
		addButton.setMaxWidth(Double.MAX_VALUE);
		addButton.onActionProperty().set((e) -> {
			TextField titleInput = new TextField(Object.class.toString());
			DialogPane dp = new DialogPane();
			dp.getChildren().add(titleInput);
			TypeNodeEditor item = thumbList.getSelectionModel().getSelectedItem();
			final TextInputDialog d = new TextInputDialog(
					item == null ? "" : ((Class<?>) item.myType.getKey()).getName());
			d.contentTextProperty().set("ClassName");
			d.setHeaderText("Please input a fully qualified class name.");
			d.setTitle("New Type");
			d.setOnCloseRequest((a) -> {
				d.close();
			});
			Optional<String> input = d.showAndWait();
			String str = input.isPresent() ? input.get() : null;
			if (str != null) {
				try {
					final TypeNodeEditor newType = editor.createTypeNodeEditor(str);
					int si = thumbList.getSelectionModel().getSelectedIndex();
					if (si < 0 || si > editor.types.size())
						si = 0;
					final int index = si;
					editor.types.add(index, newType);
					editor.undoManager.logUndo(() -> {
						editor.types.remove(index);
					}, () -> {
						editor.types.add(index, newType);
					}, "Added " + newType.getDisplayName());
					editor.updateLabelColors(thumbList.getSelectionModel().getSelectedItem());
				} catch (IllegalArgumentException err) {
					Alert errorD = new Alert(AlertType.ERROR, err.getMessage(), ButtonType.OK);
					errorD.setOnCloseRequest((a) -> {
						d.close();
					});
					errorD.showAndWait();
				}
			}
		});

		removeButton = new Button("Remove");
		removeButton.onActionProperty().set((e) -> {
			final int index = thumbList.getSelectionModel().getSelectedIndex();
			final TypeNodeEditor item = thumbList.getSelectionModel().getSelectedItem();
			if (item != null) {
				editor.types.remove(index);
				editor.undoManager.logUndo(() -> {
					editor.types.add(index, item);
				}, () -> {
					editor.types.remove(index);
				}, "Removed " + item.getDisplayName());
			}
		});

		HBox.setHgrow(addButton, Priority.ALWAYS);
		HBox.setHgrow(removeButton, Priority.ALWAYS);
		listControls = new HBox(addButton, removeButton);

		VBox.setVgrow(leftScroller, Priority.ALWAYS);
		VBox.setVgrow(listControls, Priority.NEVER);
		leftPane = new VBox(leftScroller/* , listControls */);

		Button minusZoomButton = new Button("-");
		minusZoomButton.onActionProperty().set((a) -> {
			editor.zoom(-1);
		});
		Label zoomLabel = new Label(zoomStr());
		zoomLabel.setPrefWidth(72);
		zoomLabel.setAlignment(Pos.BOTTOM_CENTER);
		editor.tabZoom.addListener(referencer, (prop, oldVal, newVal) -> {
			zoomLabel.setText(zoomStr());
		});
		Button plusZoomButton = new Button("+");
		plusZoomButton.onActionProperty().set((a) -> {
			editor.zoom(1);
		});

		positionLabel = new Label(editor.positionText.get());
		positionLabel.setAlignment(Pos.CENTER);
		editor.positionText.addListener(referencer, (prop, oldVal, newVal) -> {
			positionLabel.setText(newVal);
		});
		positionLabel.setPrefWidth(48);

		sizeButton = new Button(sizeStr());
		editor.hierarchy.connectorWidth.addListener(referencer, (prop, oldVal, newVal) -> {
			sizeButton.setText(sizeStr());
		});
		editor.hierarchy.connectorHeight.addListener(referencer, (prop, oldVal, newVal) -> {
			sizeButton.setText(sizeStr());
		});
		sizeButton.setPrefWidth(128);
		sizeButton.onActionProperty().set((a) -> {
			Dialog<ButtonType> d = new Dialog<>();
			d.setHeaderText("Header");
			d.setTitle("Resize...");
			d.setHeaderText("Please input new size for the tabs and slots.");

			d.getDialogPane().getButtonTypes().add(ButtonType.OK);
			d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

			final GridPane grid = new GridPane();
			grid.add(new Text("Width: "), 0, 0);
			final TextField widthField = new TextField(String.valueOf(editor.hierarchy.connectorWidth.get()));
			grid.add(widthField, 1, 0);
			grid.add(new Text("Height: "), 0, 1);
			final TextField heightField = new TextField(String.valueOf(editor.hierarchy.connectorHeight.get()));
			grid.add(heightField, 1, 1);

			d.getDialogPane().setContent(grid);
			Optional<ButtonType> r = d.showAndWait();
			if (r.isPresent() && r.get() == ButtonType.OK) {
				try {
					final short newWidth = Short.parseShort(widthField.getText());
					final short newHeight = Short.parseShort(heightField.getText());
					if (newWidth < 1)
						throw new IllegalArgumentException("Width: " + newWidth + " is not a positive integer!");
					else if (newHeight < 1)
						throw new IllegalArgumentException("Height: " + newHeight + " is not a positive integer!");
					else if (newWidth > MAX_SIZE)
						throw new IllegalArgumentException(
								"Width: " + newWidth + " is too large... Maximum size is " + MAX_SIZE);
					else if (newHeight > MAX_SIZE)
						throw new IllegalArgumentException(
								"Height: " + newHeight + " is too large... Maximum size is " + MAX_SIZE);

					final short oldWidth = editor.hierarchy.connectorWidth.get();
					final short oldHeight = editor.hierarchy.connectorHeight.get();
					boolean chw = newWidth != oldWidth;
					boolean chh = newHeight != oldHeight;
					if (chw || chh) {
						final ArrayList<TypeNodeEditor> localTypes = new ArrayList<>();
						final ArrayList<byte[]> rasterUndos = new ArrayList<>();
						for (TypeNodeEditor t : editor.types) {
							localTypes.add(t);
							rasterUndos.add(t.getPixels());
						}
						if (chw)
							((Property<Short>) editor.hierarchy.connectorWidth).set(newWidth);
						if (chh)
							((Property<Short>) editor.hierarchy.connectorHeight).set(newHeight);

						final ArrayList<byte[]> rasterRedos = new ArrayList<>();
						for (TypeNodeEditor t : editor.types)
							rasterRedos.add(t.getPixels());

						editor.undoManager.logUndo(() -> {
							if (chw)
								((Property<Short>) editor.hierarchy.connectorWidth).set(oldWidth);
							if (chh)
								((Property<Short>) editor.hierarchy.connectorHeight).set(oldHeight);
							for (int i = 0; i < localTypes.size(); i++)
								localTypes.get(i).setPixels(rasterUndos.get(i));
							for (int i = 0; i < localTypes.size(); i++)
								localTypes.get(i).updateRaster(false);
						}, () -> {
							if (chw)
								((Property<Short>) editor.hierarchy.connectorWidth).set(newWidth);
							if (chh)
								((Property<Short>) editor.hierarchy.connectorHeight).set(newHeight);
							for (int i = 0; i < localTypes.size(); i++)
								localTypes.get(i).setPixels(rasterRedos.get(i));
							for (int i = 0; i < localTypes.size(); i++)
								localTypes.get(i).updateRaster(false);
						}, "Changed size from (" + oldWidth + ", " + oldHeight + ") to (" + newWidth + ", " + newHeight
								+ ")");
					}
				} catch (IllegalArgumentException err) {
					if (err instanceof NumberFormatException)
						new Alert(AlertType.ERROR, "Only integer input allowed: " + err.getMessage(), ButtonType.OK)
								.showAndWait();
					else
						new Alert(AlertType.ERROR, err.getMessage(), ButtonType.OK).showAndWait();
				}
			}
		});
		// Note: Java 1.8.0 u60: Buttons with image only are bugged, causing
		// artifacts in other buttons that have text.
		// Using untoggleable ToggleButtons here as a workaround, but this
		// likely only works because there are no toggle buttons with text in
		// the scene.
		// The bug manifests with both null and the empty string.
		final ToggleButton clearThisButton = new ToggleButton(null,
				new ImageView(new Image(getClass().getResourceAsStream("newOne.png"))));
		clearThisButton.setPadding(new Insets(0, 4, 0, 4));
		clearThisButton.setTooltip(new Tooltip("Clear the selected type's cut"));
		clearThisButton.onActionProperty().set((a) -> {
			clear(Clear.THIS);
			clearThisButton.setSelected(false);
		});
		final ToggleButton clearParentsButton = new ToggleButton(null,
				new ImageView(new Image(getClass().getResourceAsStream("newParents.png"))));
		clearParentsButton.setPadding(new Insets(0, 4, 0, 4));
		clearParentsButton.setTooltip(new Tooltip("Clear the cuts of the selected type's parents"));
		clearParentsButton.onActionProperty().set((a) -> {
			clear(Clear.PARENTS);
			clearParentsButton.setSelected(false);
		});
		final ToggleButton clearAllButton = new ToggleButton(null,
				new ImageView(new Image(getClass().getResourceAsStream("newAll.png"))));
		clearAllButton.setPadding(new Insets(0, 4, 0, 4));
		clearAllButton.setTooltip(new Tooltip("Clear the cuts of all types"));
		clearAllButton.onActionProperty().set((a) -> {
			clear(Clear.ALL);
			clearAllButton.setSelected(false);
		});

		final ToggleButton brushButton = new ToggleButton(null,
				new ImageView(new Image(getClass().getResourceAsStream("brush.png"))));
		brushButton.setPadding(Insets.EMPTY);
		final ToggleButton bucketButton = new ToggleButton(null,
				new ImageView(new Image(getClass().getResourceAsStream("bucket.png"))));
		bucketButton.setPadding(Insets.EMPTY);

		final ToggleButton[] buttons = { brushButton, bucketButton };

		Color sColor = Color.CYAN;
		Color dColor = null;

		final Runnable toolButtonFixer = () -> {
			Tool newVal = editor.currentTool.get();
			ToggleButton cButton = null;
			if (newVal == CutEditor.Tool.BRUSH)
				cButton = brushButton;
			else if (newVal == CutEditor.Tool.BUCKET)
				cButton = bucketButton;
			for (ToggleButton b : buttons) {
				boolean n = b == cButton;
				FXUtil.setBackground(b, n ? sColor : dColor);
				b.setSelected(n);
			}
		};

		final PropertyListener<Tool> toolButtonUpdater = (prop, oldVal, newVal) -> {
			toolButtonFixer.run();
		};
		editor.currentTool.addListener(referencer, toolButtonUpdater);

		// Hack to prevent deselecting all tools
		for (ToggleButton b : buttons)
			b.selectedProperty().addListener((p, o, n) -> {
				toolButtonFixer.run();
			});

		brushButton.onActionProperty().set((a) -> {
			editor.currentTool.set(CutEditor.Tool.BRUSH);
		});
		bucketButton.onActionProperty().set((a) -> {
			editor.currentTool.set(CutEditor.Tool.BUCKET);
		});

		toolButtonFixer.run();

		HBox statusbar = new HBox(clearAllButton, clearParentsButton, clearThisButton, brushButton, bucketButton,
				sizeButton, positionLabel, minusZoomButton, zoomLabel, plusZoomButton);
		statusbar.setAlignment(Pos.CENTER_RIGHT);
		statusbar.setBorder(new Border(
				new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

		contentPane = new StackPane();
		contentPane.setPrefWidth(400);
		leftPane.setPrefWidth(200);

		VBox.setVgrow(statusbar, Priority.NEVER);
		VBox.setVgrow(contentPane, Priority.ALWAYS);
		rightPane = new VBox(contentPane, statusbar);

		SplitPane.setResizableWithParent(leftPane, false);
		splitPane = new SplitPane(leftPane, rightPane);

		thumbList.getSelectionModel().selectedItemProperty().addListener((prop, oldVal, newVal) -> {
			if (oldVal != null) {
				contentPane.getChildren().remove(oldVal.getUI());
				oldVal.deselect();
			}
			if (newVal != null) {
				contentPane.getChildren().add(newVal.getUI());
				newVal.select();
			}
			editor.updateLabelColors(newVal);

			if (!editor.undoManager.isPerforming())
				editor.undoManager.logUndo(() -> {
					thumbList.getSelectionModel().select(oldVal);
				}, () -> {
					thumbList.getSelectionModel().select(newVal);
				}, "Selected " + (newVal == null ? "null" : newVal.getDisplayName()));
		});
	}

	private void clear(Clear type) {
		TypeNodeEditor selected = thumbList.getSelectionModel().selectedItemProperty().get();
		final boolean updateChildren = type != Clear.ALL;
		final ArrayList<TypeNodeEditor> localTypes = new ArrayList<>();
		final ArrayList<byte[]> rasterUndos = new ArrayList<>();
		for (TypeNodeEditor t : editor.types) {
			assert t != null;
			assert type != null;
			if (type.shouldBeCleared(selected == null ? null : selected.myType, t.myType)) {
				localTypes.add(t);
				rasterUndos.add(t.getPixels());
			}
		}
		int w = editor.hierarchy.connectorWidth.get();
		int h = editor.hierarchy.connectorHeight.get();

		final byte[] clearedRaster = new byte[w * h];
		Arrays.fill(clearedRaster, (byte) 1);

		for (int i = 0; i < localTypes.size(); i++)
			localTypes.get(i).setPixels(clearedRaster);
		for (int i = 0; i < localTypes.size(); i++)
			localTypes.get(i).updateRaster(updateChildren);

		editor.undoManager.logUndo(() -> {
			for (int i = 0; i < localTypes.size(); i++)
				localTypes.get(i).setPixels(rasterUndos.get(i));
			for (int i = 0; i < localTypes.size(); i++)
				localTypes.get(i).updateRaster(updateChildren);
		}, () -> {
			for (int i = 0; i < localTypes.size(); i++)
				localTypes.get(i).setPixels(clearedRaster);
			for (int i = 0; i < localTypes.size(); i++)
				localTypes.get(i).updateRaster(updateChildren);
		}, type.getDescription(selected == null ? null : selected.myType));
	}

	private String sizeStr() {
		return "Size( " + editor.hierarchy.connectorWidth.get() + ", " + editor.hierarchy.connectorHeight.get() + " )";
	}

	private String zoomStr() {
		return "Zoom( " + editor.tabZoom.get() + "x )";
	}

	@Override
	public Node getUI() {
		return splitPane;
	}

}
