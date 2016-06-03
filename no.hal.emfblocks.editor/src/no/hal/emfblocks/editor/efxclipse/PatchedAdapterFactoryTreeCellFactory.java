package no.hal.emfblocks.editor.efxclipse;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryTreeCellFactory;
import at.bestsolution.efxclipse.runtime.emf.edit.ui.AdapterFactoryCellFactory;
import javafx.scene.control.Cell;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;

/**
 * {@link AdapterFactoryTreeCellFactory} is bugged(doesn't clear empty cells).
 * This class patches the issue.
 */
public class PatchedAdapterFactoryTreeCellFactory extends AdapterFactoryTreeCellFactory {

	public PatchedAdapterFactoryTreeCellFactory(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public TreeCell<Object> call(TreeView<Object> param) {
		// Cell implementation is an anonymous class, so we can't inherit from
		// it.
		// Need to reimplement the entire damn thing. Furthermore, access
		// restrictions
		// prevent us from doing it cleanly. Thus, reflection hacks follow.

		final TreeCell<Object> treeCell = new TreeCell<Object>() {

			Object currentItem = null;
			ICellEditHandler cellEditHandler;

			AdapterImpl adapter = new AdapterImpl() {
				@Override
				public void notifyChanged(Notification msg) {
					update(msg.getNotifier());
				}
			};

			@Override
			public void startEdit() {
				super.startEdit();

				try {
					Method getCellEditHandler = AdapterFactoryCellFactory.class.getDeclaredMethod("getCellEditHandler",
							Cell.class);
					getCellEditHandler.setAccessible(true);
					cellEditHandler = (ICellEditHandler) getCellEditHandler
							.invoke(PatchedAdapterFactoryTreeCellFactory.this, this);

					if (cellEditHandler != null)
						cellEditHandler.startEdit(this);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void commitEdit(Object newValue) {
				super.commitEdit(newValue);
				if (cellEditHandler != null)
					cellEditHandler.commitEdit(this, newValue);
			}

			@Override
			public void cancelEdit() {
				super.cancelEdit();
				if (cellEditHandler != null)
					cellEditHandler.cancelEdit(this);
				update(getItem());
			}

			@Override
			protected void updateItem(Object item, boolean empty) {
				super.updateItem(item, empty);
				// check if the item changed
				if (item != currentItem) {

					// remove the adapter if attached
					if (currentItem instanceof Notifier)
						((Notifier) currentItem).eAdapters().remove(adapter);

					// update the current item
					currentItem = item;

					// attach the adapter to the new item
					if (currentItem instanceof Notifier)
						((Notifier) currentItem).eAdapters().add(adapter);
				}

				try {
					Field cellUpdateListenersField = AdapterFactoryCellFactory.class
							.getDeclaredField("cellUpdateListeners");
					cellUpdateListenersField.setAccessible(true);
					@SuppressWarnings("unchecked")
					List<ICellUpdateListener> cellUpdateListeners = (List<ICellUpdateListener>) cellUpdateListenersField
							.get(PatchedAdapterFactoryTreeCellFactory.this);
					// notify the listeners
					for (ICellUpdateListener cellUpdateListener : cellUpdateListeners)
						cellUpdateListener.updateItem(this, item, empty);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				update(item);

				if (empty) {
					setText(null);
					setGraphic(null);
				}
			}

			private void update(Object item) {
				// setText(item == null ? "null" : item.toString());

				try {
					Method applyItemProviderStyle = AdapterFactoryCellFactory.class.getDeclaredMethod(
							"applyItemProviderStyle", Object.class, Cell.class, AdapterFactory.class);
					applyItemProviderStyle.setAccessible(true);
					applyItemProviderStyle.invoke(PatchedAdapterFactoryTreeCellFactory.this, item, this,
							adapterFactory);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};

		try {
			Field cellCreationListenersField = AdapterFactoryCellFactory.class
					.getDeclaredField("cellCreationListeners");
			cellCreationListenersField.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<ICellCreationListener> cellCreationListeners = (List<ICellCreationListener>) cellCreationListenersField
					.get(PatchedAdapterFactoryTreeCellFactory.this);
			// notify the listeners
			for (ICellCreationListener cellCreationListener : cellCreationListeners)
				cellCreationListener.cellCreated(treeCell);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return treeCell;
	}
}
