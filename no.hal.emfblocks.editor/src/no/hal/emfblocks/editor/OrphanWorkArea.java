package no.hal.emfblocks.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.javafx.fxml.FXWorkArea;
import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;

public class OrphanWorkArea extends EMFWorkArea {
	private final Adapter resourceAdapter;

	public OrphanWorkArea(EMFWorkAreaManager manager) {
		super(manager, manager.getEditor().getModelInstance().getOrCreateOrphansRepresentation());

		preventRecursion = true;
		try {
			for (EObject obj : editor.getModelInstance().getInstanceResource().getContents()) {
				if (obj.eContainer() == null) {
					// Note these objects are directly contained in the
					// resource, so they do not have a container.
					// The editor allows blocks representing such objects to
					// exist in any work area.
					BlockMapping bmm = editor.getMappingModel().getBlockMapping(obj.eClass());
					if (bmm != null) {
						BlockRepresentation blockRepresentation = editor.getModelInstance()
								.getOrCreateAndRegisterBlockRepresentation(obj, bmm);
						add(blockRepresentation, bmm);
					}
				}
			}
		} finally {
			preventRecursion = false;
		}

		resourceAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (msg.getEventType() == Notification.MOVE)
					return;// Ignore MOVE because work areas have no order.

				if (!preventRecursion && msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
					preventRecursion = true;
					try {
						if (msg.getNewValue() == null) {
							if (msg.getOldValue() instanceof EObject) {
								if (editor.getMappingModel()
										.getBlockMapping(((EObject) msg.getOldValue()).eClass()) != null) {
									if (!duplicationSet.remove((EObject) msg.getOldValue()))// If
																							// it
																							// didn't
																							// exist
																							// twice
									{
										int rem = removeObject((EObject) msg.getOldValue());
										if (rem == -1) // Note: This is normal.
														// There's no guarantee
														// it will be here.
											new Throwable("Warning: The object " + msg.getOldValue()
													+ " could not be found in the work area...").printStackTrace();
									}
								}
							}
						} else {
							if (msg.getNewValue() instanceof EObject) {
								if (((EObject) msg.getNewValue()).eContainer() == null) {
									BlockMapping bmm = editor.getMappingModel()
											.getBlockMapping(((EObject) msg.getNewValue()).eClass());
									if (bmm != null) {
										if (containsObject((EObject) msg.getNewValue()))// The
																						// instance
																						// exists
																						// in
																						// two
																						// places
																						// at
																						// the
																						// same
																						// time.
											Assert.isTrue(duplicationSet.add((EObject) msg.getNewValue()));// This
																											// is
																											// an
																											// invalid
																											// state
																											// but
																											// it
																											// must
																											// be
																											// dealt
																											// with.
										else {
											BlockRepresentation blockRepresentation = editor.getModelInstance()
													.getOrCreateAndRegisterBlockRepresentation(
															(EObject) msg.getNewValue(), bmm);
											add(blockRepresentation, bmm);
										}
									}
								}
							} else {
								System.err.println("Warning: Weird message at " + EMFWorkArea.class.getSimpleName()
										+ ": \"" + msg + "\"\n" + "(" + msg.getNewValue() + " instanceof EObject == "
										+ (msg.getNewValue() instanceof EObject) + ")");
							}
						}
					} finally {
						preventRecursion = false;
					}
				}
			}
		};
		editor.getModelInstance().getInstanceResource().eAdapters().add(resourceAdapter);
	}

	// private boolean orphanBelongsHere(BlockRepresentation
	// blockRepresentation) {
	// return representation.getObject() == null;
	// TODO if this is going to work, then a mechanism is needed to move the
	// block between work areas when this property changes.
	// Objects without a container can go in multiple places depending
	/*
	 * JigsawRepresentation prefContainer =
	 * blockRepresentation.getPreferredContainer(); if(prefContainer instanceof
	 * WorkAreaRepresentation)//implied: non-null { if(prefContainer ==
	 * this.representation)//The object goes here return true; else//The object
	 * goes somewhere else {
	 * if(!manager.contains((WorkAreaRepresentation)prefContainer))//The place
	 * where the object goes doesn't exist. { if(this.representation.getObject()
	 * == null)//Only the orphan pane can contain something that doesn't fit
	 * anywhere. return true; } } } else if(this.representation.getObject() ==
	 * null)//The orphan pane receives stuff that doesn't go into another
	 * working area. return true; return false;
	 */
	// }

	@Override
	public void pFieldChange(PFieldImmutable<? extends WBlock> field, WBlock newChild, int index)
			throws IllegalArgumentException {
		if (!preventRecursion) {
			preventRecursion = true;
			try {
				// Note that there is no regard for order, because it wouldn't
				// make sense anyway
				if (newChild == null) {
					WBlock blk = field.get(index);
					if (blk instanceof EMFPointerBlock)
						throw new IllegalArgumentException();
					EObject obj = ((EObjectBlock) blk).getObject();
					Assert.isTrue(editor.getModelInstance().getInstanceResource().getContents().remove(obj));
				} else {
					if (newChild instanceof EMFPointerBlock)
						throw new IllegalArgumentException();
					EObject obj = ((EObjectBlock) newChild).getObject();
					Assert.isTrue(editor.getModelInstance().getInstanceResource().getContents().add(obj));
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			} finally {
				preventRecursion = false;
			}
		}
	}

	@Override
	protected void dispose2() {
		super.dispose2();
		editor.getModelInstance().getInstanceResource().eAdapters().remove(resourceAdapter);
	}

	@Override
	protected FXWorkArea createDefaultUI() {
		return new FXWorkArea(this);
	}
}
