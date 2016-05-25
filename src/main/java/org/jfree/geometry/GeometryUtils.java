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

	public static Line2D newLine(double startX, double startY, double endX, double endY) {
		return new Line2D(startX, startY, endX, endY);
	}

	public static Line2D emptyLine() {
		return new Line2D(0, 0, 0, 0);
	}

	public static void strokeLine(GraphicsContext context, Line2D line) {
		context.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}

	public static void fillRectangle(GraphicsContext context, Rectangle2D rectangle) {
		context.fillRect(rectangle.getMinX(), rectangle.getMinY(), rectangle.getWidth(), rectangle.getHeight());
	}
}
