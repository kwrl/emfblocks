package no.hal.emfblocks.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import no.hal.emfblocks.BlockDropAction;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.editor.ui.AttributeFormFactory;
import no.hal.emfblocks.editor.ui.DefaultAttributeFormFactory;
import no.hal.emfblocks.javafx.fxml.FXWorkArea;
import no.hal.emfblocks.layout.BlockRepresentation;
import no.hal.emfblocks.layout.WorkAreaRepresentation;
import no.hal.emfblocks.mapping.BlockMapping;
import no.hal.emfblocks.mapping.WorkAreaContainment;
import no.hal.emfblocks.mapping.WorkAreaFormMapping;
import no.hal.emfblocks.mapping.WorkAreaMapping;
import no.hal.emfblocks.util.data.pfield.PFieldImmutable;

public class EObjectWorkArea extends EMFWorkArea {
	protected final WorkAreaMapping mappingModel;

	protected final Adapter myAdapter;
	public final BlockLabelProvider labelProvider;

	protected Node decoratedUI;

	public EObjectWorkArea(EMFWorkAreaManager manager, WorkAreaMapping wamm, EObject obj) {
		this(manager, wamm,
				manager.getEditor().getModelInstance().getOrCreateAndRegisterWorkAreaRepresentation(obj, wamm));
	}

	private EObjectWorkArea(EMFWorkAreaManager manager, WorkAreaMapping wamm, WorkAreaRepresentation representation) {
		super(manager, representation);
		this.mappingModel = wamm;

		this.labelProvider = new BlockLabelProvider(wamm, representation.getObject());

		preventRecursion = true;
		try {
			for (EReference e : getObject().eClass().getEAllReferences()) {
				if (editor.getMappingModel().getWorkAreaContainment(getObject().eClass(), e) != null) {
					Object value = getObject().eGet(e);
					if (value != null) {
						List<EObject> valueList;
						if (value instanceof List) {
							@SuppressWarnings("unchecked")
							List<EObject> tmp = (List<EObject>) value;
							valueList = tmp;
						} else
							valueList = Collections.singletonList((EObject) value);

						for (EObject obj : valueList) {
							BlockMapping bmm = editor.getMappingModel().getBlockMapping(obj.eClass());
							if (bmm != null) {
								BlockRepresentation blockRepresentation = editor.getModelInstance()
										.getOrCreateAndRegisterBlockRepresentation(obj, bmm);
								add(blockRepresentation, bmm);
							}
						}
					}
				}
			}
		} finally {
			preventRecursion = false;
		}

		myAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (msg.getEventType() == Notification.MOVE)
					return;// Ignore MOVE because work areas have no order.

				if (!preventRecursion && msg.getFeature() instanceof EReference) {
					if (editor.getMappingModel().getWorkAreaContainment(getObject().eClass(),
							(EReference) msg.getFeature()) != null) {
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
											if (rem == -1)
												new Throwable("Warning: The object " + msg.getOldValue()
														+ " could not be found in the work area...").printStackTrace();
										}
									}
								}
							} else {
								if (msg.getNewValue() instanceof EObject) {
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
								} else {
									System.err.println("Warning: Weird message at "
											+ EObjectWorkArea.class.getSimpleName() + ": \"" + msg + "\"\n" + "("
											+ msg.getNewValue() + " instanceof EObject == "
											+ (msg.getNewValue() instanceof EObject) + ")");
								}
							}
						} finally {
							preventRecursion = false;
						}
					}
				}
			}
		};
		getObject().eAdapters().add(myAdapter);
	}

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
					EClass type = obj.eClass();
					WorkAreaContainment containment = editor.getMappingModel()
							.getWorkAreaContainment(getObject().eClass(), type);
					if (containment == null) {
						new Throwable("Warning: " + blk + " cannot be removed from " + this
								+ " because there exists no containment matching " + MMUtil.fullyQualifiedName(type)
								+ " in the mapping model.").printStackTrace();
					} else {
						// Note: the mapping model will have verified that this
						// is valid.
						EReference ref = (EReference) getObject().eClass()
								.getEStructuralFeature(containment.getContainmentReference());
						if (ref.isMany()) {
							@SuppressWarnings("unchecked")
							List<EObject> remList = (List<EObject>) getObject().eGet(ref);
							boolean rem = remList.remove(obj);
							if (!rem)
								System.err.println("Warning: " + obj + " was not found in " + ref.getName() + " on "
										+ getObject());
						} else {
							if (getObject().eGet(ref) == obj)
								getObject().eSet(ref, null);
							else
								System.err.println("Warning: " + obj + " was not the value of " + ref.getName() + " on "
										+ getObject());
						}
					}
				} else {
					if (newChild instanceof EMFPointerBlock)
						throw new IllegalArgumentException();

					EObject obj = ((EObjectBlock) newChild).getObject();
					EClass type = obj.eClass();
					WorkAreaContainment containment = editor.getMappingModel()
							.getWorkAreaContainment(getObject().eClass(), type);
					if (containment == null)
						throw new IllegalArgumentException(getObject().eClass().getName()
								+ " has no containment matching the type " + type.getName());
					// Note: the mapping model will have verified that this is
					// valid.
					EReference ref = (EReference) getObject().eClass()
							.getEStructuralFeature(containment.getContainmentReference());
					if (ref.isMany()) {
						@SuppressWarnings("unchecked")
						List<EObject> addList = (List<EObject>) getObject().eGet(ref);
						Assert.isTrue(addList.add(obj));
					} else {
						Object oldValue = getObject().eGet(ref);
						if (oldValue != null) {
							assert oldValue instanceof EObject;
							int rem = removeObject((EObject) oldValue);
							if (rem == -1)
								new Throwable("Warning: the old value of " + ref.getName() + " on " + getObject()
										+ " was " + oldValue
										+ ", but there was no block representing this object in the work area!")
												.printStackTrace();
						}
						getObject().eSet(ref, obj);
					}
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			} finally {
				preventRecursion = false;
			}
		}
	}

	@Override
	protected FXWorkArea createDefaultUI() {
		return new FXWorkArea(this, mappingModel.getDefaultColor());
	}

	@Override
	public BlockDropAction accepts(WBlock b) {
		return super.accepts(b);// TODO
	}

	@Override
	protected void dispose2() {
		super.dispose2();
		labelProvider.dispose();
		getObject().eAdapters().remove(myAdapter);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		if (!isDestroyed())// This can happen between dispose and
							// Platform.runLater.
		{
			if (!editor.getModelInstance().getInstanceResource().getContents().contains(getObject())) {
				// Note: This object is eagerly disposed by the work area
				// manager(no reuse, no pointers), so this shouldn't happen.
				new Throwable("Warning: " + this
						+ " no longer exists due to an action, but the work area manager should have disposed it!")
								.printStackTrace();
				dispose();
			}
		}
	}

	@Override
	public Node getDecoratedUI() {
		if (decoratedUI == null) {
			ArrayList<Node[]> forms = new ArrayList<>();
			ArrayList<int[]> layout = new ArrayList<>();
			int maxWidth = 0;
			for (EAttribute e : getObject().eClass().getEAllAttributes()) {
				if (e.isChangeable()) {
					WorkAreaFormMapping fmm = editor.getMappingModel().getWorkAreaFormMapping(getObject().eClass(), e);
					if (fmm != null) {
						AttributeFormFactory formFactory = DefaultAttributeFormFactory
								.instanceOrDefault(fmm.getAttributeFormFactoryClass());
						Node[] nodes = formFactory.createForm(editor.getEditingDomain(), getObject(), e,
								destructionListeners);
						maxWidth = Math.max(nodes.length, maxWidth);
						forms.add(nodes);
						layout.add(new int[] { fmm.getGridX(), fmm.getGridY(), Math.max(1, fmm.getGridWidth()),
								Math.max(1, fmm.getGridHeight()) });
					}
				}
			}
			if (maxWidth > 0) {
				GridPane gp = new GridPane();

				int totalWidth = 0;
				int currentX = 0, currentY = 0;
				for (int i = 0; i < forms.size(); i++) {
					// Note: There is absolutely no protection from overlap in
					// these computations.
					Node[] f = forms.get(i);
					int[] l = layout.get(i);

					if (l[0] < 0)
						currentX++;
					else
						currentX = l[0];
					if (l[1] < 0)
						currentY++;
					else
						currentY = l[1];

					totalWidth = Math.max((1 + currentX) * maxWidth * l[2], totalWidth);

					for (int j = 0; j < f.length; j++) {
						int gridX = currentX * maxWidth + j * l[2];
						int gridY = currentY;
						int gridWidth;
						if (j == f.length - 1) {
							gridWidth = l[2] * (1 + maxWidth - f.length);// Extra
																			// space
																			// goes
																			// to
																			// last
																			// element
																			// in
																			// each
																			// metacell.
							GridPane.setFillWidth(f[j], true);
							GridPane.setHgrow(f[j], Priority.ALWAYS);
						} else
							gridWidth = l[2];
						int gridHeight = l[3];
						gp.add(f[j], gridX, gridY, gridWidth, gridHeight);
					}
				}

				if (totalWidth > 0) {
					ScrollPane scp = new ScrollPane(gp);
					scp.setFitToWidth(true);
					scp.setVbarPolicy(ScrollBarPolicy.NEVER);
					SplitPane.setResizableWithParent(scp, false);
					VBox.setVgrow(scp, Priority.NEVER);
					VBox.setVgrow(super.getUI(), Priority.ALWAYS);
					VBox sp = new VBox(scp, super.getUI());
					Rectangle clipRect = new Rectangle();
					clipRect.widthProperty().bind(super.getUI().widthProperty());
					clipRect.heightProperty().bind(super.getUI().heightProperty());
					super.getUI().setClip(clipRect);
					decoratedUI = sp;
				}
			}

			if (decoratedUI == null)
				decoratedUI = super.getUI();
		}
		return decoratedUI;
	}
}
