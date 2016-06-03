package no.hal.emfblocks.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.slots.SingleSlot;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;

public class EMFSingleSlot extends SingleSlot implements EMFSlot {

	protected final EMFBlockEditor editor;
	protected final EObject object;
	protected final EReference reference;
	protected boolean preventRecursion = false;
	protected boolean initialized = false;
	protected Adapter myAdapter;

	public EMFSingleSlot(EMFBlockEditor editor, EObject obj, EReference e) {
		super(MMUtil.typeOf(editor.getRoot(), obj, e));
		this.editor = editor;
		this.object = obj;
		this.reference = e;
		labelTextProp.set((e.isContainment() ? EMFBlockEditor.CONTAINMENT_SYMBOL : EMFBlockEditor.POINTER_SYMBOL)
				+ e.getName());
	}

	@Override
	public void addNotify(PFieldImmutable<?> newField) {
		super.addNotify(newField);
		if (!initialized) {
			EObject child = (EObject) object.eGet(reference);
			preventRecursion = true;
			try {
				update(child);
			} finally {
				preventRecursion = false;
			}

			initialized = true;
			myAdapter = new AdapterImpl() {
				@Override
				public void notifyChanged(Notification msg) {
					if (!preventRecursion && msg.getFeature() == reference) {
						preventRecursion = true;
						try {
							update((EObject) msg.getNewValue());
						} finally {
							preventRecursion = false;
						}
					}
				}
			};
			object.eAdapters().add(myAdapter);
		}
	}

	private void update(EObject newValue) {
		EObject currentValue = MMUtil.extractEObject(getChildren().get());
		if (currentValue == newValue)
			return;// Do nothing.

		if (newValue == null)
			getChildren().remove();
		else {
			BlockMapping bmm = editor.getMappingModel().getBlockMapping(newValue.eClass());
			if (bmm == null)
				throw new IllegalArgumentException(
						newValue + " has no block mapping model, but is referenced by " + object + " at " + reference);
			WBlock element;
			if (reference.isContainment())
				element = new EObjectBlock(editor, bmm, newValue);
			else {
				element = editor.getReferenceResolver().createPointerBlock(newValue);
			}
			getChildren().set(element);
		}
	}

	@Override
	public void pFieldChange(PFieldImmutable<? extends WBlock> field, WBlock newChild, int index)
			throws IllegalArgumentException {
		super.pFieldChange(field, newChild, index);

		if (!preventRecursion) {
			preventRecursion = true;
			// SLOPPY
			try {
				EObject value = MMUtil.extractEObject(newChild);
				object.eSet(reference, value);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			} finally {
				preventRecursion = false;
			}
		}
	}

	@Override
	public BlockDropAction accepts(WBlock dragBlock) {
		if (!(dragBlock instanceof EMFPointerBlock) ^ reference.isContainment())
			return BlockDropAction.CANCEL;
		return super.accepts(dragBlock);
	}

	@Override
	public void dispose() {
		super.dispose();
		Assert.isTrue(object.eAdapters().remove(myAdapter));
	}
}
