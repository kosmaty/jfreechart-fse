package org.jfree.geometry;

import static org.jfree.geometry.GeometryUtils.fillRectangle;
import static org.jfree.geometry.GeometryUtils.strokeLine;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawableShapes {

	private static class Line implements DrawableShape {

		private final Line2D line;

		Line(Line2D line) {
			this.line = line;
		}

		@Override
		public void draw(GraphicsContext context) {
			strokeLine(context, line);
		}
	}

	private static class Rectangle implements DrawableShape {
		private final Rectangle2D rectangle;

		Rectangle(Rectangle2D rectangle) {
			this.rectangle = rectangle;
		}

		@Override
		public void draw(GraphicsContext context) {
			fillRectangle(context, rectangle);
		}

	}

	public static DrawableShape from(Line2D line) {
		return new Line(line);
	}

	public static DrawableShape from(Rectangle2D rectangle) {
		return new Rectangle(rectangle);
	}

}
