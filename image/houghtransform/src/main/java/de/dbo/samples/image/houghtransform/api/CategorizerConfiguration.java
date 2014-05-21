package de.dbo.samples.image.houghtransform.api;

import java.util.List;

import de.dbo.samples.image.houghtransform.core.hough.HoughLines;

public interface CategorizerConfiguration {

    //
    // Transform type
    //

    public abstract CategorizerMode type();

    //
    // Hough Configuration
    //

    public abstract int getWhiteBorder();

    public abstract void setWhiteBorder(int whiteBorder);

    public abstract boolean isSplit();

    public abstract void setSplit(boolean split);

    public abstract String getHoughClassname();

    public abstract void setHoughClassname(String classname);

    public abstract int neighbourhoodSize(ImageQuality quality) throws HoughTransformException;

    public abstract int neighbourhoodSize(int width, int height) throws HoughTransformException;

    /*
     * threshold (percentage) determines lines that are extracted from the hough
     * array
     */

    public abstract int getThreshold();

    public abstract void setThreshold(int procent);

    /*
     * size of the neighborhood to search for other local maximum It depends on
     * the image quality. Typical values 1,2,5
     */

    public abstract int getNeighbourhoodSizeHigh();

    public abstract void setNeighbourhoodSizeHigh(int pixel);

    public abstract int getNeighbourhoodSizeNormal();

    public abstract void setNeighbourhoodSizeNormal(int pixel);

    public abstract int getNeighbourhoodSizeLow();

    public abstract void setNeighbourhoodSizeLow(int pixel);

    /*
     * number of discrete values for theta. Typical value is 180. Increasing of
     * this value has no sense
     */

    public abstract int getMaxTheta();

    public abstract void setMaxTheta(int cnt);

    //
    // shape-line recognition
    //

    public abstract String getShapeLineClassname();

    public abstract void setShapeLineClassname(String classname);

    public abstract double getShapeLinePeakTolerance();

    public abstract void setShapeLinePeakTolerance(double ratio);

    public abstract double getShapeLineCenterTolerance();

    public abstract void setShapeLineCenterTolerance(double ratio);

    public abstract double getShapeLineThetaEps();

    public abstract void setShapeLineThetaEps(double eps);

    //
    // content-line recognition
    //

    public abstract double getContentLinePeakTolerance();

    public abstract void setContentLinePeakTolerance(double ratio);

    public abstract double getContentLineCenterTolerance();

    public abstract void setContentLineCenterTolerance(double ratio);

    public abstract double getContentLineThetaEps();

    public abstract void setContentLineThetaEps(double eps);

    //
    // shape recognition: shape-line counters.
    // Shape lines can have several categories.
    // Therefore, there are two minimal counters:
    // minimal for each category and the total minimal counter
    // Acceptance algorithms is shape-specific
    //

    public abstract int getShapeLineCntMin();

    public abstract void setShapeLineCntMin(int mincnt);

    public abstract int getShapeLineCntTotal();

    public abstract void setShapeLineCntTotal(int cnttotal);

    //
    // content recognition: content-line counters.
    // Content lines can have several categories.
    // Therefore, there are two minimal counters:
    // minimal for each category and the total minimal counter
    // Acceptance algorithms is shape-specific
    //

    public abstract int getContentLineCntMin();

    public abstract void setContentLineCntMin(int cnt);

    public abstract int getContentLineCntTotal();

    public abstract void setContentLineCntTotal(int cnt);

    //
    // shape and its constraints
    //

    public abstract Shape shape() throws HoughTransformException;

    public abstract HoughLines shapeLines() throws HoughTransformException;

    public abstract String getShapeClassname();

    public abstract void setShapeClassname(String classname);

    public abstract String getShapeLinesClassname();

    public abstract void setShapeLinesClassname(String classname);

    public abstract double getShapeRectangularRatioMax();

    public abstract void setShapeRectangularRatioMax(double ratio);

    public abstract double getShapeRadius();

    public abstract void setShapeRadius(double ratio);

    //
    // shape-filter
    //

    /**
     * usage of the shape-filter in the CONTENT-step. If shape-filter is not
     * used, them the categorization algorithm only performs the first step
     *
     * @return true only if the shape-filter should be used at the second phase
     *         of the algorithm
     */
    public abstract boolean shapeFilterUsage();

    public abstract String getShapeFilterClassname();

    public abstract void setShapeFilterClassname(String classname);

    public abstract int getShapeFilterMargin();

    public abstract void setShapeFilterMargin(int procent);

    //
    // Image quality classification
    //

    public abstract ImageQuality imageQuality(int width, int height);

    public abstract double getImageErrorMax();

    public abstract void setImageErrorMax(double widthHeightRatio);

    public abstract int getImageHighMax();

    public abstract void setImageHighMax(int pixel);

    public abstract int getImageNormalMax();

    public abstract void setImageNormalMax(int pixel);

    public abstract int getImageLowMax();

    public abstract void setImageLowMax(int pixel);

    public abstract int getImageLowMin();

    public abstract void setImageLowMin(int pixel);

    /**
     * sequence of image-filters to applied on the input image
     *
     * @return
     */
    public abstract List<String> getImageFilters();

    public abstract void setImageFilters(List<String> classnames);

    //
    // Debugging
    //

    /**
     * Pretty-print of this configuration
     *
     * @return pretty-print string
     */
    public abstract String print();

    /**
     * Pretty-print of the quality classification
     *
     * @return pretty-print string
     */
    public String printImageQualityClassification();

    /**
     * Pretty-print of the neighborhood sizes
     *
     * @return pretty-print string
     */
    public String printNeighbourhoodSizes();

    /**
     * Pretty-print of the image-filters
     *
     * @return pretty-print string
     */
    public abstract String printImageFilters();

    /**
     * Pretty-print of the image-filters
     *
     * @return pretty-print string
     */
    public abstract String printShapeFilter();

}
