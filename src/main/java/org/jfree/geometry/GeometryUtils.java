package org.jfree.geometry;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class GeometryUtils {
	public static double getCenterX(Rectangle2D rectangle) {
		return ((rectangle.getMinX() + rectangle.getMaxX()) / 2);
	}

	public static double getCenterY(Rectangle2D rectangle) {
		return ((rectangle.getMinY() + rectangle.getMaxY()) / 2);
	}

	public static boolean isEmpty(Rectangle2D rectangle) {
		return rectangle.getWidth() <= 0.0 || rectangle.getHeight() <= 0.0;
	}

	public static Rectangle2D union(Rectangle2D r1, Rectangle2D r2) {
		double minX = Math.min(r1.getMinX(), r2.getMinX());
		double minY = Math.min(r1.getMinY(), r2.getMinY());
		double maxX = Math.max(r1.getMaxX(), r2.getMaxX());
		double maxY = Math.max(r1.getMaxY(), r2.getMaxY());
		return new Rectangle2D(minX, minY, maxX - minX, maxY - minY);

	}

	public static Rectangle2D newOrEmptyRectangle(double x, double y, double width, double height) {
		if (width < 0 || height < 0) {
			return Rectangle2D.EMPTY;
		}
		return new Rectangle2D(x, y, width, height);
	}

	public static Line2D newLine(double startX, double startY, double endX, double endY) {
		return new Line2D(startX, startY, endX, endY);
	}

	public static Line2D emptyLine() {

		return new Line2D(0, 0, 0, 0) {
			@Override
			public boolean isEmpty() {
				return true;
			}

			@Override
			public Rectangle2D getBounds2D() {
				return Rectangle2D.EMPTY;
			}
		};
	}

	public static void strokeLine(GraphicsContext context, Line2D line) {
		// To draw one-pixel horizontal or vertical line in JavaFX we need to
		// adjust x or y value
		if (isHorizontal(line)) {
			double y = ((int) line.getY1()) + 0.5;
			context.strokeLine(line.getX1(), y, line.getX2(), y);
		}
		else if (isVertical(line)) {
			double x = ((int) line.getX1()) + 0.5;
			context.strokeLine(x, line.getY1(), x, line.getY2());
		} else {
			context.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
		}
	}

	private static boolean isHorizontal(Line2D line) {
		return line.getY1() == line.getY2();
	}

	private static boolean isVertical(Line2D line) {
		return line.getX1() == line.getX2();
	}

	public static void fillRectangle(GraphicsContext context, Rectangle2D rectangle) {
		context.fillRect(rectangle.getMinX(), rectangle.getMinY(), rectangle.getWidth(), rectangle.getHeight());
	}

	public static void strokeRectangle(GraphicsContext context, Rectangle2D rectangle) {
		context.strokeRect(rectangle.getMinX(), rectangle.getMinY(), rectangle.getWidth(), rectangle.getHeight());
	}
}
