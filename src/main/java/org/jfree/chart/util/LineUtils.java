/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
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
 * --------------
 * LineUtils.java
 * --------------
 * (C) Copyright 2008, 2014, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Nov-2008 : Version 1 (DG);
 * 28-Feb-2014 : Added extendLine() (DG);
 *
 */

package org.jfree.chart.util;

import static org.jfree.geometry.GeometryUtils.emptyLine;
import static org.jfree.geometry.GeometryUtils.newLine;

import org.jfree.geometry.Line2D;

import javafx.geometry.Rectangle2D;

/**
 * Some utility methods for {@link Line2D} objects.
 *
 * @since 1.0.12
 */
public class LineUtils {

	/**
	 * The bitmask that indicates that a point lies to the left of this
	 * <code>Rectangle2D</code>.
	 * 
	 * @since 1.2
	 */
	public static final int OUT_LEFT = 1;

	/**
	 * The bitmask that indicates that a point lies above this
	 * <code>Rectangle2D</code>.
	 * 
	 * @since 1.2
	 */
	public static final int OUT_TOP = 2;

	/**
	 * The bitmask that indicates that a point lies to the right of this
	 * <code>Rectangle2D</code>.
	 * 
	 * @since 1.2
	 */
	public static final int OUT_RIGHT = 4;

	/**
	 * The bitmask that indicates that a point lies below this
	 * <code>Rectangle2D</code>.
	 * 
	 * @since 1.2
	 */
	public static final int OUT_BOTTOM = 8;

	/**
	 * Clips the specified line to the given rectangle.
	 *
	 * @param line
	 *            the line (<code>null</code> not permitted).
	 * @param rect
	 *            the clipping rectangle (<code>null</code> not permitted).
	 *
	 * @return <code>true</code> if the clipped line is visible, and
	 *         <code>false</code> otherwise.
	 */
	public static Line2D clipLine(Line2D line, Rectangle2D rect) {
		double x1 = line.getX1();
		double y1 = line.getY1();
		double x2 = line.getX2();
		double y2 = line.getY2();

		double minX = rect.getMinX();
		double maxX = rect.getMaxX();
		double minY = rect.getMinY();
		double maxY = rect.getMaxY();

		int f1 = outcode(rect, x1, y1);
		int f2 = outcode(rect, x2, y2);

		while ((f1 | f2) != 0) {
			if ((f1 & f2) != 0) {
				return emptyLine();
			}
			double dx = (x2 - x1);
			double dy = (y2 - y1);
			// update (x1, y1), (x2, y2) and f1 and f2 using intersections
			// then recheck
			if (f1 != 0) {
				// first point is outside, so we update it against one of the
				// four sides then continue
				if ((f1 & OUT_LEFT) == OUT_LEFT
						&& dx != 0.0) {
					y1 = y1 + (minX - x1) * dy / dx;
					x1 = minX;
				}
				else if ((f1 & OUT_RIGHT) == OUT_RIGHT
						&& dx != 0.0) {
					y1 = y1 + (maxX - x1) * dy / dx;
					x1 = maxX;
				}
				else if ((f1 & OUT_BOTTOM) == OUT_BOTTOM
						&& dy != 0.0) {
					x1 = x1 + (maxY - y1) * dx / dy;
					y1 = maxY;
				}
				else if ((f1 & OUT_TOP) == OUT_TOP
						&& dy != 0.0) {
					x1 = x1 + (minY - y1) * dx / dy;
					y1 = minY;
				}
				f1 = outcode(rect, x1, y1);
			}
			else if (f2 != 0) {
				// second point is outside, so we update it against one of the
				// four sides then continue
				if ((f2 & OUT_LEFT) == OUT_LEFT
						&& dx != 0.0) {
					y2 = y2 + (minX - x2) * dy / dx;
					x2 = minX;
				}
				else if ((f2 & OUT_RIGHT) == OUT_RIGHT
						&& dx != 0.0) {
					y2 = y2 + (maxX - x2) * dy / dx;
					x2 = maxX;
				}
				else if ((f2 & OUT_BOTTOM) == OUT_BOTTOM
						&& dy != 0.0) {
					x2 = x2 + (maxY - y2) * dx / dy;
					y2 = maxY;
				}
				else if ((f2 & OUT_TOP) == OUT_TOP
						&& dy != 0.0) {
					x2 = x2 + (minY - y2) * dx / dy;
					y2 = minY;
				}
				f2 = outcode(rect, x2, y2);
			}
		}
		return newLine(x1, y1, x2, y2);
	}

	private static int outcode(Rectangle2D rect, double x, double y) {
		int out = 0;
		if (rect.getWidth() <= 0) {
			out |= OUT_LEFT | OUT_RIGHT;
		} else if (x < rect.getMinX()) {
			out |= OUT_LEFT;
		} else if (x > rect.getMinX() + rect.getWidth()) {
			out |= OUT_RIGHT;
		}
		if (rect.getHeight() <= 0) {
			out |= OUT_TOP | OUT_BOTTOM;
		} else if (y < rect.getMinY()) {
			out |= OUT_TOP;
		} else if (y > rect.getMinY() + rect.getHeight()) {
			out |= OUT_BOTTOM;
		}
		return out;
	}

	/**
	 * Creates a new line by extending an existing line.
	 *
	 * @param line
	 *            the line (<code>null</code> not permitted).
	 * @param startPercent
	 *            the amount to extend the line at the start point end.
	 * @param endPercent
	 *            the amount to extend the line at the end point end.
	 *
	 * @return A new line.
	 * 
	 * @since 1.0.18
	 */
	public static Line2D extendLine(Line2D line, double startPercent,
			double endPercent) {
		ParamChecks.nullNotPermitted(line, "line");
		double x1 = line.getX1();
		double x2 = line.getX2();
		double deltaX = x2 - x1;
		double y1 = line.getY1();
		double y2 = line.getY2();
		double deltaY = y2 - y1;
		x1 = x1 - (startPercent * deltaX);
		y1 = y1 - (startPercent * deltaY);
		x2 = x2 + (endPercent * deltaX);
		y2 = y2 + (endPercent * deltaY);
		return newLine(x1, y1, x2, y2);
	}

}
