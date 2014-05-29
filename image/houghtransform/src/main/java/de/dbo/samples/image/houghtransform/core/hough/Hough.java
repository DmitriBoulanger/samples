package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.api.CategorizerException;

/**
 * 
 * Basics of the Hough Algorithm
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Hough {

    public abstract int[][] getHoughArray();
    
    public abstract int imagePixel();

    /**
     * Adds points from an image. The image is assumed to be grey-scale black
     * and white, so all pixels that are not white are counted as edges. The
     * image should have the same dimensions as the one passed to the
     * constructor.
     */
    public abstract void addPoints(BufferedImage image);

    /**
     * Once points have been added in some way this
     * method extracts lines from the hough-array
     * @see Hough#addPoints(BufferedImage)
     *
     * @throws CategorizerException
     */
    public abstract void generateLines(BufferedImage image) throws CategorizerException;

    /**
     * Once lines have been already generated this method returns
     * reference to the loaded HoughLines object
     */
    public abstract HoughLines getHoughLines();

    /**
     * Once points have been added in some way,
     * this method gets the highest value in the hough array
     */
    public abstract HoughValue getMaxHoughValue();

    /**
     * Gets the hough array as an image, in case you want to have a look at it.
     * This methods is only needed for developing/debugging/testing
     */
    public abstract BufferedImage getHoughArrayImage();

    /**
     *
     * @return number of points accumulated in the hough array
     */
    public abstract int getNumPoints();

    /**
     * configuration parameter adjusted to the highest value in the hough array
     * @return  minimal peak-value for a line to be considered as part of a shape
     */
    public abstract int getShapePeakMin();

    /**
     * configuration parameter adjusted to the highest value in the hough array
     * @return  minimal peak-value for a line to be considered as part of a content
     */
    public abstract int getContentPeakMin();

    public int peakMax();

    /**
     * @return width of the image
     */
    public int getWidth();

    /**
     * height of the image
     * @return
     */
    public int getHeight();

    /**
     * configuration parameter adjusted to the highest value in the hough array
     * @return expected radius of the shape
     */
    public double getRadius();

    /**
     * @return X-coordinate of the image center
     */
    public double getCenterX();

    /**
     * @return Y-coordinate of the image center
     */
    public double getCenterY();

    /**
     * @return length of the image diagonal
     */
    public double getDiagonal();

    /**
     * @return pretty-print for debugging/logging
     */
    public abstract String print();
}