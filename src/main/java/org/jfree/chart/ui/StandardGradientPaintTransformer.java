/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2012, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.]
 *
 * -------------------------------------
 * StandardGradientPaintTransformer.java
 * -------------------------------------
 * (C) Copyright 2003-2012, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: StandardGradientPaintTransformer.java,v 1.11 2007/04/03 13:55:13 mungady Exp $
 *
 * Changes
 * -------
 * 28-Oct-2003 : Version 1 (DG);
 * 19-Mar-2004 : Added equals() method (DG);
 * 17-Jun-2012 : Moved from JCommon to JFreeChart (DG);
 *
 */

package org.jfree.chart.ui;

import static org.jfree.chart.util.ShapeUtils.asRectangle2D;
import static org.jfree.geometry.GeometryUtils.getCenterX;
import static org.jfree.geometry.GeometryUtils.getCenterY;

import java.io.Serializable;

import org.jfree.chart.util.PublicCloneable;

import com.sun.javafx.geom.RectBounds;
import com.sun.javafx.geom.Shape;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

/**
 * Transforms a <code>GradientPaint</code> to range over the width of a target
 * shape. Instances of this class are immutable.
 */
public class StandardGradientPaintTransformer
		implements GradientPaintTransformer, Cloneable, PublicCloneable,
		Serializable {

	/** For serialization. */
	private static final long serialVersionUID = -8155025776964678320L;

	/** The transform type. */
	private GradientPaintTransformType type;

	/**
	 * Creates a new transformer with the type
	 * {@link GradientPaintTransformType#VERTICAL}.
	 */
	public StandardGradientPaintTransformer() {
		this(GradientPaintTransformType.VERTICAL);
	}

	/**
	 * Creates a new transformer with the specified type.
	 *
	 * @param type
	 *            the transform type (<code>null</code> not permitted).
	 */
	public StandardGradientPaintTransformer(
			final GradientPaintTransformType type) {
		if (type == null) {
			throw new IllegalArgumentException("Null 'type' argument.");
		}
		this.type = type;
	}

	/**
	 * Returns the type of transform.
	 *
	 * @return The type of transform (never <code>null</code>).
	 *
	 * @since 1.0.10
	 */
	public GradientPaintTransformType getType() {
		return this.type;
	}

	/**
	 * Transforms a <code>GradientPaint</code> instance to fit the specified
	 * <code>target</code> shape.
	 *
	 * @param paint
	 *            the original paint (<code>null</code> not permitted).
	 * @param target
	 *            the target shape (<code>null</code> not permitted).
	 *
	 * @return The transformed paint.
	 */
	@Override
	public LinearGradient transform(final LinearGradient paint,
			final Shape target) {

		final Rectangle2D bounds = asRectangle2D(target.getBounds());

		return transform(paint, bounds);
	}

	@Override
	public LinearGradient transform(LinearGradient paint, Rectangle2D bounds) {

		LinearGradient result = paint;

		if (this.type.equals(GradientPaintTransformType.VERTICAL)) {
			result = new LinearGradient(
					getCenterX(bounds), bounds.getMinY(), getCenterX(bounds), bounds.getMaxY(),
					/* proportional */true, CycleMethod.REPEAT, paint.getStops());
		}
		else if (this.type.equals(GradientPaintTransformType.HORIZONTAL)) {
			result = new LinearGradient(
					bounds.getMinX(), getCenterY(bounds), bounds.getMaxX(), getCenterY(bounds),
					/* proportional */true, CycleMethod.NO_CYCLE, paint.getStops());
		}
		else if (this.type.equals(
				GradientPaintTransformType.CENTER_HORIZONTAL)) {
			result = new LinearGradient(
					getCenterX(bounds), getCenterY(bounds), bounds.getMaxX(), getCenterY(bounds),
					/* proportional */true, CycleMethod.REPEAT, paint.getStops());

		}
		else if (this.type.equals(GradientPaintTransformType.CENTER_VERTICAL)) {
			result = new LinearGradient(
					getCenterX(bounds), bounds.getMinY(), getCenterX(bounds), getCenterY(bounds),
					/* proportional */true, CycleMethod.REPEAT, paint.getStops());
		}

		return result;
	}

	/**
	 * Tests this instance for equality with an arbitrary object.
	 *
	 * @param obj
	 *            the object (<code>null</code> permitted).
	 *
	 * @return A boolean.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof StandardGradientPaintTransformer)) {
			return false;
		}
		StandardGradientPaintTransformer that = (StandardGradientPaintTransformer) obj;
		if (this.type != that.type) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a clone of the transformer. Note that instances of this class are
	 * immutable, so cloning an instance isn't really necessary.
	 *
	 * @return A clone.
	 *
	 * @throws CloneNotSupportedException
	 *             not thrown by this class, but subclasses (if any) might.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Returns a hash code for this object.
	 *
	 * @return A hash code.
	 */
	@Override
	public int hashCode() {
		return (this.type != null ? this.type.hashCode() : 0);
	}

}
