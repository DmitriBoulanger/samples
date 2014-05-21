package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.HoughTransformException;

/**
 *
 * Abstraction for an implementation of the Hough-Transforms
 *
 * @author D. Boulanger ITyX GmbH
 *
 */
public abstract class HoughAbstraction implements Constants, Hough {

    /**
     * reference of the configuration bean
     */
    public final CategorizerConfiguration cfg;

    // the hough array
    protected int[][]                     houghArray;
    protected int                         houghArraySizeX;
    protected int                         houghArraySizeY;

    // accumulated lines
    protected final HoughLines            houghLines;

    // the width and height of the image
    private final int                     width, height;

    // the coordinates of the center of the image
    private final double                  centerX, centerY;

    // expected abstract radius
    private final double                  radius;

    // the height of the hough array
    public final int                      houghHeight;

    private int                           shapePeakMin   = -1;
    private int                           contentPeakMin = -1;
    private int                           peakMax        = -1;

    // double the hough height (allows for negative numbers)
    protected final int                   doubleHeight;

    // the number of points that have been added
    protected int                         numPoints;

    // cache of values of sin and cos for different theta values.
    // Has a significant performance improvement!
    protected double[]                    sinCache;
    protected double[]                    cosCache;

    // Using maxTheta, work out the theta-step in the constructor
    protected final double                thetaStep;

    // lenght of the image-diagonal
    private final double                  diagonal;

    // height of the triangle
    public final int                      h;

    // generated value of the maximum in the hough array
    private HoughValue                    maxHoughValue;

    // configuration parameter adjusted the diagonal of the image
    protected final double                threshold;

    /**
     * Initializes the hough transform. The dimensions of the input image are
     * needed in order to initialize the hough array.
     *
     * @param width
     *            The width of the image
     * @param height
     *            The height of the image
     */
    protected HoughAbstraction(int width, int height, final CategorizerConfiguration cfg, HoughLines houghLines) {
        this.cfg = cfg;
        this.width = width;
        this.height = height;
        this.houghLines = houghLines;
        this.thetaStep = PI / cfg.getMaxTheta();
        this.diagonal = Util.diagonal(width, height);
        this.h = (int) ((width * height) / this.diagonal);
        this.threshold = Util.ratio(this.diagonal, this.cfg.getThreshold());

        // calculate the maximum height the hough array needs to have (pixel)
        this.houghHeight = (int) (SQRT22 * Math.max(this.height, this.width));

        // double the height of the hough array to cope with negative r-values
        this.doubleHeight = 2 * this.houghHeight;

        // Find edge points and vote in array
        this.centerX = this.width / 2;
        this.centerY = this.height / 2;
        this.radius = (int) (cfg.getShapeRadius() * this.diagonal);

        // Count how many points there are
        this.numPoints = 0;

        // Set all needed parameters, values, etc.
        initialise();
    }
    
    /**
     * Initializes the hough array. Called in the constructor
     */
    protected abstract void initialise();

    @Override
    public final int[][] getHoughArray() {
        return this.houghArray;
    }

    /**
     * Gets the highest value in the hough array
     */
    @Override
    public final HoughValue getMaxHoughValue() {
        if (null != maxHoughValue) {
            return maxHoughValue;
        }
        int max = 0;
        int maxX = -1;
        int maxY = -1;
        for (int x = 0; x < this.houghArraySizeX; x++) {
            for (int y = 0; y < this.houghArraySizeY; y++) {
                if (this.houghArray[x][y] > max) {
                    max = this.houghArray[x][y];
                    maxX = x;
                    maxY = y;
                }
            }
        }
        return new HoughValue(max, maxX, maxY);
    }



    @Override
    public final void addPoints(final BufferedImage image) {

        // Now find edge points and update the hough array
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                // Find black pixels // (color &  0x000000ff) == 0
                if (image.getRGB(x, y) == BLACK_COLOR_RGB) {
                    addPoint(x, y);
                }
            }
        }

        this.shapePeakMin = (int) (this.cfg.getShapeLinePeakTolerance() * (this.getMaxHoughValue().value));
        this.contentPeakMin = (int) (this.cfg.getContentLinePeakTolerance() * (this.getMaxHoughValue().value));
        this.peakMax = (int) (1.0D * (this.getMaxHoughValue().value));
    }

    /**
     * Adds a single point to the hough transform. You can use this method
     * directly if your data isn't represented as a buffered image.
     */
    protected abstract void addPoint(int x, int y);

    @Override
    public final HoughLines getHoughLines() {
        return this.houghLines;
    }

    @Override
    public abstract void generateLines() throws HoughTransformException;

    @Override
    public abstract BufferedImage getHoughArrayImage();

    @Override
    public final int getNumPoints() {
        return this.numPoints;
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" " + this.width + "x" + this.height);
        sb.append(" diagonal=" + ((int) Util.round1(this.diagonal)));
        sb.append(" center=[" + ((int) this.centerX) + "," + ((int) this.centerY) + "]");
        return sb.toString();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getCenterX() {
        return centerX;
    }

    @Override
    public double getCenterY() {
        return centerY;
    }

    @Override
    public double getDiagonal() {
        return diagonal;
    }

    @Override
    public int getShapePeakMin() {
        return shapePeakMin;
    }

    @Override
    public int getContentPeakMin() {
        return contentPeakMin;
    }

    @Override
    public int peakMax() {
        return peakMax;
    }

}
