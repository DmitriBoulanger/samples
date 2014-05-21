package de.dbo.samples.image.houghtransform.core;

import java.lang.reflect.Constructor;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import de.dbo.samples.image.houghtransform.api.HTException;
import de.dbo.samples.image.houghtransform.api.HTCategorizerMode;
import de.dbo.samples.image.houghtransform.api.ImageQuality;
import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.Util;

public final class CategorizerConfigurationImpl implements CategorizerConfiguration {
    private static final Logger      log = LoggerFactory.getLogger(CategorizerConfiguration.class);

    private final HTCategorizerMode type;

    protected CategorizerConfigurationImpl(final HTCategorizerMode type) {
        this.type = type;
    }

    @Override
    public HTCategorizerMode type() {
        return type;
    }

    // ###
    private int whiteBorder;

    @Override
    public final int getWhiteBorder() {
        return this.whiteBorder;
    }

    @Override
    @Required
    public final void setWhiteBorder(int whiteBorder) {
        this.whiteBorder = whiteBorder;
    }

    // ###
    private boolean split;

    @Override
    public final boolean isSplit() {
        return this.split;
    }

    @Override
    @Required
    public final void setSplit(boolean split) {
        this.split = split;
    }

    // HOUGH PARAMETRS

    // ###
    private String houghClassname;

    @Override
    public final String getHoughClassname() {
        return this.houghClassname;
    }

    @Override
    @Required
    public final void setHoughClassname(String classname) {
        this.houghClassname = classname;
    }

    @Override
    public final int neighbourhoodSize(ImageQuality quality)
            throws HTException {
        switch (quality) {
            case HIGH:
                return getNeighbourhoodSizeHigh();
            case NORMAL:
                return getNeighbourhoodSizeNormal();
            case LOW:
                return getNeighbourhoodSizeLow();
            default:
                if (0 == whiteBorder) {
                    throw new HTException(HTException.SYSTEM,
                            "Cannot determine neighbourhood size for image quality " + quality.name());
                }
                else {
                    log.warn("Cannot determine neighbourhood size for image quality " + quality.name());
                    return getNeighbourhoodSizeNormal();
                }
        }
    }

    @Override
    public final int neighbourhoodSize(int width, int height) throws HTException {
        return neighbourhoodSize(imageQuality(width, height));
    }

    // ###
    private int threshold;

    @Override
    public final int getThreshold() {
        return this.threshold;
    }

    @Override
    @Required
    public final void setThreshold(int procent) {
        this.threshold = procent;
    }

    // ###
    private int neighbourhoodSizeHigh;

    @Override
    public final int getNeighbourhoodSizeHigh() {
        return this.neighbourhoodSizeHigh;
    }

    @Override
    @Required
    public final void setNeighbourhoodSizeHigh(int pixel) {
        this.neighbourhoodSizeHigh = pixel;
    }

    // ###
    private int neighbourhoodSizeNormal;

    @Override
    public final int getNeighbourhoodSizeNormal() {
        return this.neighbourhoodSizeNormal;
    }

    @Override
    @Required
    public final void setNeighbourhoodSizeNormal(int pixel) {
        this.neighbourhoodSizeNormal = pixel;
    }

    // ###
    private int neighbourhoodSizeLow;

    @Override
    public final int getNeighbourhoodSizeLow() {
        return this.neighbourhoodSizeLow;
    }

    @Override
    @Required
    public final void setNeighbourhoodSizeLow(int pixel) {
        this.neighbourhoodSizeLow = pixel;
    }

    // ###
    private int maxTheta = 180;

    @Override
    public final int getMaxTheta() {
        return this.maxTheta;
    }

    @Override
    @Required
    public final void setMaxTheta(int cnt) {
        this.maxTheta = cnt;
    }

    // SHAPE-LINE RECOGNITION

    // ####
    private double shapeLinePeakTolerance;

    @Override
    public final double getShapeLinePeakTolerance() {
        return this.shapeLinePeakTolerance;
    }

    @Override
    @Required
    public final void setShapeLinePeakTolerance(double tolerance) {
        this.shapeLinePeakTolerance = tolerance;
    }

    // ####
    private double shapeLineCenterTolerance;

    @Override
    public final double getShapeLineCenterTolerance() {
        return this.shapeLineCenterTolerance;
    }

    @Override
    @Required
    public final void setShapeLineCenterTolerance(double tolerance) {
        this.shapeLineCenterTolerance = tolerance;
    }

    // ####
    private double shapeThetaEps;

    @Override
    public final double getShapeLineThetaEps() {
        return this.shapeThetaEps;
    }

    @Override
    @Required
    public final void setShapeLineThetaEps(double eps) {
        this.shapeThetaEps = eps;

    }

    // CONTENT-LINE RECOGNITION

    // ####
    private double contentPeakTolerance;

    @Override
    public final double getContentLinePeakTolerance() {
        return this.contentPeakTolerance;
    }

    @Override
    @Required
    public final void setContentLinePeakTolerance(double ratio) {
        this.contentPeakTolerance = ratio;
    }

    // ####
    private double contentCenterTolerance;

    @Override
    public final double getContentLineCenterTolerance() {
        return this.contentCenterTolerance;
    }

    @Override
    @Required
    public final void setContentLineCenterTolerance(double ratio) {
        this.contentCenterTolerance = ratio;
    }

    // ###
    double contentThetaEps;

    @Override
    public final double getContentLineThetaEps() {
        return this.contentThetaEps;
    }

    @Override
    @Required
    public final void setContentLineThetaEps(double eps) {
        this.contentThetaEps = eps;
    }

    // SHAPE RECOGNITION

    // ####
    private int shapeLineCntMin;

    @Override
    public final int getShapeLineCntMin() {
        return this.shapeLineCntMin;
    }

    @Override
    @Required
    public final void setShapeLineCntMin(int cntmin) {
        this.shapeLineCntMin = cntmin;
    }

    // ####
    private int shapeLineCntTotal;

    @Override
    public final int getShapeLineCntTotal() {
        return this.shapeLineCntTotal;
    }

    @Override
    @Required
    public final void setShapeLineCntTotal(int cnttotal) {
        this.shapeLineCntTotal = cnttotal;

    }

    // CONTENT RECOGNITION

    // ####
    private int contentLineMinCnt;

    @Override
    public final int getContentLineCntMin() {
        return this.contentLineMinCnt;
    }

    @Override
    @Required
    public final void setContentLineCntMin(int cntmin) {
        this.contentLineMinCnt = cntmin;

    }

    // ####
    private int contentLineCntTotal;

    @Override
    public final int getContentLineCntTotal() {
        return this.contentLineCntTotal;
    }

    @Override
    @Required
    public final void setContentLineCntTotal(int cnttotal) {
        this.contentLineCntTotal = cnttotal;

    }

    // SHAPE AND ITS CONSTRAINTS

    @Override
    public Shape shape() throws HTException {
        final String classname = getShapeClassname();
        if (!nn(classname)) {
            throw new HTException(
                    HTException.CONFIG_CORRECTNESS,
                    "No classname for shape (Shape) found in the transform configuration."
                            + " Mode=" + type().name());
        }
        try {
            final Class<?> shapeClass = Class.forName(classname);
            final Class<?>[] parameterTypes = {Double.class};
            final Constructor<?> constructor = shapeClass
                    .getConstructor(parameterTypes);
            final Object[] parameters = {getShapeRectangularRatioMax()};
            return (Shape) constructor.newInstance(parameters);
        }
        catch(Exception e) {
            throw new HTException(
                    HTException.CONFIG_CORRECTNESS,
                    "Cannot create instance for " + classname + "." + " Mode="
                            + type().name(), e);
        }
    }

    @Override
    public HoughLines shapeLines() throws HTException {
        final String classname = getShapeLinesClassname();
        if (!nn(classname)) {
            throw new HTException(
                    HTException.CONFIG_CORRECTNESS,
                    "No classname for shape-lines (HoughLines) found in the transform configuration."
                            + " Mode=" + type().name());
        }
        try {
            final Class<?> shapeLinesClass = Class.forName(classname);
            final Class<?>[] parameterTypes = {CategorizerConfiguration.class};
            final Constructor<?> constructor = shapeLinesClass
                    .getConstructor(parameterTypes);
            final Object[] parameters = {this};
            return (HoughLines) constructor.newInstance(parameters);
        }
        catch(Exception e) {
            throw new HTException(HTException.SYSTEM,
                    "Cannot create instance for " + classname + "." + " Mode="
                            + type().name(), e);
        }
    }

    // ###
    private String shapeClassname;

    @Override
    public final String getShapeClassname() {
        return this.shapeClassname;
    }

    @Override
    @Required
    public final void setShapeClassname(String classname) {
        this.shapeClassname = classname;
    }

    // ####
    private String shapeLineClassname;

    @Override
    public final String getShapeLineClassname() {
        return this.shapeLineClassname;
    }

    @Override
    @Required
    public final void setShapeLineClassname(String classname) {
        this.shapeLineClassname = classname;
    }

    // ###
    private String shapeLinesClassname;

    @Override
    public final String getShapeLinesClassname() {
        return this.shapeLinesClassname;
    }

    @Override
    @Required
    public final void setShapeLinesClassname(String classname) {
        this.shapeLinesClassname = classname;
    }

    // ###
    private double maxShapeRectangularRatio;

    @Override
    public final double getShapeRectangularRatioMax() {
        return this.maxShapeRectangularRatio;
    }

    @Override
    @Required
    public final void setShapeRectangularRatioMax(double ratio) {
        this.maxShapeRectangularRatio = ratio;
    }

    // ###
    private double shapeRadius;

    @Override
    public final double getShapeRadius() {
        return this.shapeRadius;
    }

    @Override
    @Required
    public final void setShapeRadius(double ratio) {
        this.shapeRadius = ratio;
    }

    // SHAPE-FILTER

    @Override
    public final boolean shapeFilterUsage() {
        return null != this.shapeFilterClassname
                && 0 != this.shapeFilterClassname.trim().length();
    }

    // ###
    private String shapeFilterClassname;

    @Override
    public final String getShapeFilterClassname() {
        return this.shapeFilterClassname;
    }

    @Override
    @Required
    public final void setShapeFilterClassname(final String classname) {
        if (null == classname || 0 == classname.trim().length()) {
            shapeFilterClassname = null;
        }
        else {
            this.shapeFilterClassname = classname;
        }
    }

    // ###
    private int shapeFilterMargin;

    @Override
    public final int getShapeFilterMargin() {
        return this.shapeFilterMargin;
    }

    @Override
    @Required
    public final void setShapeFilterMargin(int procent) {
        this.shapeFilterMargin = procent;
    }

    // IMAGE FILTERS

    // ####
    private List<String> imageFilters;

    @Override
    public final List<String> getImageFilters() {
        return this.imageFilters;
    }

    @Override
    @Required
    public final void setImageFilters(final List<String> classnames) {
        this.imageFilters = classnames;
    }

    //
    // IMAGE QAULITY CLASSIFICATION
    //

    @Override
    public final ImageQuality imageQuality(int width, int height) {
        final int diagonal = (int) Util.diagonal(width, height);
        final int highMax = getImageHighMax();
        final int normalMax = getImageNormalMax();
        final int lowMax = getImageLowMax();
        final int lowMin = getImageLowMin();

        if (highMax < diagonal) {
            return ImageQuality.TOO_LARGE;
        }
        else if (diagonal < lowMin) {
            return ImageQuality.TOO_SMALL;
        }
        else if (normalMax < diagonal && diagonal <= highMax) {
            return ImageQuality.HIGH;
        }
        else if (lowMax < diagonal && diagonal <= normalMax) {
            return ImageQuality.NORMAL;
        }
        else if (lowMin < diagonal && diagonal <= lowMax) {
            return ImageQuality.LOW;
        }
        else {
            throw new IllegalStateException(
                    "Cannot derive quality for image-diagonal " + diagonal);
        }
    }

    // ###
    private double imageErrorMax;

    @Override
    public double getImageErrorMax() {
        return this.imageErrorMax;
    }

    @Override
    @Required
    public void setImageErrorMax(double widthHeightRatio) {
        this.imageErrorMax = widthHeightRatio;
    }

    // ####
    private int imageHighMax;

    @Override
    public final int getImageHighMax() {
        return this.imageHighMax;
    }

    @Override
    @Required
    public final void setImageHighMax(int pixel) {
        this.imageHighMax = pixel;
    }

    // ####
    private int imageNormalMax;

    @Override
    public final int getImageNormalMax() {
        return this.imageNormalMax;
    }

    @Override
    @Required
    public final void setImageNormalMax(int pixel) {
        this.imageNormalMax = pixel;
    }

    // ####
    private int imageLowMax;

    @Override
    public final int getImageLowMax() {
        return this.imageLowMax;
    }

    @Override
    @Required
    public final void setImageLowMax(int pixel) {
        this.imageLowMax = pixel;
    }

    // ####
    private int imageLowMin;

    @Override
    public final int getImageLowMin() {
        return this.imageLowMin;
    }

    @Override
    @Required
    public final void setImageLowMin(int pixel) {
        this.imageLowMin = pixel;
    }

    //
    // DEBUGGING / LOGGING
    //

    private static final String NO_FILTERS = " - ";

    @Override
    public String printShapeFilter() {
        final StringBuilder ret = new StringBuilder();
        if (shapeFilterUsage()) {
            ret.append(" margin=" + getShapeFilterMargin());
        }
        else {
            ret.append(NO_FILTERS);
        }
        return ret.toString();
    }

    @Override
    public final String printImageFilters() {
        final StringBuilder ret = new StringBuilder();
        if (null == this.getImageFilters() || 0 == getImageFilters().size()) {
            return NO_FILTERS;
        }
        for (final String classname : getImageFilters()) {
            if (null == classname || 0 == classname.trim().length()) {
                continue;
            }
            final String simpleClassname = classname.substring(1 + classname
                    .lastIndexOf("."));
            ret.append(simpleClassname.replaceFirst("Filter", "") + " ");
        }
        if (0 == ret.length()) {
            ret.append(NO_FILTERS);
        }
        return ret.toString();
    }

    @Override
    public final String printImageQualityClassification() {
        final StringBuilder ret = new StringBuilder();
        ret.append("[" + getImageLowMin());
        ret.append(" " + getImageLowMax());
        ret.append(" " + getImageNormalMax());
        ret.append(" " + getImageHighMax() + "]");
        return ret.toString();
    }

    @Override
    public final String printNeighbourhoodSizes() {
        final StringBuilder ret = new StringBuilder();
        ret.append("[" + getNeighbourhoodSizeLow());
        ret.append(" " + getNeighbourhoodSizeNormal());
        ret.append(" " + getNeighbourhoodSizeHigh() + "]");
        return ret.toString();
    }

    @Override
    public final String print() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" theta = " + getMaxTheta() + "discrete values");
        ret.append(" threshold = " + getThreshold() + "%");
        ret.append(" neighbourhood = " + printNeighbourhoodSizes() + "px");
        if (null != this.getImageFilters() && 0 < getImageFilters().size()) {
            ret.append(" ImageFilters: " + printImageFilters());
        }
        else {
            ret.append(" ImageFilters: -");
        }
        ret.append(" ShapeFilter:" + printShapeFilter());
        return ret.toString();
    }

    // HELPERS

    private static final boolean nn(final String x) {
        return x != null && 0 != x.trim().length();
    }
}
