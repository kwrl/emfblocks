package no.hal.emfblocks.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import no.hal.emfblocks.hierarchy.ParameterizedType;
import no.hal.emfblocks.hierarchy.Type;
import no.hal.emfblocks.util.data.ReferenceSet;
import no.hal.emfblocks.util.data.Referencer;
import no.hal.emfblocks.util.data.property.Property;
import no.hal.emfblocks.util.data.property.PropertyImmutable;
import no.hal.emfblocks.util.data.property.PropertyListener;

public class FXParameterizedType extends SlackedGroup {
	private final ParameterizedType primaryType;
	private final SlackedGroup primaryTypeView;

	/** Garbage collection prevention */
	private final Referencer referencer = new ReferenceSet();

	private final Property<Type>[] params;
	private final SlackedGroup[] paramViews;

	private final boolean horizontal;
	private final double scale;
	private final short cHeight;

	private ObjectProperty<Color> colorProp;

	/**
	 * A pattern describing which levels of the hierarchy should be rendered.
	 */
	private Type pattern;
	private PropertyListener<Type> patternUpdater = null;
	private Property<Type> patternProperty = null;

	private FXParameterizedType(boolean root, ParameterizedType t, Type pattern, boolean tab, ObjectProperty<Color> c,
			ObjectProperty<Color> bgColor, boolean horizontal, double scale) {
		setPickOnBounds(false);
		this.primaryType = t;
		this.pattern = pattern;
		this.horizontal = horizontal;
		this.scale = scale;
		if (root) {
			colorProp = new ObjectPropertyBase<Color>() {
				@Override
				public Object getBean() {
					return FXParameterizedType.this;
				}

				@Override
				public String getName() {
					return "Level Color";
				}
			};
			ChangeListener<Color> colorUpdater = new ChangeListener<Color>() {
				@Override
				public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
					updateColor(newValue, tab);
				}
			};
			c.addListener(new WeakChangeListener<Color>(colorUpdater));
			referencer.addReference(colorUpdater);
			updateColor(c.get(), tab);
		} else {
			this.colorProp = c;
		}
		cHeight = t.rawType.getContour().getHeight();

		params = t.concreteTypes();
		paramViews = new SlackedGroup[params.length];
		for (int i = 0; i < params.length; i++) {
			final int index = i;
			rebuild(index);
			params[index].addListener(referencer, (prop, oldVal, newVal) -> {
				rebuild(index);
			});
		}

		primaryTypeView = createNode(t.getRawType(), pattern, tab, scale, c, bgColor);
		getChildren().add(primaryTypeView);
		primaryTypeView.relocate(getParameterMaxDim(), 0);

		if (!horizontal)
			getTransforms().add(new Rotate(90, 0, 0));
	}

	public FXParameterizedType(ParameterizedType t, boolean tab, ObjectProperty<Color> c, ObjectProperty<Color> bgColor,
			boolean horizontal, double scale) {
		this(true, t, t, tab, c, bgColor, horizontal, scale);
	}

	private void updateColor(Color srcColor, boolean tab) {
		Color dstColor;
		if (tab)
			dstColor = new Color(srcColor.getRed(), srcColor.getGreen(), srcColor.getBlue(), 0.5);
		else
			dstColor = new Color(srcColor.getRed() * 0.75, srcColor.getGreen() * 0.75, srcColor.getBlue() * 0.75, 1);
		colorProp.set(dstColor);
	}

	private void rebuild(int i) {
		if (paramViews[i] != null)
			getChildren().remove(paramViews[i]);
		double scale = 1.0 / params.length;

		paramViews[i] = createNode(params[i].get(), getPattern(i), true, scale, colorProp, null);
		getChildren().add(paramViews[i]);
		paramViews[i].relocate(0, i * cHeight * scale);
	}

	private SlackedGroup createNode(Type type, Type pattern, boolean tab, double scale, ObjectProperty<Color> color,
			ObjectProperty<Color> bgColor) {
		if (type instanceof ParameterizedType)
			return new FXParameterizedType(false, (ParameterizedType) type, pattern, tab, color, bgColor, true,
					scale * this.scale);
		else {
			FXColoredImageView r = new FXColoredImageView(
					tab ? type.getRawType().getContour().tabImage() : type.getRawType().getContour().slotImage(), color,
					bgColor, true, scale * this.scale);
			// SetVisible affects layout for some bloody reason
			r.opacityProperty().set(pattern == null ? 0 : 1);
			return r;
		}
	}

	public boolean containsWithSlack(Point2D localPoint, int slack) {
		return new Rectangle2D(getParameterMaxDim() - slack, -slack, primaryTypeView.getRealWidth() + 2 * slack,
				primaryTypeView.getRealHeight() + 2 * slack).contains(localPoint);
	}

	@Override
	public double getRealWidth() {
		if (!horizontal)
			return primaryTypeView.getRealHeight();
		else
			return primaryTypeView.getRealWidth() + getParameterMaxDim();
	}

	private double getParameterMaxDim() {
		double maxWidth = 0;
		for (int i = 0; i < paramViews.length; i++) {
			maxWidth = Math.max(maxWidth, paramViews[i].getRealWidth());
		}
		return maxWidth;
	}

	@Override
	public double getRealHeight() {
		if (horizontal)
			return primaryTypeView.getRealHeight();
		else
			return primaryTypeView.getRealWidth() + getParameterMaxDim();
	}

	/**
	 * Internal implementation of {@link #setPattern(Type)}, optionally erasing
	 * the binding.
	 */
	private void setPattern(Type pattern, boolean eraseBinding) {
		this.pattern = pattern;
		if (eraseBinding && patternProperty != null) {
			patternProperty.removeListener(null, patternUpdater);
			patternProperty = null;
		}

		primaryTypeView.setVisible(pattern != null);

		for (int i = 0; i < params.length; i++)
			rebuild(i);
	}

	/**
	 * Set the current rendering pattern and erase the binding, if it was set
	 * using {@link #setPattern(Property)}. <br/>
	 * <br/>
	 * The rendering pattern determines which type arguments are rendered: Only
	 * the type arguments that are present in both the pattern and this view's
	 * type are rendered. In particular:
	 * <ul>
	 * <li>If the pattern is null, then this type view is not rendered at all.
	 * </li>
	 * <li>If the pattern is equal to this view's type, then the entire type
	 * view is rendered.</li>
	 * </ul>
	 * If a block's tab is inserted into a slot whose type contains raw types,
	 * then the type arguments of the raw types should not be rendered on the
	 * tab. The tab view's pattern is then set to the slot's type, achieving the
	 * desired effect.
	 */
	public void setPattern(Type pattern) {
		setPattern(pattern, true);
	}

	/**
	 * Bind the rendering pattern to a property. The binding is undone if the
	 * pattern is explicitly set later.
	 * 
	 * @see #setPattern(Type)
	 */
	public void setPattern(Property<Type> patternProperty) {
		setPattern(patternProperty.get(), true);

		if (patternUpdater == null)
			patternUpdater = new PropertyListener<Type>() {
				@Override
				public void valueChanged(PropertyImmutable<? extends Type> property, Type oldValue, Type newValue) {
					setPattern(newValue, false);
				}
			};
		// This hurts my brain
		patternProperty.addListener(null, patternUpdater);
		this.patternProperty = patternProperty;
	}

	/**
	 * Returns the current rendering pattern
	 * 
	 * @see #setPattern(Type)
	 */
	private Type getPattern(int index) {
		if (pattern == null)
			return null;
		else if (pattern instanceof ParameterizedType) {
			// Get type argument on the pattern type with the same name as the
			// type parameter our type has at the given index
			Property<Type> p = ((ParameterizedType) pattern)
					.concreteType(primaryType.rawType.getParameters()[index].name);
			if (p == null)
				return null;
			else
				return p.get();
		} else
			return null;
	}

	/** Returns the parameterized type that this view represents */
	public ParameterizedType getPrimaryType() {
		return primaryType;
	}

	public Referencer getReferencer() {
		return referencer;
	}
}
