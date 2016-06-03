package no.hal.emfblocks.util.data.pfield;

public interface PFieldListener<T extends PFieldElement> {
    public void valueChanged(PFieldImmutable<? extends T> field, int index, T oldValue, T newValue);
}
