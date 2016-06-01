package org.jfree.geometry;

import javafx.geometry.Rectangle2D;

public class Line2D {
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	Line2D(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}

	public Rectangle2D getBounds2D() {
		double x, y, w, h;
		if (x1 < x2) {
			x = x1;
			w = x2 - x1;
		} else {
			x = x2;
			w = x1 - x2;
		}
		if (y1 < y2) {
			y = y1;
			h = y2 - y1;
		} else {
			y = y2;
			h = y1 - y2;
		}
		return new Rectangle2D(x, y, w, h);
	}

	public boolean isEmpty() {
		double width = x2 - x1;
		double height = y2 - y1;
		boolean empty = width == 0 && height == 0;
		return empty;
	}

}
