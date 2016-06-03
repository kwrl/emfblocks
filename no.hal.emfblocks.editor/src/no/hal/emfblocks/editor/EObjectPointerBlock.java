package no.hal.emfblocks.editor;

import java.util.EventObject;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import no.hal.emfblocks.FutureWBlock;
import no.hal.emfblocks.WPointerBlockBase;
import no.hal.emfblocks.javafx.FXBlock;
import no.hal.emfblocks.javafx.SizedNode;
import no.hal.emfblocks.layout.PointerArrowMode;

/** Block that references an EObject represented by an {@link EObjectBlock}. */
public class EObjectPointerBlock extends WPointerBlockBase implements CommandStackListener, EMFPointerBlock {
	protected final EMFBlockEditor editor;

	public EObjectPointerBlock(EMFBlockEditor editor, FutureWBlock referenceBlock) {
		super(editor.showPointerArrows.get() == PointerArrowMode.ALWAYS, editor.getRoot(), referenceBlock);
		editor.showPointerArrows.addListener(referencer, (p, o, n) -> {
			this.showArrow.set(shouldShowArrow(n, getUI().isFocused()));
		});
		this.editor = editor;
		editor.getEditingDomain().getCommandStack().addCommandStackListener(this);
	}

	@Override
	public EObjectPointerBlock shallowCopy() {
		return new EObjectPointerBlock(editor, futureBlock);
	}

	@Override
	public FXBlock getUI() {
		if (pane == null) {
			FXBlock ui = super.getUI();
			ui.focusedProperty().addListener((p, o, n) -> {
				showArrow.set(shouldShowArrow(editor.showPointerArrows.get(), n));
			});
			return ui;
		} else
			return super.getUI();
	}

	private Boolean shouldShowArrow(PointerArrowMode mode, boolean focused) {
		return (mode == PointerArrowMode.ALWAYS) || (focused && mode == PointerArrowMode.WHEN_FOCUSED);
	}

	@Override
	public String getName() {
		return EMFBlockEditor.POINTER_SYMBOL
				+ ((EObjectBlock) getReferencedBlock()).labelProvider.labelTextProperty.get();
	}

	@Override
	protected void installContentPanes() {
		Label label = new Label(EMFBlockEditor.POINTER_SYMBOL);
		getCell(0, 0).installContentPane(new SizedNode(label, SizedNode.Size.AUTO_SIZE, true));

		if (isResolved())
			label.textProperty().bind(Bindings.concat(EMFBlockEditor.POINTER_SYMBOL,
					((EObjectBlock) getReferencedBlock()).labelProvider.labelTextProperty));
		referencedBlock().addListener(referencer, (p, o, n) -> {
			assert n != null;
			label.textProperty().bind(Bindings.concat(EMFBlockEditor.POINTER_SYMBOL,
					((EObjectBlock) n).labelProvider.labelTextProperty));
		});
	}

	@Override
	public void dispose2() {
		super.dispose2();
		Platform.runLater(() -> {
			editor.getEditingDomain().getCommandStack().removeCommandStackListener(this);
		});
	}

	@Override
	public void commandStackChanged(EventObject event) {
		if (!isDestroyed())// This can happen between dispose and
							// Platform.runLater.
		{
			if (!isPaletteBlock() && blockContainer().get() == null)// Note:
																	// Only need
																	// to
																	// dispose
																	// the
																	// topmost
																	// block
																	// since
																	// disposal
																	// is
																	// recursive.
			{
				System.out.println("INFO: " + this + " no longer exists due to an action.");
				dispose();
			}
		}
	}

	@Override
	public EObject dereference() {
		return ((EObjectBlock) getReferencedBlock()).getObject();
	}
}
