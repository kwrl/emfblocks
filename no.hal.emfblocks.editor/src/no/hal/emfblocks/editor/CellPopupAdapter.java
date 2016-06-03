package no.hal.emfblocks.editor;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryCellFactory.ICellCreationListener;
import javafx.geometry.Side;
import javafx.scene.control.Cell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import no.hal.emfblocks.javafx.FXUtil;

public class CellPopupAdapter implements ICellCreationListener {
	private final EditingDomain editingDomain;

	public CellPopupAdapter(EditingDomain domain) {
		this.editingDomain = domain;
	}

	@Override
	public void cellCreated(Cell<?> cell) {
		cell.onMouseClickedProperty().set((e) -> {
			if (e.getButton() == MouseButton.SECONDARY && cell.getItem() instanceof EObject) {
				EObject item = (EObject) cell.getItem();
				ArrayList<MenuItem> items = new ArrayList<MenuItem>();
				MenuItem deleteItem = new MenuItem("Delete " + cell.getText());
				deleteItem.onActionProperty().set((a) -> {
					editingDomain.getCommandStack().execute(new ChangeCommand(item.eResource()) {
						@Override
						protected void doExecute() {
							EcoreUtil.remove(item);
						}
					});
				});
				items.add(deleteItem);

				/*
				 * for(Entry<EClass, WorkAreaMapping> entry :
				 * workAreaTypes.entrySet()) { EClass c = entry.getKey();
				 * WorkAreaMapping wamm = entry.getValue(); MenuItem item = new
				 * MenuItem("Add "+c.getName()); item.onActionProperty().set((a)
				 * -> { EObject obj =
				 * c.getEPackage().getEFactoryInstance().create(c); assert
				 * thePane == split; EObjectWorkArea w = new
				 * EObjectWorkArea(editor, wamm, obj); preventRecursion = true;
				 * //FIXME AddCommand didn't work but there has to be a better
				 * way to do this!
				 * editor.getEditingDomain().getCommandStack().execute(new
				 * ChangeCommand(editor.getModelInstance().getInstanceResource()
				 * ) { protected void doExecute() {
				 * editor.getModelInstance().getInstanceResource().getContents()
				 * .add(obj); } }); assert
				 * editor.getModelInstance().getInstanceResource().getContents()
				 * .contains(obj) :
				 * editor.getModelInstance().getInstanceResource().getContents()
				 * +" did not contain "+ obj+" after the add command!";
				 * preventRecursion = false; addObjectAsTab(w, location, -1);
				 * }); items.add(item); }
				 */
				ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[items.size()]));
				FXUtil.contextMenuCreated(menu);
				menu.show(cell, Side.BOTTOM, 0, 0);
			}
		});
	}

}
