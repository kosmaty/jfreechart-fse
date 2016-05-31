/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2016, by Object Refinery Limited and Contributors.
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
 * ---------------------------------
 * AbstractCategoryItemRenderer.java
 * ---------------------------------
 * (C) Copyright 2002-2016, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Peter Kolb (patch 2497611);
 *
 * Changes:
 * --------
 * 29-May-2002 : Version 1 (DG);
 * 06-Jun-2002 : Added accessor methods for the tool tip generator (DG);
 * 11-Jun-2002 : Made constructors protected (DG);
 * 26-Jun-2002 : Added axis to initialise method (DG);
 * 05-Aug-2002 : Added urlGenerator member variable plus accessors (RA);
 * 22-Aug-2002 : Added categoriesPaint attribute, based on code submitted by
 *               Janet Banks.  This can be used when there is only one series,
 *               and you want each category item to have a different color (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 29-Oct-2002 : Fixed bug where background image for plot was not being
 *               drawn (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 26-Nov 2002 : Replaced the isStacked() method with getRangeType() (DG);
 * 09-Jan-2003 : Renamed grid-line methods (DG);
 * 17-Jan-2003 : Moved plot classes into separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 12-May-2003 : Modified to take into account the plot orientation (DG);
 * 12-Aug-2003 : Very minor javadoc corrections (DB)
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 05-Nov-2003 : Fixed marker rendering bug (833623) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 11-Feb-2004 : Modified labelling for markers (DG);
 * 12-Feb-2004 : Updated clone() method (DG);
 * 15-Apr-2004 : Created a new CategoryToolTipGenerator interface (DG);
 * 05-May-2004 : Fixed bug (948310) where interval markers extend outside axis
 *               range (DG);
 * 14-Jun-2004 : Fixed bug in drawRangeMarker() method - now uses 'paint' and
 *               'stroke' rather than 'outlinePaint' and 'outlineStroke' (DG);
 * 15-Jun-2004 : Interval markers can now use GradientPaint (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities
 *               --> TextUtilities (DG);
 * 01-Oct-2004 : Fixed bug 1029697, problem with label alignment in
 *               drawRangeMarker() method (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() (DG);
 * 21-Jan-2005 : Modified return type of calculateRangeMarkerTextAnchorPoint()
 *               method (DG);
 * 08-Mar-2005 : Fixed positioning of marker labels (DG);
 * 20-Apr-2005 : Added legend label, tooltip and URL generators (DG);
 * 01-Jun-2005 : Handle one dimension of the marker label adjustment
 *               automatically (DG);
 * 09-Jun-2005 : Added utility method for adding an item entity (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Mar-2006 : Updated getLegendItems() to check seriesVisibleInLegend
 *               flags (DG);
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 23-Oct-2006 : Draw outlines for interval markers (DG);
 * 24-Oct-2006 : Respect alpha setting in markers, as suggested by Sergei
 *               Ivanov in patch 1567843 (DG);
 * 30-Nov-2006 : Added a check for series visibility in the getLegendItem()
 *               method (DG);
 * 07-Dec-2006 : Fix for equals() method (DG);
 * 22-Feb-2007 : Added createState() method (DG);
 * 01-Mar-2007 : Fixed interval marker drawing (patch 1670686 thanks to
 *               Sergei Ivanov) (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change, and deprecated
 *               itemLabelGenerator, toolTipGenerator and itemURLGenerator
 *               override fields (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 17-Jun-2008 : Apply legend shape, font and paint attributes (DG);
 * 26-Jun-2008 : Added crosshair support (DG);
 * 25-Nov-2008 : Fixed bug in findRangeBounds() method (DG);
 * 14-Jan-2009 : Update initialise() to store visible series indices (PK);
 * 21-Jan-2009 : Added drawRangeLine() method (DG);
 * 27-Mar-2009 : Added new findRangeBounds() method to account for hidden
 *               series (DG);
 * 01-Apr-2009 : Added new addEntity() method (DG);
 * 09-Feb-2010 : Fixed bug 2947660 (DG);
 * 15-Jun-2012 : Removed JCommon dependencies (DG);
 * 10-Mar-2014 : Removed LegendItemCollection (DG);
 *
 */

package org.jfree.chart.renderer.category;

import static org.jfree.geometry.GeometryUtils.fillRectangle;
import static org.jfree.geometry.GeometryUtils.newLine;
import static org.jfree.geometry.GeometryUtils.strokeLine;

// 
// import java.awt.AlphaComposite;
// import java.awt.Composite;
// import java.awt.Font;
// import java.awt.GradientPaint;
// import java.awt.Graphics2D;
// import java.awt.Paint;
// import java.awt.RenderingHints;
// import java.awt.Shape;
// import java.awt.Stroke;
// import java.awt.geom.Ellipse2D;
// import java.awt.geom.Line2D;
// import java.awt.geom.Point2D;
// import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
// import org.jfree.chart.ui.GradientPaintTransformer;
import org.jfree.chart.ui.LengthAdjustmentType;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.ObjectUtils;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.chart.util.SortOrder;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryCrosshairState;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.text.TextUtilities;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.util.CloneUtils;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.geometry.Line2D;

import com.sun.javafx.geom.Ellipse2D;
import com.sun.javafx.geom.Shape;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 * An abstract base class that you can use to implement a new
 * {@link CategoryItemRenderer}. When you create a new
 * {@link CategoryItemRenderer} you are not required to extend this class, but
 * it makes the job easier.
 */
public abstract class AbstractCategoryItemRenderer extends AbstractRenderer
		implements CategoryItemRenderer, Cloneable, PublicCloneable,
		Serializable {

	/** For serialization. */
	private static final long serialVersionUID = 1247553218442497391L;

	/** The plot that the renderer is assigned to. */
	private CategoryPlot plot;

	/**
	 * Storage for item label generators by series (if there is no generator for
	 * a series, the defaultItemLabelGenerator will be used).
	 */
	private Map<Integer, CategoryItemLabelGenerator> itemLabelGeneratorMap;

	/** The default item label generator. */
	private CategoryItemLabelGenerator defaultItemLabelGenerator;

	/**
	 * Storage for tool tip generators by series (if there is no generator for a
	 * series, the defaultToolTipGenerator will be used).
	 */
	private Map<Integer, CategoryToolTipGenerator> toolTipGeneratorMap;

	/** The default tool tip generator. */
	private CategoryToolTipGenerator defaultToolTipGenerator;

	/**
	 * Storage for URL generators by series (if there is no generator for a
	 * series, the defaultURLGenerator will be used).
	 */
	private Map<Integer, CategoryURLGenerator> urlGeneratorMap;

	/** The default URL generator. */
	private CategoryURLGenerator defaultURLGenerator;

	/** The legend item label generator. */
	private CategorySeriesLabelGenerator legendItemLabelGenerator;

	/** The legend item tool tip generator. */
	private CategorySeriesLabelGenerator legendItemToolTipGenerator;

	/** The legend item URL generator. */
	private CategorySeriesLabelGenerator legendItemURLGenerator;

	/** The number of rows in the dataset (temporary record). */
	private transient int rowCount;

	/** The number of columns in the dataset (temporary record). */
	private transient int columnCount;

	/**
	 * Creates a new renderer with no tool tip generator and no URL generator.
	 * The defaults (no tool tip or URL generators) have been chosen to minimise
	 * the processing required to generate a default chart. If you require tool
	 * tips or URLs, then you can easily add the required generators.
	 */
	protected AbstractCategoryItemRenderer() {
		this.itemLabelGeneratorMap = new HashMap<Integer, CategoryItemLabelGenerator>();
		this.toolTipGeneratorMap = new HashMap<Integer, CategoryToolTipGenerator>();
		this.urlGeneratorMap = new HashMap<Integer, CategoryURLGenerator>();
		this.legendItemLabelGenerator = new StandardCategorySeriesLabelGenerator();
	}

	/**
	 * Returns the number of passes through the dataset required by the
	 * renderer. This method returns {@code 1}, subclasses should override if
	 * they need more passes.
	 *
	 * @return The pass count.
	 */
	@Override
	public int getPassCount() {
		return 1;
	}

	/**
	 * Returns the plot that the renderer has been assigned to (where
	 * {@code null} indicates that the renderer is not currently assigned to a
	 * plot).
	 *
	 * @return The plot (possibly {@code null}).
	 *
	 * @see #setPlot(CategoryPlot)
	 */
	@Override
	public CategoryPlot getPlot() {
		return this.plot;
	}

	/**
	 * Sets the plot that the renderer has been assigned to. This method is
	 * usually called by the {@link CategoryPlot}, in normal usage you shouldn't
	 * need to call this method directly.
	 *
	 * @param plot
	 *            the plot ({@code null} not permitted).
	 *
	 * @see #getPlot()
	 */
	@Override
	public void setPlot(CategoryPlot plot) {
		ParamChecks.nullNotPermitted(plot, "plot");
		this.plot = plot;
	}

	// ITEM LABEL GENERATOR

	/**
	 * Returns the item label generator for a data item. This implementation
	 * simply passes control to the {@link #getSeriesItemLabelGenerator(int)}
	 * method. If, for some reason, you want a different generator for
	 * individual items, you can override this method.
	 *
	 * @param row
	 *            the row index (zero based).
	 * @param column
	 *            the column index (zero based).
	 *
	 * @return The generator (possibly {@code null}).
	 */
	@Override
	public CategoryItemLabelGenerator getItemLabelGenerator(int row,
			int column) {
		return getSeriesItemLabelGenerator(row);
	}

	/**
	 * Returns the item label generator for a series.
	 *
	 * @param series
	 *            the series index (zero based).
	 *
	 * @return The generator (possibly {@code null}).
	 *
	 * @see #setSeriesItemLabelGenerator(int, CategoryItemLabelGenerator)
	 */
	@Override
	public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series)
	{
		CategoryItemLabelGenerator g = this.itemLabelGeneratorMap.get(series);
		if (g == null) {
			g = this.defaultItemLabelGenerator;
		}
		return g;
	}

	/**
	 * Sets the item label generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero based).
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #getSeriesItemLabelGenerator(int)
	 */
	@Override
	public void setSeriesItemLabelGenerator(int series,
			CategoryItemLabelGenerator generator) {
		setSeriesItemLabelGenerator(series, generator, true);
	}

	/**
	 * Sets the item label generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero based).
	 * @param generator
	 *            the generator ({@code null} permitted).
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getSeriesItemLabelGenerator(int)
	 */
	@Override
	public void setSeriesItemLabelGenerator(int series,
			CategoryItemLabelGenerator generator, boolean notify) {
		this.itemLabelGeneratorMap.put(series, generator);
		if (notify) {
			fireChangeEvent();
		}
	}

	/**
	 * Returns the default item label generator.
	 *
	 * @return The generator (possibly {@code null}).
	 *
	 * @see #setDefaultItemLabelGenerator(CategoryItemLabelGenerator)
	 */
	@Override
	public CategoryItemLabelGenerator getDefaultItemLabelGenerator() {
		return this.defaultItemLabelGenerator;
	}

	/**
	 * Sets the default item label generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #getDefaultItemLabelGenerator()
	 */
	@Override
	public void setDefaultItemLabelGenerator(
			CategoryItemLabelGenerator generator) {
		setDefaultItemLabelGenerator(generator, true);
	}

	/**
	 * Sets the default item label generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getDefaultItemLabelGenerator()
	 */
	@Override
	public void setDefaultItemLabelGenerator(
			CategoryItemLabelGenerator generator, boolean notify) {
		this.defaultItemLabelGenerator = generator;
		if (notify) {
			fireChangeEvent();
		}
	}

	// TOOL TIP GENERATOR

	/**
	 * Returns the tool tip generator that should be used for the specified
	 * item. This method looks up the generator using the "three-layer" approach
	 * outlined in the general description of this interface. You can override
	 * this method if you want to return a different generator per item.
	 *
	 * @param row
	 *            the row index (zero-based).
	 * @param column
	 *            the column index (zero-based).
	 *
	 * @return The generator (possibly {@code null}).
	 */
	@Override
	public CategoryToolTipGenerator getToolTipGenerator(int row, int column)
	{

		CategoryToolTipGenerator result = getSeriesToolTipGenerator(row);
		if (result == null) {
			result = this.defaultToolTipGenerator;
		}
		return result;
	}

	/**
	 * Returns the tool tip generator for the specified series (a "layer 1"
	 * generator).
	 *
	 * @param series
	 *            the series index (zero-based).
	 *
	 * @return The tool tip generator (possibly {@code null}).
	 *
	 * @see #setSeriesToolTipGenerator(int, CategoryToolTipGenerator)
	 */
	@Override
	public CategoryToolTipGenerator getSeriesToolTipGenerator(int series) {
		return this.toolTipGeneratorMap.get(series);
	}

	/**
	 * Sets the tool tip generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero-based).
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #getSeriesToolTipGenerator(int)
	 */
	@Override
	public void setSeriesToolTipGenerator(int series,
			CategoryToolTipGenerator generator) {
		setSeriesToolTipGenerator(series, generator, true);
	}

	/**
	 * Sets the tool tip generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero-based).
	 * @param generator
	 *            the generator ({@code null} permitted).
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getSeriesToolTipGenerator(int)
	 */
	@Override
	public void setSeriesToolTipGenerator(int series,
			CategoryToolTipGenerator generator, boolean notify) {
		this.toolTipGeneratorMap.put(series, generator);
		if (notify) {
			fireChangeEvent();
		}
	}

	/**
	 * Returns the default tool tip generator.
	 *
	 * @return The tool tip generator (possibly {@code null}).
	 *
	 * @see #setDefaultToolTipGenerator(CategoryToolTipGenerator)
	 */
	@Override
	public CategoryToolTipGenerator getDefaultToolTipGenerator() {
		return this.defaultToolTipGenerator;
	}

	/**
	 * Sets the default tool tip generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #getDefaultToolTipGenerator()
	 */
	@Override
	public void setDefaultToolTipGenerator(CategoryToolTipGenerator
			generator) {
		setDefaultToolTipGenerator(generator, true);
	}

	/**
	 * Sets the default tool tip generator and sends a change event to all
	 * registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getDefaultToolTipGenerator()
	 */
	@Override
	public void setDefaultToolTipGenerator(CategoryToolTipGenerator
			generator,
			boolean notify) {
		this.defaultToolTipGenerator = generator;
		if (notify) {
			fireChangeEvent();
		}
	}

	// URL GENERATOR

	/**
	 * Returns the URL generator for a data item. This method just calls the
	 * getSeriesItemURLGenerator method, but you can override this behaviour if
	 * you want to.
	 *
	 * @param row
	 *            the row index (zero based).
	 * @param column
	 *            the column index (zero based).
	 *
	 * @return The URL generator.
	 */
	@Override
	public CategoryURLGenerator getItemURLGenerator(int row, int column) {
		return getSeriesItemURLGenerator(row);
	}

	/**
	 * Returns the URL generator for a series.
	 *
	 * @param series
	 *            the series index (zero based).
	 *
	 * @return The URL generator for the series.
	 *
	 * @see #setSeriesItemURLGenerator(int, CategoryURLGenerator)
	 */
	@Override
	public CategoryURLGenerator getSeriesItemURLGenerator(int series) {
		CategoryURLGenerator g = this.urlGeneratorMap.get(series);
		if (g == null) {
			g = this.defaultURLGenerator;
		}
		return g;
	}

	/**
	 * Sets the URL generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero based).
	 * @param generator
	 *            the generator.
	 *
	 * @see #getSeriesItemURLGenerator(int)
	 */
	@Override
	public void setSeriesItemURLGenerator(int series,
			CategoryURLGenerator generator) {
		setSeriesItemURLGenerator(series, generator, true);
	}

	/**
	 * Sets the URL generator for a series and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param series
	 *            the series index (zero based).
	 * @param generator
	 *            the generator.
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getSeriesItemURLGenerator(int)
	 */
	@Override
	public void setSeriesItemURLGenerator(int series,
			CategoryURLGenerator generator, boolean notify) {
		this.urlGeneratorMap.put(series, generator);
		if (notify) {
			fireChangeEvent();
		}
	}

	/**
	 * Returns the default item URL generator.
	 *
	 * @return The item URL generator.
	 *
	 * @see #setDefaultURLGenerator(CategoryURLGenerator)
	 */
	@Override
	public CategoryURLGenerator getDefaultURLGenerator() {
		return this.defaultURLGenerator;
	}

	/**
	 * Sets the default URL generator and sends a change event to all registered
	 * listeners.
	 *
	 * @param generator
	 *            the URL generator ({@code null} permitted).
	 *
	 * @see #getDefaultURLGenerator()
	 */
	@Override
	public void setDefaultURLGenerator(CategoryURLGenerator generator) {
		setDefaultURLGenerator(generator, true);
	}

	/**
	 * Sets the default URL generator and sends a change event to all registered
	 * listeners.
	 *
	 * @param generator
	 *            the item URL generator ({@code null} permitted).
	 * @param notify
	 *            notify listeners?
	 *
	 * @see #getDefaultURLGenerator()
	 */
	@Override
	public void setDefaultURLGenerator(CategoryURLGenerator generator,
			boolean notify) {
		this.defaultURLGenerator = generator;
		if (notify) {
			fireChangeEvent();
		}
	}

	//
	/**
	 * Returns the number of rows in the dataset. This value is updated in the
	 * {@link AbstractCategoryItemRenderer#initialise} method.
	 *
	 * @return The row count.
	 */
	public int getRowCount() {
		return this.rowCount;
	}

	/**
	 * Returns the number of columns in the dataset. This value is updated in
	 * the {@link AbstractCategoryItemRenderer#initialise} method.
	 *
	 * @return The column count.
	 */
	public int getColumnCount() {
		return this.columnCount;
	}

	/**
	 * Creates a new state instance---this method is called from the
	 * {@link #initialise(Graphics2D, Rectangle2D, CategoryPlot, int, PlotRenderingInfo)}
	 * method. Subclasses can override this method if they need to use a
	 * subclass of {@link CategoryItemRendererState}.
	 *
	 * @param info
	 *            collects plot rendering info ({@code null} permitted).
	 *
	 * @return The new state instance (never {@code null}).
	 *
	 * @since 1.0.5
	 */
	protected CategoryItemRendererState createState(PlotRenderingInfo info) {
		return new CategoryItemRendererState(info);
	}

	/**
	 * Initialises the renderer and returns a state object that will be used for
	 * the remainder of the drawing process for a single chart. The state object
	 * allows for the fact that the renderer may be used simultaneously by
	 * multiple threads (each thread will work with a separate state object).
	 *
	 * @param g2
	 *            the graphics device.
	 * @param dataArea
	 *            the data area.
	 * @param plot
	 *            the plot.
	 * @param rendererIndex
	 *            the renderer index.
	 * @param info
	 *            an object for returning information about the structure of the
	 *            plot ({@code null} permitted).
	 *
	 * @return The renderer state.
	 */
	@Override
	public CategoryItemRendererState initialise(GraphicsContext g2,
			Rectangle2D dataArea, CategoryPlot plot, int rendererIndex,
			PlotRenderingInfo info) {

		setPlot(plot);
		CategoryDataset data = plot.getDataset(rendererIndex);
		if (data != null) {
			this.rowCount = data.getRowCount();
			this.columnCount = data.getColumnCount();
		}
		else {
			this.rowCount = 0;
			this.columnCount = 0;
		}
		CategoryItemRendererState state = createState(info);
		int[] visibleSeriesTemp = new int[this.rowCount];
		int visibleSeriesCount = 0;
		for (int row = 0; row < this.rowCount; row++) {
			if (isSeriesVisible(row)) {
				visibleSeriesTemp[visibleSeriesCount] = row;
				visibleSeriesCount++;
			}
		}
		int[] visibleSeries = new int[visibleSeriesCount];
		System.arraycopy(visibleSeriesTemp, 0, visibleSeries, 0,
				visibleSeriesCount);
		state.setVisibleSeriesArray(visibleSeries);
		return state;
	}

	/**
	 * Returns the range of values the renderer requires to display all the
	 * items from the specified dataset.
	 *
	 * @param dataset
	 *            the dataset ({@code null} permitted).
	 *
	 * @return The range (or {@code null} if the dataset is {@code null} or
	 *         empty).
	 */
	@Override
	public Range findRangeBounds(CategoryDataset dataset) {
		return findRangeBounds(dataset, false);
	}

	/**
	 * Returns the range of values the renderer requires to display all the
	 * items from the specified dataset.
	 *
	 * @param dataset
	 *            the dataset ({@code null} permitted).
	 * @param includeInterval
	 *            include the y-interval if the dataset has one.
	 *
	 * @return The range ({@code null} if the dataset is {@code null} or empty).
	 *
	 * @since 1.0.13
	 */
	protected Range findRangeBounds(CategoryDataset dataset,
			boolean includeInterval) {
		if (dataset == null) {
			return null;
		}
		if (getDataBoundsIncludesVisibleSeriesOnly()) {
			List<Comparable> visibleSeriesKeys = new ArrayList<Comparable>();
			int seriesCount = dataset.getRowCount();
			for (int s = 0; s < seriesCount; s++) {
				if (isSeriesVisible(s)) {
					visibleSeriesKeys.add(dataset.getRowKey(s));
				}
			}
			return DatasetUtilities.findRangeBounds(dataset,
					visibleSeriesKeys, includeInterval);
		}
		else {
			return DatasetUtilities.findRangeBounds(dataset, includeInterval);
		}
	}

	/**
	 * Returns the Java2D coordinate for the middle of the specified data item.
	 *
	 * @param rowKey
	 *            the row key.
	 * @param columnKey
	 *            the column key.
	 * @param dataset
	 *            the dataset.
	 * @param axis
	 *            the axis.
	 * @param area
	 *            the data area.
	 * @param edge
	 *            the edge along which the axis lies.
	 *
	 * @return The Java2D coordinate for the middle of the item.
	 *
	 * @since 1.0.11
	 */
	@Override
	public double getItemMiddle(Comparable rowKey, Comparable columnKey,
			CategoryDataset dataset, CategoryAxis axis, Rectangle2D area,
			RectangleEdge edge) {
		return axis.getCategoryMiddle(columnKey, dataset.getColumnKeys(), area,
				edge);
	}

	/**
	 * Draws a background for the data area. The default implementation just
	 * gets the plot to draw the background, but some renderers will override
	 * this behaviour.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param plot
	 *            the plot.
	 * @param dataArea
	 *            the data area.
	 */
	@Override
	public void drawBackground(GraphicsContext g2, CategoryPlot plot,
			Rectangle2D dataArea) {

		plot.drawBackground(g2, dataArea);

	}

	/**
	 * Draws a grid line against the domain axis.
	 * <P>
	 * Note that this default implementation assumes that the horizontal axis is
	 * the domain axis. If this is not the case, you will need to override this
	 * method.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param plot
	 *            the plot.
	 * @param dataArea
	 *            the area for plotting data (not yet adjusted for any 3D
	 *            effect).
	 * @param value
	 *            the Java2D value at which the grid line should be drawn.
	 *
	 * @see #drawRangeGridline(Graphics2D, CategoryPlot, ValueAxis, Rectangle2D,
	 *      double)
	 */
	@Override
	public void drawDomainGridline(GraphicsContext g2, CategoryPlot plot,
			Rectangle2D dataArea, double value) {

		Point2D start = null;
		Point2D end = null;
		PlotOrientation orientation = plot.getOrientation();

		if (orientation == PlotOrientation.HORIZONTAL) {
			start = new Point2D(dataArea.getMinX(), value);
			end = new Point2D(dataArea.getMaxX(), value);
		}
		else if (orientation == PlotOrientation.VERTICAL) {
			start = new Point2D(value, dataArea.getMinY());
			end = new Point2D(value, dataArea.getMaxY());
		}

		Paint paint = plot.getDomainGridlinePaint();
		if (paint == null) {
			paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
		}
		g2.setStroke(paint);

		// JAVAFX stroke
		// Stroke stroke = plot.getDomainGridlineStroke();
		// if (stroke == null) {
		// stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
		// }
		// g2.setStroke(stroke);

		g2.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	/**
	 * Draws a grid line against the range axis.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param plot
	 *            the plot.
	 * @param axis
	 *            the value axis.
	 * @param dataArea
	 *            the area for plotting data (not yet adjusted for any 3D
	 *            effect).
	 * @param value
	 *            the value at which the grid line should be drawn.
	 *
	 * @see #drawDomainGridline(Graphics2D, CategoryPlot, Rectangle2D, double)
	 */
	@Override
	public void drawRangeGridline(GraphicsContext g2, CategoryPlot plot,
			ValueAxis axis, Rectangle2D dataArea, double value) {

		Range range = axis.getRange();
		if (!range.contains(value)) {
			return;
		}

		PlotOrientation orientation = plot.getOrientation();
		double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());

		double startX = 0;
		double startY = 0;
		double endX = 0;
		double endY = 0;

		if (orientation == PlotOrientation.HORIZONTAL) {
			startX = v;
			startY = dataArea.getMinY();
			endX = v;
			endY = dataArea.getMaxY();
		}
		else if (orientation == PlotOrientation.VERTICAL) {
			startX = dataArea.getMinX();
			startY = v;
			endX = dataArea.getMaxX();
			endY = v;
		}

		Paint paint = plot.getRangeGridlinePaint();
		if (paint == null) {
			paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
		}
		g2.setStroke(paint);

		// JAVAFX stroke
		// Stroke stroke = plot.getRangeGridlineStroke();
		// if (stroke == null) {
		// stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
		// }
		// g2.setStroke(stroke);

		g2.strokeLine(startX, startY, endX, endY);

	}

	//
	// /**
	// * Draws a line perpendicular to the range axis.
	// *
	// * @param g2 the graphics device.
	// * @param plot the plot.
	// * @param axis the value axis.
	// * @param dataArea the area for plotting data (not yet adjusted for any 3D
	// * effect).
	// * @param value the value at which the grid line should be drawn.
	// * @param paint the paint ({@code null} not permitted).
	// * @param stroke the stroke ({@code null} not permitted).
	// *
	// * @see #drawRangeGridline
	// *
	// * @since 1.0.13
	// */
	// public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis
	// axis,
	// Rectangle2D dataArea, double value, Paint paint, Stroke stroke) {
	//
	// // TODO: In JFreeChart 1.2.0, put this method in the
	// // CategoryItemRenderer interface
	// Range range = axis.getRange();
	// if (!range.contains(value)) {
	// return;
	// }
	//
	// PlotOrientation orientation = plot.getOrientation();
	// Line2D line = null;
	// double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
	// if (orientation == PlotOrientation.HORIZONTAL) {
	// line = new Line2D.Double(v, dataArea.getMinY(), v,
	// dataArea.getMaxY());
	// } else if (orientation == PlotOrientation.VERTICAL) {
	// line = new Line2D.Double(dataArea.getMinX(), v,
	// dataArea.getMaxX(), v);
	// }
	//
	// g2.setPaint(paint);
	// g2.setStroke(stroke);
	// Object saved = g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
	// g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
	// RenderingHints.VALUE_STROKE_NORMALIZE);
	// g2.draw(line);
	// g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, saved);
	// }
	//
	/**
	 * Draws a marker for the domain axis.
	 *
	 * @param g2
	 *            the graphics device (not {@code null}).
	 * @param plot
	 *            the plot (not {@code null}).
	 * @param axis
	 *            the range axis (not {@code null}).
	 * @param marker
	 *            the marker to be drawn (not {@code null}).
	 * @param dataArea
	 *            the area inside the axes (not {@code null}).
	 *
	 * @see #drawRangeMarker(Graphics2D, CategoryPlot, ValueAxis, Marker,
	 *      Rectangle2D)
	 */
	@Override
	public void drawDomainMarker(GraphicsContext g2, CategoryPlot plot,
			CategoryAxis axis, CategoryMarker marker, Rectangle2D dataArea) {

		Comparable category = marker.getKey();
		CategoryDataset dataset = plot.getDataset(plot.findRendererIndex(this));
		int columnIndex = dataset.getColumnIndex(category);
		if (columnIndex < 0) {
			return;
		}

		// JAVAFX
		// final Composite savedComposite = g2.getComposite();
		// g2.setComposite(AlphaComposite.getInstance(
		// AlphaComposite.SRC_OVER, marker.getAlpha()));
		//
		PlotOrientation orientation = plot.getOrientation();
		Rectangle2D bounds;
		if (marker.getDrawAsLine()) {
			double v = axis.getCategoryMiddle(columnIndex,
					dataset.getColumnCount(), dataArea,
					plot.getDomainAxisEdge());
			Line2D line;
			if (orientation == PlotOrientation.HORIZONTAL) {
				line = newLine(dataArea.getMinX(), v, dataArea.getMaxX(), v);
			} else if (orientation == PlotOrientation.VERTICAL) {
				line = newLine(v, dataArea.getMinY(), v, dataArea.getMaxY());
			} else {
				throw new IllegalStateException("Unrecognised orientation: "
						+ orientation);
			}
			g2.setStroke(marker.getPaint());
			// JAVAFX stroke
			// g2.setStroke(marker.getStroke());
			strokeLine(g2, line);
			bounds = line.getBounds2D();
		} else {
			double v0 = axis.getCategoryStart(columnIndex,
					dataset.getColumnCount(), dataArea,
					plot.getDomainAxisEdge());
			double v1 = axis.getCategoryEnd(columnIndex,
					dataset.getColumnCount(), dataArea,
					plot.getDomainAxisEdge());
			Rectangle2D area = null;
			if (orientation == PlotOrientation.HORIZONTAL) {
				area = new Rectangle2D(dataArea.getMinX(), v0,
						dataArea.getWidth(), (v1 - v0));
			}
			else if (orientation == PlotOrientation.VERTICAL) {
				area = new Rectangle2D(v0, dataArea.getMinY(),
						(v1 - v0), dataArea.getHeight());
			}
			g2.setFill(marker.getPaint());
			fillRectangle(g2, area);
			bounds = area;
		}

		String label = marker.getLabel();
		RectangleAnchor anchor = marker.getLabelAnchor();
		if (label != null) {
			Font labelFont = marker.getLabelFont();
			g2.setFont(labelFont);
			g2.setFill(marker.getLabelPaint());
			g2.setStroke(marker.getLabelPaint());
			Point2D coords = calculateDomainMarkerTextAnchorPoint(
					g2, orientation, dataArea, bounds, marker.getLabelOffset(),
					marker.getLabelOffsetType(), anchor);
			TextUtilities.drawAlignedString(label, g2,
					(float) coords.getX(), (float) coords.getY(),
					marker.getLabelTextAnchor());
		}
		// JAVAFX
		// g2.setComposite(savedComposite);
	}

	/**
	 * Draws a marker for the range axis.
	 *
	 * @param g2
	 *            the graphics device (not {@code null}).
	 * @param plot
	 *            the plot (not {@code null}).
	 * @param axis
	 *            the range axis (not {@code null}).
	 * @param marker
	 *            the marker to be drawn (not {@code null}).
	 * @param dataArea
	 *            the area inside the axes (not {@code null}).
	 *
	 * @see #drawDomainMarker(Graphics2D, CategoryPlot, CategoryAxis,
	 *      CategoryMarker, Rectangle2D)
	 */
	@Override
	public void drawRangeMarker(GraphicsContext g2, CategoryPlot plot,
			ValueAxis axis, Marker marker, Rectangle2D dataArea) {

		// JAVAFX
		if (marker instanceof ValueMarker) {
			ValueMarker vm = (ValueMarker) marker;
			double value = vm.getValue();
			Range range = axis.getRange();
			if (!range.contains(value)) {
				return;
			}

			// JAVAFX
			// final Composite savedComposite = g2.getComposite();
			// g2.setComposite(AlphaComposite.getInstance(
			// AlphaComposite.SRC_OVER, marker.getAlpha()));

			PlotOrientation orientation = plot.getOrientation();
			double v = axis.valueToJava2D(value, dataArea,
					plot.getRangeAxisEdge());
			Line2D line = null;
			if (orientation == PlotOrientation.HORIZONTAL) {
				line = newLine(v, dataArea.getMinY(), v,
						dataArea.getMaxY());
			} else if (orientation == PlotOrientation.VERTICAL) {
				line = newLine(dataArea.getMinX(), v,
						dataArea.getMaxX(), v);
			} else {
				throw new IllegalStateException("Unrecognised orientation: "
						+ orientation);
			}

			g2.setStroke(marker.getPaint());
			// JAVAFX stroke
			// g2.setStroke(marker.getStroke());
			strokeLine(g2, line);

			String label = marker.getLabel();
			if (label != null) {
				RectangleAnchor anchor = marker.getLabelAnchor();
				Font labelFont = marker.getLabelFont();
				g2.setFont(labelFont);
				Point2D coords = calculateRangeMarkerTextAnchorPoint(
						g2, orientation, dataArea, line.getBounds2D(),
						marker.getLabelOffset(), LengthAdjustmentType.EXPAND,
						anchor);
				Rectangle2D rect = TextUtilities.calcAlignedStringBounds(label,
						g2, (float) coords.getX(), (float) coords.getY(),
						marker.getLabelTextAnchor());
				g2.setFill(marker.getLabelBackgroundColor());
				fillRectangle(g2, rect);
				g2.setFill(marker.getLabelPaint());
				TextUtilities.drawAlignedString(label, g2,
						(float) coords.getX(), (float) coords.getY(),
						marker.getLabelTextAnchor());
			}
			// JAVAFX
			// g2.setComposite(savedComposite);
		} else if (marker instanceof IntervalMarker) {
			IntervalMarker im = (IntervalMarker) marker;
			double start = im.getStartValue();
			double end = im.getEndValue();
			Range range = axis.getRange();
			if (!(range.intersects(start, end))) {
				return;
			}

			// JAVAFX
			// final Composite savedComposite = g2.getComposite();
			// g2.setComposite(AlphaComposite.getInstance(
			// AlphaComposite.SRC_OVER, marker.getAlpha()));

			double start2d = axis.valueToJava2D(start, dataArea,
					plot.getRangeAxisEdge());
			double end2d = axis.valueToJava2D(end, dataArea,
					plot.getRangeAxisEdge());
			double low = Math.min(start2d, end2d);
			double high = Math.max(start2d, end2d);

			PlotOrientation orientation = plot.getOrientation();
			Rectangle2D rect = null;
			if (orientation == PlotOrientation.HORIZONTAL) {
				// clip left and right bounds to data area
				low = Math.max(low, dataArea.getMinX());
				high = Math.min(high, dataArea.getMaxX());
				rect = new Rectangle2D(low,
						dataArea.getMinY(), high - low,
						dataArea.getHeight());
			} else if (orientation == PlotOrientation.VERTICAL) {
				// clip top and bottom bounds to data area
				low = Math.max(low, dataArea.getMinY());
				high = Math.min(high, dataArea.getMaxY());
				rect = new Rectangle2D(dataArea.getMinX(),
						low, dataArea.getWidth(),
						high - low);
			}
			Paint p = marker.getPaint();
			// JAVAFX gradient
			// if (p instanceof GradientPaint) {
			// GradientPaint gp = (GradientPaint) p;
			// GradientPaintTransformer t = im.getGradientPaintTransformer();
			// if (t != null) {
			// gp = t.transform(gp, rect);
			// }
			// g2.setPaint(gp);
			// } else {
			g2.setFill(p);
			// }
			fillRectangle(g2, rect);

			// JAVAFX stroke
			// // now draw the outlines, if visible...
			// if (im.getOutlinePaint() != null && im.getOutlineStroke() !=
			// null) {
			// if (orientation == PlotOrientation.VERTICAL) {
			// Line2D line = new Line2D.Double();
			// double x0 = dataArea.getMinX();
			// double x1 = dataArea.getMaxX();
			// g2.setPaint(im.getOutlinePaint());
			// g2.setStroke(im.getOutlineStroke());
			// if (range.contains(start)) {
			// line.setLine(x0, start2d, x1, start2d);
			// g2.draw(line);
			// }
			// if (range.contains(end)) {
			// line.setLine(x0, end2d, x1, end2d);
			// g2.draw(line);
			// }
			// } else { // PlotOrientation.HORIZONTAL
			// Line2D line = new Line2D.Double();
			// double y0 = dataArea.getMinY();
			// double y1 = dataArea.getMaxY();
			// g2.setPaint(im.getOutlinePaint());
			// g2.setStroke(im.getOutlineStroke());
			// if (range.contains(start)) {
			// line.setLine(start2d, y0, start2d, y1);
			// g2.draw(line);
			// }
			// if (range.contains(end)) {
			// line.setLine(end2d, y0, end2d, y1);
			// g2.draw(line);
			// }
			// }
			// }

			String label = marker.getLabel();
			if (label != null) {
				RectangleAnchor anchor = marker.getLabelAnchor();
				Font labelFont = marker.getLabelFont();
				g2.setFont(labelFont);
				Point2D coords = calculateRangeMarkerTextAnchorPoint(g2,
						orientation, dataArea, rect, marker.getLabelOffset(),
						marker.getLabelOffsetType(), anchor);
				Rectangle2D r = TextUtilities.calcAlignedStringBounds(label,
						g2, (float) coords.getX(), (float) coords.getY(),
						marker.getLabelTextAnchor());
				g2.setFill(marker.getLabelBackgroundColor());
				fillRectangle(g2, r);
				g2.setFill(marker.getLabelPaint());
				TextUtilities.drawAlignedString(label, g2,
						(float) coords.getX(), (float) coords.getY(),
						marker.getLabelTextAnchor());
			}
			// JAVAFX
			// g2.setComposite(savedComposite);
		}
	}

	/**
	 * Calculates the {@code (x, y)} coordinates for drawing the label for a
	 * marker on the range axis.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param orientation
	 *            the plot orientation.
	 * @param dataArea
	 *            the data area.
	 * @param markerArea
	 *            the rectangle surrounding the marker.
	 * @param markerOffset
	 *            the marker offset.
	 * @param labelOffsetType
	 *            the label offset type.
	 * @param anchor
	 *            the label anchor.
	 *
	 * @return The coordinates for drawing the marker label.
	 */
	protected Point2D calculateDomainMarkerTextAnchorPoint(GraphicsContext g2,
			PlotOrientation orientation, Rectangle2D dataArea,
			Rectangle2D markerArea, RectangleInsets markerOffset,
			LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {

		Rectangle2D anchorRect = null;
		if (orientation == PlotOrientation.HORIZONTAL) {
			anchorRect = markerOffset.createAdjustedRectangle(markerArea,
					LengthAdjustmentType.CONTRACT, labelOffsetType);
		} else if (orientation == PlotOrientation.VERTICAL) {
			anchorRect = markerOffset.createAdjustedRectangle(markerArea,
					labelOffsetType, LengthAdjustmentType.CONTRACT);
		}
		return RectangleAnchor.coordinates(anchorRect, anchor);
	}

	/**
	 * Calculates the (x, y) coordinates for drawing a marker label.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param orientation
	 *            the plot orientation.
	 * @param dataArea
	 *            the data area.
	 * @param markerArea
	 *            the rectangle surrounding the marker.
	 * @param markerOffset
	 *            the marker offset.
	 * @param labelOffsetType
	 *            the label offset type.
	 * @param anchor
	 *            the label anchor.
	 *
	 * @return The coordinates for drawing the marker label.
	 */
	protected Point2D calculateRangeMarkerTextAnchorPoint(GraphicsContext g2,
			PlotOrientation orientation, Rectangle2D dataArea,
			Rectangle2D markerArea, RectangleInsets markerOffset,
			LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {
		Rectangle2D anchorRect = null;
		if (orientation == PlotOrientation.HORIZONTAL) {
			anchorRect = markerOffset.createAdjustedRectangle(markerArea,
					labelOffsetType, LengthAdjustmentType.CONTRACT);
		} else if (orientation == PlotOrientation.VERTICAL) {
			anchorRect = markerOffset.createAdjustedRectangle(markerArea,
					LengthAdjustmentType.CONTRACT, labelOffsetType);
		}
		return anchor.getAnchorPoint(anchorRect);
	}

	/**
	 * Returns a legend item for a series. This default implementation will
	 * return {@code null} if {@link #isSeriesVisible(int)} or
	 * {@link #isSeriesVisibleInLegend(int)} returns {@code false}.
	 *
	 * @param datasetIndex
	 *            the dataset index (zero-based).
	 * @param series
	 *            the series index (zero-based).
	 *
	 * @return The legend item (possibly {@code null}).
	 *
	 * @see #getLegendItems()
	 */
	@Override
	public LegendItem getLegendItem(int datasetIndex, int series) {

		CategoryPlot p = getPlot();
		if (p == null) {
			return null;
		}

		// check that a legend item needs to be displayed...
		if (!isSeriesVisible(series) || !isSeriesVisibleInLegend(series)) {
			return null;
		}

		CategoryDataset dataset = p.getDataset(datasetIndex);
		String label = this.legendItemLabelGenerator.generateLabel(dataset,
				series);
		String description = label;
		String toolTipText = null;
		if (this.legendItemToolTipGenerator != null) {
			toolTipText = this.legendItemToolTipGenerator.generateLabel(
					dataset, series);
		}
		String urlText = null;
		if (this.legendItemURLGenerator != null) {
			urlText = this.legendItemURLGenerator.generateLabel(dataset,
					series);
		}
		Shape shape = lookupLegendShape(series);
		Paint paint = lookupSeriesPaint(series);
		Paint outlinePaint = lookupSeriesOutlinePaint(series);
		// JAVAFX stroke
		// Stroke outlineStroke = lookupSeriesOutlineStroke(series);

		LegendItem item = new LegendItem(label, description, toolTipText,
				urlText, shape, paint, /* JAVAFX outlineStroke, */outlinePaint);
		item.setLabelFont(lookupLegendTextFont(series));
		Paint labelPaint = lookupLegendTextPaint(series);
		if (labelPaint != null) {
			item.setLabelPaint(labelPaint);
		}
		item.setSeriesKey(dataset.getRowKey(series));
		item.setSeriesIndex(series);
		item.setDataset(dataset);
		item.setDatasetIndex(datasetIndex);
		return item;
	}

	/**
	 * Tests this renderer for equality with another object.
	 *
	 * @param obj
	 *            the object.
	 *
	 * @return {@code true} or {@code false}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AbstractCategoryItemRenderer)) {
			return false;
		}
		AbstractCategoryItemRenderer that = (AbstractCategoryItemRenderer) obj;

		if (!ObjectUtils.equal(this.itemLabelGeneratorMap,
				that.itemLabelGeneratorMap)) {
			return false;
		}
		if (!ObjectUtils.equal(this.defaultItemLabelGenerator,
				that.defaultItemLabelGenerator)) {
			return false;
		}
		if (!ObjectUtils.equal(this.toolTipGeneratorMap,
				that.toolTipGeneratorMap)) {
			return false;
		}
		if (!ObjectUtils.equal(this.defaultToolTipGenerator,
				that.defaultToolTipGenerator)) {
			return false;
		}
		if (!ObjectUtils.equal(this.urlGeneratorMap,
				that.urlGeneratorMap)) {
			return false;
		}
		if (!ObjectUtils.equal(this.defaultURLGenerator,
				that.defaultURLGenerator)) {
			return false;
		}
		// JAVAFX
		// if (!ObjectUtils.equal(this.legendItemLabelGenerator,
		// that.legendItemLabelGenerator)) {
		// return false;
		// }
		// if (!ObjectUtils.equal(this.legendItemToolTipGenerator,
		// that.legendItemToolTipGenerator)) {
		// return false;
		// }
		// if (!ObjectUtils.equal(this.legendItemURLGenerator,
		// that.legendItemURLGenerator)) {
		// return false;
		// }
		return super.equals(obj);
	}

	/**
	 * Returns a hash code for the renderer.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	/**
	 * Returns the drawing supplier from the plot.
	 *
	 * @return The drawing supplier (possibly {@code null}).
	 */
	@Override
	public DrawingSupplier getDrawingSupplier() {
		DrawingSupplier result = null;
		CategoryPlot cp = getPlot();
		if (cp != null) {
			result = cp.getDrawingSupplier();
		}
		return result;
	}

	/**
	 * Considers the current (x, y) coordinate and updates the crosshair point
	 * if it meets the criteria (usually means the (x, y) coordinate is the
	 * closest to the anchor point so far).
	 *
	 * @param crosshairState
	 *            the crosshair state ({@code null} permitted, but the method
	 *            does nothing in that case).
	 * @param rowKey
	 *            the row key.
	 * @param columnKey
	 *            the column key.
	 * @param value
	 *            the data value.
	 * @param datasetIndex
	 *            the dataset index.
	 * @param transX
	 *            the x-value translated to Java2D space.
	 * @param transY
	 *            the y-value translated to Java2D space.
	 * @param orientation
	 *            the plot orientation ({@code null} not permitted).
	 *
	 * @since 1.0.11
	 */
	protected void updateCrosshairValues(CategoryCrosshairState
			crosshairState,
			Comparable rowKey, Comparable columnKey, double value,
			int datasetIndex,
			double transX, double transY, PlotOrientation orientation) {

		ParamChecks.nullNotPermitted(orientation, "orientation");

		if (crosshairState != null) {
			if (this.plot.isRangeCrosshairLockedOnData()) {
				// both axes
				crosshairState.updateCrosshairPoint(rowKey, columnKey, value,
						datasetIndex, transX, transY, orientation);
			}
			else {
				crosshairState.updateCrosshairX(rowKey, columnKey,
						datasetIndex, transX, orientation);
			}
		}
	}

	/**
	 * Draws an item label.
	 *
	 * @param g2
	 *            the graphics device.
	 * @param orientation
	 *            the orientation.
	 * @param dataset
	 *            the dataset.
	 * @param row
	 *            the row.
	 * @param column
	 *            the column.
	 * @param x
	 *            the x coordinate (in Java2D space).
	 * @param y
	 *            the y coordinate (in Java2D space).
	 * @param negative
	 *            indicates a negative value (which affects the item label
	 *            position).
	 */
	protected void drawItemLabel(GraphicsContext g2, PlotOrientation orientation,
			CategoryDataset dataset, int row, int column,
			double x, double y, boolean negative) {

		CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
				column);
		if (generator != null) {
			// JAVAFX paint, stroke
			Font labelFont = getItemLabelFont(row, column);
			Paint paint = getItemLabelPaint(row, column);
			g2.setFont(labelFont);
			g2.setFill(paint);
			g2.setStroke(paint);
			String label = generator.generateLabel(dataset, row, column);
			ItemLabelPosition position;
			if (!negative) {
				position = getPositiveItemLabelPosition(row, column);
			}
			else {
				position = getNegativeItemLabelPosition(row, column);
			}
			Point2D anchorPoint = calculateLabelAnchorPoint(
					position.getItemLabelAnchor(), x, y, orientation);
			TextUtilities.drawRotatedString(label, g2,
					(float) anchorPoint.getX(), (float) anchorPoint.getY(),
					position.getTextAnchor(),
					position.getAngle(), position.getRotationAnchor());
		}

	}

	/**
	 * Returns an independent copy of the renderer. The {@code plot} reference
	 * is shallow copied.
	 *
	 * @return A clone.
	 *
	 * @throws CloneNotSupportedException
	 *             can be thrown if one of the objects belonging to the renderer
	 *             does not support cloning (for example, an item label
	 *             generator).
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		AbstractCategoryItemRenderer clone = (AbstractCategoryItemRenderer) super.clone();
		// JAVAFX
		// if (this.itemLabelGeneratorMap != null) {
		// clone.itemLabelGeneratorMap = CloneUtils.cloneMapValues(
		// this.itemLabelGeneratorMap);
		// }
		// if (this.defaultItemLabelGenerator != null) {
		// if (this.defaultItemLabelGenerator instanceof PublicCloneable) {
		// PublicCloneable pc = (PublicCloneable)
		// this.defaultItemLabelGenerator;
		// clone.defaultItemLabelGenerator = (CategoryItemLabelGenerator)
		// pc.clone();
		// }
		// else {
		// throw new CloneNotSupportedException(
		// "ItemLabelGenerator not cloneable.");
		// }
		// }
		//
		// if (this.toolTipGeneratorMap != null) {
		// clone.toolTipGeneratorMap = CloneUtils.cloneMapValues(
		// this.toolTipGeneratorMap);
		// }
		//
		// if (this.defaultToolTipGenerator != null) {
		// if (this.defaultToolTipGenerator instanceof PublicCloneable) {
		// PublicCloneable pc = (PublicCloneable) this.defaultToolTipGenerator;
		// clone.defaultToolTipGenerator = (CategoryToolTipGenerator)
		// pc.clone();
		// }
		// else {
		// throw new CloneNotSupportedException(
		// "Base tool tip generator not cloneable.");
		// }
		// }
		//
		// if (this.urlGeneratorMap != null) {
		// clone.urlGeneratorMap = CloneUtils.cloneMapValues(
		// this.urlGeneratorMap);
		// }
		//
		// if (this.defaultURLGenerator != null) {
		// if (this.defaultURLGenerator instanceof PublicCloneable) {
		// PublicCloneable pc = (PublicCloneable) this.defaultURLGenerator;
		// clone.defaultURLGenerator = (CategoryURLGenerator) pc.clone();
		// }
		// else {
		// throw new CloneNotSupportedException(
		// "Base item URL generator not cloneable.");
		// }
		// }
		//
		// if (this.legendItemLabelGenerator instanceof PublicCloneable) {
		// clone.legendItemLabelGenerator =
		// ObjectUtils.clone(this.legendItemLabelGenerator);
		// }
		// if (this.legendItemToolTipGenerator instanceof PublicCloneable) {
		// clone.legendItemToolTipGenerator =
		// ObjectUtils.clone(this.legendItemToolTipGenerator);
		// }
		// if (this.legendItemURLGenerator instanceof PublicCloneable) {
		// clone.legendItemURLGenerator =
		// ObjectUtils.clone(this.legendItemURLGenerator);
		// }
		return clone;
	}

	//
	// /**
	// * Returns a domain axis for a plot.
	// *
	// * @param plot the plot.
	// * @param index the axis index.
	// *
	// * @return A domain axis.
	// */
	// protected CategoryAxis getDomainAxis(CategoryPlot plot, int index) {
	// CategoryAxis result = plot.getDomainAxis(index);
	// if (result == null) {
	// result = plot.getDomainAxis();
	// }
	// return result;
	// }
	//
	// /**
	// * Returns a range axis for a plot.
	// *
	// * @param plot the plot.
	// * @param index the axis index.
	// *
	// * @return A range axis.
	// */
	// protected ValueAxis getRangeAxis(CategoryPlot plot, int index) {
	// ValueAxis result = plot.getRangeAxis(index);
	// if (result == null) {
	// result = plot.getRangeAxis();
	// }
	// return result;
	// }
	//
	/**
	 * Returns a (possibly empty) collection of legend items for the series that
	 * this renderer is responsible for drawing.
	 *
	 * @return The legend item collection (never {@code null}).
	 *
	 * @see #getLegendItem(int, int)
	 */
	@Override
	public List<LegendItem> getLegendItems() {
		List<LegendItem> result = new ArrayList<LegendItem>();
		if (this.plot == null) {
			return result;
		}
		int index = this.plot.findRendererIndex(this);
		CategoryDataset dataset = this.plot.getDataset(index);
		if (dataset == null) {
			return result;
		}
		int seriesCount = dataset.getRowCount();
		if (plot.getRowRenderingOrder().equals(SortOrder.ASCENDING)) {
			for (int i = 0; i < seriesCount; i++) {
				if (isSeriesVisibleInLegend(i)) {
					LegendItem item = getLegendItem(index, i);
					if (item != null) {
						result.add(item);
					}
				}
			}
		}
		else {
			for (int i = seriesCount - 1; i >= 0; i--) {
				if (isSeriesVisibleInLegend(i)) {
					LegendItem item = getLegendItem(index, i);
					if (item != null) {
						result.add(item);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Returns the legend item label generator.
	 *
	 * @return The label generator (never {@code null}).
	 *
	 * @see #setLegendItemLabelGenerator(CategorySeriesLabelGenerator)
	 */
	public CategorySeriesLabelGenerator getLegendItemLabelGenerator() {
		return this.legendItemLabelGenerator;
	}

	/**
	 * Sets the legend item label generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} not permitted).
	 *
	 * @see #getLegendItemLabelGenerator()
	 */
	public void setLegendItemLabelGenerator(
			CategorySeriesLabelGenerator generator) {
		ParamChecks.nullNotPermitted(generator, "generator");
		this.legendItemLabelGenerator = generator;
		fireChangeEvent();
	}

	/**
	 * Returns the legend item tool tip generator.
	 *
	 * @return The tool tip generator (possibly {@code null}).
	 *
	 * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
	 */
	public CategorySeriesLabelGenerator getLegendItemToolTipGenerator() {
		return this.legendItemToolTipGenerator;
	}

	/**
	 * Sets the legend item tool tip generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
	 */
	public void setLegendItemToolTipGenerator(
			CategorySeriesLabelGenerator generator) {
		this.legendItemToolTipGenerator = generator;
		fireChangeEvent();
	}

	/**
	 * Returns the legend item URL generator.
	 *
	 * @return The URL generator (possibly {@code null}).
	 *
	 * @see #setLegendItemURLGenerator(CategorySeriesLabelGenerator)
	 */
	public CategorySeriesLabelGenerator getLegendItemURLGenerator() {
		return this.legendItemURLGenerator;
	}

	/**
	 * Sets the legend item URL generator and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param generator
	 *            the generator ({@code null} permitted).
	 *
	 * @see #getLegendItemURLGenerator()
	 */
	public void setLegendItemURLGenerator(
			CategorySeriesLabelGenerator generator) {
		this.legendItemURLGenerator = generator;
		fireChangeEvent();
	}

	/**
	 * Adds an entity with the specified hotspot.
	 *
	 * @param entities
	 *            the entity collection.
	 * @param dataset
	 *            the dataset.
	 * @param row
	 *            the row index.
	 * @param column
	 *            the column index.
	 * @param hotspot
	 *            the hotspot ({@code null} not permitted).
	 */
	protected void addItemEntity(EntityCollection entities,
			CategoryDataset dataset, int row, int column, Shape hotspot) {
		ParamChecks.nullNotPermitted(hotspot, "hotspot");
		if (!getItemCreateEntity(row, column)) {
			return;
		}
		String tip = null;
		CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
		if (tipster != null) {
			tip = tipster.generateToolTip(dataset, row, column);
		}
		String url = null;
		CategoryURLGenerator urlster = getItemURLGenerator(row, column);
		if (urlster != null) {
			url = urlster.generateURL(dataset, row, column);
		}
		CategoryItemEntity entity = new CategoryItemEntity(hotspot, tip, url,
				dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
		entities.add(entity);
	}

	/**
	 * Adds an entity to the collection.
	 *
	 * @param entities
	 *            the entity collection being populated.
	 * @param hotspot
	 *            the entity area (if {@code null} a default will be used).
	 * @param dataset
	 *            the dataset.
	 * @param row
	 *            the series.
	 * @param column
	 *            the item.
	 * @param entityX
	 *            the entity's center x-coordinate in user space (only used if
	 *            {@code area} is {@code null}).
	 * @param entityY
	 *            the entity's center y-coordinate in user space (only used if
	 *            {@code area} is {@code null}).
	 *
	 * @since 1.0.13
	 */
	protected void addEntity(EntityCollection entities, Shape hotspot,
			CategoryDataset dataset, int row, int column,
			double entityX, double entityY) {
		if (!getItemCreateEntity(row, column)) {
			return;
		}
		Shape s = hotspot;
		if (hotspot == null) {
			double r = getDefaultEntityRadius();
			double w = r * 2;
			if (getPlot().getOrientation() == PlotOrientation.VERTICAL) {
				s = new Ellipse2D((float) (entityX - r), (float) (entityY - r), (float) w, (float) w);
			}
			else {
				s = new Ellipse2D((float) (entityY - r), (float) (entityX - r), (float) w, (float) w);
			}
		}
		String tip = null;
		CategoryToolTipGenerator generator = getToolTipGenerator(row, column);
		if (generator != null) {
			tip = generator.generateToolTip(dataset, row, column);
		}
		String url = null;
		CategoryURLGenerator urlster = getItemURLGenerator(row, column);
		if (urlster != null) {
			url = urlster.generateURL(dataset, row, column);
		}
		CategoryItemEntity entity = new CategoryItemEntity(s, tip, url,
				dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
		entities.add(entity);
	}

}
