package no.hal.emfblocks.editor;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.slots.MultiSlot;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;

public class EMFMultiSlot extends MultiSlot implements EMFSlot {
	protected final EMFBlockEditor editor;
	protected final EObject object;
	protected final EReference reference;
	protected boolean preventRecursion = false;
	protected boolean initialized = false;
	protected boolean destroyed = false;
	protected Adapter myAdapter;

	public EMFMultiSlot(EMFBlockEditor editor, EObject obj, EReference e) {
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
			preventRecursion = true;
			try {
				@SuppressWarnings("unchecked")
				List<EObject> source = (List<EObject>) object.eGet(reference);
				for (int i = 0; i < source.size(); i++) {
					EObject child = source.get(i);
					update(i, child);
				}
			} finally {
				preventRecursion = false;
			}

			initialized = true;

			myAdapter = new AdapterImpl() {
				@Override
				public void notifyChanged(Notification msg) {
					// System.out.println("WHY??
					// "+object.eClass().getName()+"."+reference.getName()+":
					// index "+msg.getPosition()+" set to "+msg.getNewValue());

					if (!preventRecursion && msg.getFeature() == reference) {
						preventRecursion = true;
						try {
							int realPosition;
							if (msg.getPosition() == Notification.NO_INDEX) {
								if (msg.getNewValue() != null)
									throw new IllegalArgumentException("Invalid notification on many-type reference "
											+ object.eClass().getName() + "." + reference.getName() + ": index "
											+ msg.getPosition() + " set to " + msg.getNewValue());
								System.out.println("Bogus change on many-type reference " + object.eClass().getName()
										+ "." + reference.getName() + ": index " + msg.getPosition() + " set to "
										+ msg.getNewValue());
								return; // Bogus change, why is this even
										// delivered
							} else
								realPosition = msg.getPosition();
							update(realPosition, (EObject) msg.getNewValue());
						} finally {
							preventRecursion = false;
						}
					}
				}
			};
			object.eAdapters().add(myAdapter);
		}
	}

	private void update(int index, EObject child) {
		if (child == null)
			getChildren().remove(index);
		else {
			BlockMapping bmm = editor.getMappingModel().getBlockMapping(child.eClass());
			if (bmm == null)
				throw new IllegalArgumentException(
						child + " has no block mapping model, but is referenced by " + object + " at " + reference);
			WBlock element;
			if (reference.isContainment())
				element = new EObjectBlock(editor, bmm, child);
			else {
				element = editor.getReferenceResolver().createPointerBlock(child);
			}
			getChildren().insert(index, element);
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
				@SuppressWarnings("unchecked")
				List<Object> target = (List<Object>) object.eGet(reference);
				EObject value = MMUtil.extractEObject(newChild);
				if (value == null)
					target.remove(index);
				else
					target.add(index, value);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			} finally {
				preventRecursion = false;
			}
		}
	}

	@Override
	public BlockDropAction accepts(WBlock dragBlock) {
		if ((dragBlock instanceof EMFPointerBlock) && reference.isContainment()) {
			// System.err.println("You cannot drop a pointer block on a
			// containment reference slot!");
			return BlockDropAction.CANCEL;
		}
		if (!(dragBlock instanceof EMFPointerBlock) && !reference.isContainment()) {
			// System.err.println("You can only drop pointer blocks on a
			// non-containment reference slot!");
			return BlockDropAction.CANCEL;
		}
		return super.accepts(dragBlock);
	}

	@Override
	public void dispose() {
		if (destroyed)
			new Throwable("Warning: slot destroyed twice: " + this).printStackTrace();
		else {
			destroyed = true;
			if (initialized)
				Assert.isTrue(object.eAdapters().remove(myAdapter));
			else
				new Throwable("Warning: slot destroyed before it was added: " + this).printStackTrace();
			super.dispose();
		}
	}
}
