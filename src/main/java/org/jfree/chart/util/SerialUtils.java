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
 * ----------------
 * SerialUtils.java
 * ----------------
 * (C) Copyright 2000-2014, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arik Levin;
 *
 * Changes
 * -------
 * 25-Mar-2003 : Version 1 (DG);
 * 18-Sep-2003 : Added capability to serialize GradientPaint (DG);
 * 26-Apr-2004 : Added read/writePoint2D() methods (DG);
 * 22-Feb-2005 : Added support for Arc2D - see patch 1147035 by Arik Levin (DG);
 * 29-Jul-2005 : Added support for AttributedString (DG);
 * 10-Oct-2011 : Added support for AlphaComposite instances (MH);
 *
 */

package org.jfree.chart.util;

//import java.awt.AlphaComposite;
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Composite;
//import java.awt.GradientPaint;
//import java.awt.Paint;
//import java.awt.Shape;
//import java.awt.Stroke;
//import java.awt.geom.Arc2D;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.GeneralPath;
//import java.awt.geom.Line2D;
//import java.awt.geom.PathIterator;
//import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.drawable.StrokeProperties;

import com.sun.javafx.geom.Arc2D;
import com.sun.javafx.geom.Ellipse2D;
import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.Path2D;
import com.sun.javafx.geom.PathIterator;
import com.sun.javafx.geom.RectangularShape;
import com.sun.javafx.geom.RoundRectangle2D;
import com.sun.javafx.geom.Shape;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * A class containing useful utility methods relating to serialization.
 */
public class SerialUtils {

	/**
	 * Private constructor prevents object creation.
	 */
	private SerialUtils() {
	}

	/**
	 * Returns {@code true} if a class implements {@code Serializable} and
	 * {@code false} otherwise.
	 *
	 * @param c
	 *            the class.
	 *
	 * @return A boolean.
	 */
	public static boolean isSerializable(Class c) {
		return (Serializable.class.isAssignableFrom(c));
	}

	/**
	 * Reads a {@code Paint} object that has been serialised by the
	 * {@link SerialUtils#writePaint(Paint, ObjectOutputStream)} method.
	 *
	 * @param stream
	 *            the input stream ({@code null} not permitted).
	 *
	 * @return The paint object (possibly {@code null}).
	 *
	 * @throws IOException
	 *             if there is an I/O problem.
	 * @throws ClassNotFoundException
	 *             if there is a problem loading a class.
	 */
	public static Paint readPaint(ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		Paint result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			Class c = (Class) stream.readObject();
			if (isSerializable(c)) {
				result = (Paint) stream.readObject();
			} else if (c.equals(Color.class)) {
				double red = stream.readDouble();
				double green = stream.readDouble();
				double blue = stream.readDouble();
				double opacity = stream.readDouble();
				result = new Color(red, green, blue, opacity);

				// JAVAFX paint, serialization
				// } else if (c.equals(GradientPaint.class)) {
				// float x1 = stream.readFloat();
				// float y1 = stream.readFloat();
				// Color c1 = (Color) stream.readObject();
				// float x2 = stream.readFloat();
				// float y2 = stream.readFloat();
				// Color c2 = (Color) stream.readObject();
				// boolean isCyclic = stream.readBoolean();
				// result = new GradientPaint(x1, y1, c1, x2, y2, c2, isCyclic);
			}
		}
		return result;

	}

	/**
	 * Serialises a {@code Paint} object.
	 *
	 * @param paint
	 *            the paint object ({@code null} permitted).
	 * @param stream
	 *            the output stream ({@code null} not permitted).
	 *
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	public static void writePaint(Paint paint, ObjectOutputStream stream)
			throws IOException {

		ParamChecks.nullNotPermitted(stream, "stream");
		if (paint != null) {
			stream.writeBoolean(false);
			stream.writeObject(paint.getClass());
			if (paint instanceof Color) {
				Color color = (Color) paint;
				stream.writeDouble(color.getRed());
				stream.writeDouble(color.getGreen());
				stream.writeDouble(color.getBlue());
				stream.writeDouble(color.getOpacity());
			} else {
				new IllegalStateException("JAVAFX - to be done").printStackTrace();
				// JAVAFX paint, serialization
				// if (paint instanceof Serializable) {
				// stream.writeObject(paint);
				// } else if (paint instanceof GradientPaint) {
				// GradientPaint gp = (GradientPaint) paint;
				// stream.writeFloat((float) gp.getPoint1().getX());
				// stream.writeFloat((float) gp.getPoint1().getY());
				// stream.writeObject(gp.getColor1());
				// stream.writeFloat((float) gp.getPoint2().getX());
				// stream.writeFloat((float) gp.getPoint2().getY());
				// stream.writeObject(gp.getColor2());
				// stream.writeBoolean(gp.isCyclic());
				// }
			}
		} else {
			stream.writeBoolean(true);
		}
	}

	public static Font readFont(ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		Font result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			String family = (String) stream.readObject();
			FontWeight weight = readFontWeight(stream);
			FontPosture posture = readFontPosture(stream);
			double size = stream.readDouble();

			result = Font.font(family, weight, posture, size);
		}
		return result;
	}

	private static FontWeight readFontWeight(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		FontWeight weight = null;
		boolean fontWeightIsNull = stream.readBoolean();
		if (!fontWeightIsNull) {
			weight = (FontWeight) stream.readObject();
		}
		return weight;
	}

	private static FontPosture readFontPosture(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		FontPosture posture = null;
		boolean fontPostureIsNull = stream.readBoolean();
		if (!fontPostureIsNull) {
			posture = (FontPosture) stream.readObject();
		}
		return posture;
	}

	public static void writeFont(Font font, ObjectOutputStream stream)
			throws IOException {

		ParamChecks.nullNotPermitted(stream, "stream");
		if (font != null) {
			stream.writeBoolean(false);
			stream.writeObject(font.getFamily());

			String[] fontStyles = font.getStyle().split(" ");
			writeFontWeight(fontStyles, stream);
			writeFontPosture(fontStyles, stream);

			stream.writeDouble(font.getSize());

		} else {
			stream.writeBoolean(true);
		}
	}

	private static void writeFontWeight(String[] fontStyles, ObjectOutputStream stream) throws IOException {
		FontWeight weight = null;
		for (String style : fontStyles)
		{
			weight = FontWeight.findByName(style.toLowerCase());
			if (weight != null)
			{
				break;
			}
		}
		if (weight != null) {
			stream.writeBoolean(false);
			stream.writeObject(weight);
		} else {
			stream.writeBoolean(true);
		}
	}

	private static void writeFontPosture(String[] fontStyles, ObjectOutputStream stream) throws IOException {

		FontPosture posture = null;
		for (String style : fontStyles)
		{
			posture = FontPosture.findByName(style.toLowerCase());
			if (posture != null)
			{
				break;
			}
		}
		if (posture != null) {
			stream.writeBoolean(false);
			stream.writeObject(posture);
		} else {
			stream.writeBoolean(true);
		}
	}

	public static void writeFontMap(Map<? extends Serializable, Font> fontMap, ObjectOutputStream stream)
			throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		if (fontMap != null) {
			stream.writeBoolean(false);
			stream.writeInt(fontMap.size());
			for (Entry<? extends Serializable, Font> entry : fontMap.entrySet()) {
				stream.writeObject(entry.getKey());
				writeFont(entry.getValue(), stream);
			}
		} else {
			stream.writeBoolean(true);
		}
	}

	public static <T extends Serializable> Map<T, Font> readFontMap(
			final ObjectInputStream stream, final Class<T> keyType)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		Map<T, Font> result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			result = new HashMap<>();
			int size = stream.readInt();
			for (int i = 0; i < size; i++) {
				T key = (T) stream.readObject();
				Font font = readFont(stream);
				result.put(key, font);
			}
		}
		return result;

	}

	/**
	 * Reads a {@code Stroke} object that has been serialised by the
	 * {@link SerialUtils#writeStroke(Stroke, ObjectOutputStream)} method.
	 *
	 * @param stream
	 *            the input stream ({@code null} not permitted).
	 *
	 * @return The stroke object (possibly {@code null}).
	 *
	 * @throws IOException
	 *             if there is an I/O problem.
	 * @throws ClassNotFoundException
	 *             if there is a problem loading a class.
	 */
	public static StrokeProperties readStroke(final ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		StrokeProperties result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			result = (StrokeProperties) stream.readObject();
		}
		return result;
	}

	/**
	 * Serialises a {@code Stroke} object. This code handles the
	 * {@code BasicStroke} class which is the only {@code Stroke} implementation
	 * provided by the JDK (and isn't directly {@code Serializable}).
	 *
	 * @param stroke
	 *            the stroke object ({@code null} permitted).
	 * @param stream
	 *            the output stream ({@code null} not permitted).
	 *
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	public static void writeStroke(StrokeProperties stroke, ObjectOutputStream stream)
			throws IOException {

		ParamChecks.nullNotPermitted(stream, "stream");
		if (stroke != null) {
			stream.writeBoolean(false);
			stream.writeObject(stroke);
		} else {
			stream.writeBoolean(true);
		}
	}

	// JAVAFX serialization
	// /**
	// * Reads a {@code Composite} object that has been serialised by the
	// * {@link SerialUtils#writeComposite(Composite, ObjectOutputStream)}
	// method.
	// *
	// * @param stream
	// * the input stream ({@code null} not permitted).
	// *
	// * @return The composite object (possibly {@code null}).
	// *
	// * @throws IOException
	// * if there is an I/O problem.
	// * @throws ClassNotFoundException
	// * if there is a problem loading a class.
	// *
	// * @since 1.0.17
	// */
	// public static Composite readComposite(ObjectInputStream stream)
	// throws IOException, ClassNotFoundException {
	// ParamChecks.nullNotPermitted(stream, "stream");
	// Composite result = null;
	// boolean isNull = stream.readBoolean();
	// if (!isNull) {
	// Class c = (Class) stream.readObject();
	// if (isSerializable(c)) {
	// result = (Composite) stream.readObject();
	// } else if (c.equals(AlphaComposite.class)) {
	// int rule = stream.readInt();
	// float alpha = stream.readFloat();
	// result = AlphaComposite.getInstance(rule, alpha);
	// }
	// }
	// return result;
	//
	// }
	//
	// /**
	// * Serialises a {@code Composite} object.
	// *
	// * @param composite
	// * the composite object ({@code null} permitted).
	// * @param stream
	// * the output stream ({@code null} not permitted).
	// *
	// * @throws IOException
	// * if there is an I/O error.
	// *
	// * @since 1.0.17
	// */
	// public static void writeComposite(Composite composite,
	// ObjectOutputStream stream) throws IOException {
	//
	// ParamChecks.nullNotPermitted(stream, "stream");
	// if (composite != null) {
	// stream.writeBoolean(false);
	// stream.writeObject(composite.getClass());
	// if (composite instanceof Serializable) {
	// stream.writeObject(composite);
	// } else if (composite instanceof AlphaComposite) {
	// AlphaComposite ac = (AlphaComposite) composite;
	// stream.writeInt(ac.getRule());
	// stream.writeFloat(ac.getAlpha());
	// }
	// } else {
	// stream.writeBoolean(true);
	// }
	// }
	//

	public static Rectangle2D readRectangle(ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		boolean isNull = stream.readBoolean();
		if (isNull) {
			return null;
		}
		Class c = (Class) stream.readObject();
		if (!c.equals(Rectangle2D.class)) {
			throw new IllegalStateException("serialized object should be instance of Rectangle2D, but is "
					+ c.getName());
		}
		double x = stream.readDouble();
		double y = stream.readDouble();
		double w = stream.readDouble();
		double h = stream.readDouble();
		return new Rectangle2D(x, y, w, h);
	}

	public static void writeRectangle(Rectangle2D rectangle, ObjectOutputStream stream) throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		if (rectangle != null) {
			stream.writeBoolean(false);

			stream.writeObject(Rectangle2D.class);
			stream.writeDouble(rectangle.getMinX());
			stream.writeDouble(rectangle.getMinY());
			stream.writeDouble(rectangle.getWidth());
			stream.writeDouble(rectangle.getHeight());
		} else {
			stream.writeBoolean(true);
		}
	}

	/**
	 * Reads a {@code Shape} object that has been serialised by the
	 * {@link #writeShape(Shape, ObjectOutputStream)} method.
	 *
	 * @param stream
	 *            the input stream ({@code null} not permitted).
	 *
	 * @return The shape object (possibly {@code null}).
	 *
	 * @throws IOException
	 *             if there is an I/O problem.
	 * @throws ClassNotFoundException
	 *             if there is a problem loading a class.
	 */
	public static Shape readShape(ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		Shape result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			Class c = (Class) stream.readObject();
			if (c.equals(Line2D.class)) {
				double x1 = stream.readDouble();
				double y1 = stream.readDouble();
				double x2 = stream.readDouble();
				double y2 = stream.readDouble();
				result = new Line2D((float) x1, (float) y1, (float) x2, (float) y2);
			} else if (c.equals(Ellipse2D.class)) {
				double x = stream.readDouble();
				double y = stream.readDouble();
				double w = stream.readDouble();
				double h = stream.readDouble();
				result = new Ellipse2D((float) x, (float) y, (float) w, (float) h);
			} else if (c.equals(Arc2D.class)) {
				double x = stream.readDouble();
				double y = stream.readDouble();
				double w = stream.readDouble();
				double h = stream.readDouble();
				double as = stream.readDouble(); // Angle Start
				double ae = stream.readDouble(); // Angle Extent
				int at = stream.readInt(); // Arc type
				result = new Arc2D((float) x, (float) y, (float) w, (float) h, (float) as, (float) ae, at);
			} else if (c.equals(RoundRectangle2D.class)) {
				double x = stream.readDouble();
				double y = stream.readDouble();
				double w = stream.readDouble();
				double h = stream.readDouble();
				double arcw = stream.readDouble(); // Arc Width
				double arch = stream.readDouble(); // Arc Height
				result = new RoundRectangle2D(
						(float) x, (float) y, (float) w, (float) h, (float) arcw, (float) arch);
			} else if (c.equals(Path2D.class)) {
				Path2D gp = new Path2D();
				float[] args = new float[6];
				boolean hasNext = stream.readBoolean();
				while (!hasNext) {
					int type = stream.readInt();
					for (int i = 0; i < 6; i++) {
						args[i] = stream.readFloat();
					}
					switch (type) {
					case PathIterator.SEG_MOVETO:
						gp.moveTo(args[0], args[1]);
						break;
					case PathIterator.SEG_LINETO:
						gp.lineTo(args[0], args[1]);
						break;
					case PathIterator.SEG_CUBICTO:
						gp.curveTo(args[0], args[1], args[2],
								args[3], args[4], args[5]);
						break;
					case PathIterator.SEG_QUADTO:
						gp.quadTo(args[0], args[1], args[2], args[3]);
						break;
					case PathIterator.SEG_CLOSE:
						gp.closePath();
						break;
					default:
						throw new RuntimeException(
								"JFreeChart - No path exists");
					}
					gp.setWindingRule(stream.readInt());
					hasNext = stream.readBoolean();
				}
				result = gp;
			} else {
				result = (Shape) stream.readObject();
			}
		}
		return result;
	}

	/**
	 * Serialises a {@code Shape} object.
	 *
	 * @param shape
	 *            the shape object ({@code null} permitted).
	 * @param stream
	 *            the output stream ({@code null} not permitted).
	 *
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	public static void writeShape(Shape shape, ObjectOutputStream stream)
			throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		if (shape != null) {
			stream.writeBoolean(false);
			if (shape instanceof Line2D) {
				Line2D line = (Line2D) shape;
				stream.writeObject(Line2D.class);
				stream.writeDouble(line.x1);
				stream.writeDouble(line.y1);
				stream.writeDouble(line.x2);
				stream.writeDouble(line.y2);

			} else if (shape instanceof Ellipse2D) {
				Ellipse2D ellipse = (Ellipse2D) shape;
				stream.writeObject(Ellipse2D.class);
				writeRectangularShapeProperties(stream, ellipse);
			} else if (shape instanceof Arc2D) {
				Arc2D arc = (Arc2D) shape;
				stream.writeObject(Arc2D.class);
				writeRectangularShapeProperties(stream, arc);
				stream.writeDouble(arc.start);
				stream.writeDouble(arc.extent);
				stream.writeInt(arc.getArcType());
			} else if (shape instanceof RoundRectangle2D) {
				RoundRectangle2D roundRectangle = (RoundRectangle2D) shape;
				stream.writeObject(RoundRectangle2D.class);
				writeRectangularShapeProperties(stream, roundRectangle);
				stream.writeDouble(roundRectangle.arcWidth);
				stream.writeDouble(roundRectangle.arcHeight);
			} else if (shape instanceof Path2D) {
				stream.writeObject(Path2D.class);
				PathIterator pi = shape.getPathIterator(null);
				float[] args = new float[6];
				stream.writeBoolean(pi.isDone());
				while (!pi.isDone()) {
					int type = pi.currentSegment(args);
					stream.writeInt(type);
					// TODO: could write this to only stream the values
					// required for the segment type
					for (int i = 0; i < 6; i++) {
						stream.writeFloat(args[i]);
					}
					stream.writeInt(pi.getWindingRule());
					pi.next();
					stream.writeBoolean(pi.isDone());
				}
			} else {
				stream.writeObject(shape.getClass());
				stream.writeObject(shape);
			}
		} else {
			stream.writeBoolean(true);
		}
	}

	private static void writeRectangularShapeProperties(ObjectOutputStream stream, RectangularShape ellipse)
			throws IOException {
		stream.writeDouble(ellipse.getX());
		stream.writeDouble(ellipse.getY());
		stream.writeDouble(ellipse.getWidth());
		stream.writeDouble(ellipse.getHeight());
	}

	/**
	 * Reads a {@code Point2D} object that has been serialised by the
	 * {@link #writePoint2D(Point2D, ObjectOutputStream)} method.
	 *
	 * @param stream
	 *            the input stream ({@code null} not permitted).
	 *
	 * @return The point object (possibly {@code null}).
	 *
	 * @throws IOException
	 *             if there is an I/O problem.
	 */
	public static Point2D readPoint2D(ObjectInputStream stream)
			throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		Point2D result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			double x = stream.readDouble();
			double y = stream.readDouble();
			result = new Point2D(x, y);
		}
		return result;
	}

	/**
	 * Serialises a {@code Point2D} object.
	 *
	 * @param p
	 *            the point object ({@code null} permitted).
	 * @param stream
	 *            the output stream ({@code null} not permitted).
	 *
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	public static void writePoint2D(Point2D p, ObjectOutputStream stream)
			throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		if (p != null) {
			stream.writeBoolean(false);
			stream.writeDouble(p.getX());
			stream.writeDouble(p.getY());
		} else {
			stream.writeBoolean(true);
		}
	}

	/**
	 * Reads a {@code AttributedString} object that has been serialised by the
	 * {@link SerialUtils#writeAttributedString(AttributedString, ObjectOutputStream)}
	 * method.
	 *
	 * @param stream
	 *            the input stream ({@code null} not permitted).
	 *
	 * @return The attributed string object (possibly {@code null}).
	 *
	 * @throws IOException
	 *             if there is an I/O problem.
	 * @throws ClassNotFoundException
	 *             if there is a problem loading a class.
	 */
	public static AttributedString readAttributedString(
			ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		ParamChecks.nullNotPermitted(stream, "stream");
		AttributedString result = null;
		boolean isNull = stream.readBoolean();
		if (!isNull) {
			// read string and attributes then create result
			String plainStr = (String) stream.readObject();
			result = new AttributedString(plainStr);
			char c = stream.readChar();
			int start = 0;
			while (c != CharacterIterator.DONE) {
				int limit = stream.readInt();
				Map<? extends AttributedCharacterIterator.Attribute, ?> atts = (Map<? extends AttributedCharacterIterator.Attribute,
						?>) stream.readObject();
				result.addAttributes(atts, start, limit);
				start = limit;
				c = stream.readChar();
			}
		}
		return result;
	}

	/**
	 * Serialises an {@code AttributedString} object.
	 *
	 * @param as
	 *            the attributed string object ({@code null} permitted).
	 * @param stream
	 *            the output stream ({@code null} not permitted).
	 *
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	public static void writeAttributedString(AttributedString as,
			ObjectOutputStream stream) throws IOException {
		ParamChecks.nullNotPermitted(stream, "stream");
		if (as != null) {
			stream.writeBoolean(false);
			AttributedCharacterIterator aci = as.getIterator();
			// build a plain string from aci
			// then write the string
			StringBuffer plainStr = new StringBuffer();
			char current = aci.first();
			while (current != CharacterIterator.DONE) {
				plainStr = plainStr.append(current);
				current = aci.next();
			}
			stream.writeObject(plainStr.toString());

			// then write the attributes and limits for each run
			current = aci.first();
			int begin = aci.getBeginIndex();
			while (current != CharacterIterator.DONE) {
				// write the current character - when the reader sees that this
				// is not CharacterIterator.DONE, it will know to read the
				// run limits and attributes
				stream.writeChar(current);

				// now write the limit, adjusted as if beginIndex is zero
				int limit = aci.getRunLimit();
				stream.writeInt(limit - begin);

				// now write the attribute set
				Map<AttributedCharacterIterator.Attribute, Object> atts = new HashMap<AttributedCharacterIterator.Attribute,
						Object>(aci.getAttributes());
				stream.writeObject(atts);
				current = aci.setIndex(limit);
			}
			// write a character that signals to the reader that all runs
			// are done...
			stream.writeChar(CharacterIterator.DONE);
		} else {
			// write a flag that indicates a null
			stream.writeBoolean(true);
		}
	}

	/**
	 * Reads a map of {@code Paint} instances that was previously written by the
	 * {@link #writePaintMap(java.util.Map, java.io.ObjectOutputStream)} method.
	 * 
	 * @param in
	 *            the input stream ({@code null} not permitted).
	 * 
	 * @return A map.
	 * @throws IOException
	 *             if there is an IO problem.
	 * @throws ClassNotFoundException
	 *             if there is a problem loading a class.
	 */
	public static Map<Integer, Paint> readPaintMap(ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		Map<Integer, Paint> result = new HashMap<Integer, Paint>();
		int keyCount = in.readInt();
		for (int i = 0; i < keyCount; i++) {
			Integer key = (Integer) in.readObject();
			Paint paint = SerialUtils.readPaint(in);
			result.put(key, paint);
		}
		return result;
	}

	/**
	 * Writes a map to the output stream.
	 * 
	 * @param map
	 *            the map.
	 * @param out
	 *            the output stream.
	 * @throws IOException
	 *             if there is an IO problem.
	 */
	public static void writePaintMap(Map<Integer, Paint> map,
			ObjectOutputStream out) throws IOException {
		out.writeInt(map.size());
		for (Integer key : map.keySet()) {
			out.writeObject(key);
			Paint paint = map.get(key);
			SerialUtils.writePaint(paint, out);
		}
	}
}
