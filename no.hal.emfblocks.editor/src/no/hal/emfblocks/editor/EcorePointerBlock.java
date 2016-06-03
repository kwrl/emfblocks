package no.hal.emfblocks.editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import no.hal.emfblocks.WBlock;
import no.hal.emfblocks.WBlockBase;
import no.hal.emfblocks.WPointerBlock;
import no.hal.emfblocks.javafx.SizedNode;

/** Block that references an EObject that does not exist as a block. */
public class EcorePointerBlock extends WBlockBase implements EMFPointerBlock {
	protected final EMFBlockEditor editor;
	protected final EObject target;

	protected String labelText;

	public EcorePointerBlock(EMFBlockEditor editor, EObject target) {
		super(editor.getRoot(), target.eClass(), Color.LIGHTGRAY);
		this.editor = editor;
		this.target = target;
	}

	@Override
	public String getName() {
		return labelText;
	}

	@Override
	protected void installContentPanes() {
		IItemLabelProvider labelProvider = (IItemLabelProvider) editor.getAdapterFactory().adapt(target,
				IItemLabelProvider.class);
		if (labelProvider != null)
			labelText = labelProvider.getText(target);
		else
			labelText = target.eClass().getName();
		Label label = new Label(EMFBlockEditor.POINTER_SYMBOL + labelText);

		getCell(0, 0).installContentPane(new SizedNode(label, SizedNode.Size.AUTO_SIZE, true));
	}

	@Override
	public WPointerBlock makePointerBlock() {
		throw new IllegalArgumentException("Cannot make a pointer to an " + getClass().getSimpleName());
	}

	@Override
	public WBlock shallowCopy() throws UnsupportedOperationException {
		return new EcorePointerBlock(editor, target);
	}

	@Override
	public EObject dereference() {
		return target;
	}

}
