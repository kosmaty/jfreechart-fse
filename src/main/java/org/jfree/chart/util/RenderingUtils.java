package org.jfree.chart.util;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class RenderingUtils {

	public static GraphicsContext createGraphiscContext(double width, double height) {
		Canvas canvas = new Canvas(width, height);
		return canvas.getGraphicsContext2D();
	}

	public static Image toImage(GraphicsContext context) {
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage image = context.getCanvas().snapshot(params, null);
		return image;
	}
}
