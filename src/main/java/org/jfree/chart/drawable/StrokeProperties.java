package org.jfree.chart.drawable;

import java.util.Arrays;

import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class StrokeProperties {
	public static final double DEFAULT_LINE_WIDTH = 1.0;
	public static final StrokeLineCap DEFAULT_LINE_CAP = StrokeLineCap.SQUARE;
	public static final StrokeLineJoin DEFAULT_LINE_JOIN = StrokeLineJoin.MITER;
	public static final double DEFAULT_MITER_LIMIT = 10.0;
	public static final double[] DEFAULT_LINE_DASHES = null;
	public static final double DEFAULT_LINE_DASH_OFFSET = 0.0;

	private double lineWidth;
	private StrokeLineCap lineCap;
	private StrokeLineJoin lineJoin;
	private double miterLimit;
	private double[] lineDashes;
	private double lineDashOffset;

	public StrokeProperties(double lineWidth, StrokeLineCap lineCap, StrokeLineJoin lineJoin, double miterLimit,
			double[] lineDashes, double lineDashOffset) {
		if (lineWidth < 0.0f) {
			throw new IllegalArgumentException("lineWidth cannot be negative");
		}
		if (lineCap == null) {
			throw new NullPointerException("lineCap cannot be null");
		}
		if (lineJoin == null) {
			throw new NullPointerException("lineJoin cannot be null");
		}
		if (lineJoin == StrokeLineJoin.MITER && miterLimit < 1.0) {
			throw new IllegalArgumentException("miter limit cannot be less than 1.0");
		}

		if (lineDashes != null) {
			if (lineDashOffset < 0.0) {
				throw new IllegalArgumentException("dash offset cannot be negative");
			}
			boolean allzero = true;
			for (double dash : lineDashes) {
				if (dash > 0.0) {
					allzero = false;
				} else if (dash < 0.0) {
					throw new IllegalArgumentException("dash length cannot be negative");
				}
			}
			if (allzero) {
				throw new IllegalArgumentException("dash lengths all zero");
			}
		}

		this.lineWidth = lineWidth;
		this.lineCap = lineCap;
		this.lineJoin = lineJoin;
		this.miterLimit = miterLimit;
		this.lineDashes = lineDashes;
		this.lineDashOffset = lineDashOffset;
	}

	public StrokeProperties(double lineWidth, StrokeLineCap lineCap, StrokeLineJoin lineJoin, double miterLimit,
			double[] lineDashes) {
		this(lineWidth, lineCap, lineJoin, miterLimit, lineDashes, DEFAULT_LINE_DASH_OFFSET);
	}

	public StrokeProperties(double lineWidth, StrokeLineCap lineCap, StrokeLineJoin lineJoin, double miterLimit) {
		this(lineWidth, lineCap, lineJoin, miterLimit, DEFAULT_LINE_DASHES, DEFAULT_LINE_DASH_OFFSET);
	}

	public StrokeProperties(double lineWidth, StrokeLineCap lineCap, StrokeLineJoin lineJoin) {
		this(lineWidth, lineCap, lineJoin, DEFAULT_MITER_LIMIT, DEFAULT_LINE_DASHES, DEFAULT_LINE_DASH_OFFSET);
	}

	public StrokeProperties(double lineWidth, StrokeLineCap lineCap) {
		this(lineWidth, lineCap, DEFAULT_LINE_JOIN, DEFAULT_MITER_LIMIT, DEFAULT_LINE_DASHES, DEFAULT_LINE_DASH_OFFSET);
	}

	public StrokeProperties(double lineWidth) {
		this(lineWidth, DEFAULT_LINE_CAP, DEFAULT_LINE_JOIN, DEFAULT_MITER_LIMIT, DEFAULT_LINE_DASHES,
				DEFAULT_LINE_DASH_OFFSET);
	}

	public StrokeProperties() {
		this(DEFAULT_LINE_WIDTH, DEFAULT_LINE_CAP, DEFAULT_LINE_JOIN, DEFAULT_MITER_LIMIT, DEFAULT_LINE_DASHES,
				DEFAULT_LINE_DASH_OFFSET);
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public StrokeLineCap getLineCap() {
		return lineCap;
	}

	public StrokeLineJoin getLineJoin() {
		return lineJoin;
	}

	public double getMiterLimit() {
		return miterLimit;
	}

	public double[] getLineDashes() {
		return lineDashes;
	}

	public double getLineDashOffset() {
		return lineDashOffset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineCap == null) ? 0 : lineCap.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lineDashOffset);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(lineDashes);
		result = prime * result + ((lineJoin == null) ? 0 : lineJoin.hashCode());
		temp = Double.doubleToLongBits(lineWidth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(miterLimit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrokeProperties other = (StrokeProperties) obj;
		if (lineCap != other.lineCap)
			return false;
		if (Double.doubleToLongBits(lineDashOffset) != Double.doubleToLongBits(other.lineDashOffset))
			return false;
		if (!Arrays.equals(lineDashes, other.lineDashes))
			return false;
		if (lineJoin != other.lineJoin)
			return false;
		if (Double.doubleToLongBits(lineWidth) != Double.doubleToLongBits(other.lineWidth))
			return false;
		if (Double.doubleToLongBits(miterLimit) != Double.doubleToLongBits(other.miterLimit))
			return false;
		return true;
	}

}
