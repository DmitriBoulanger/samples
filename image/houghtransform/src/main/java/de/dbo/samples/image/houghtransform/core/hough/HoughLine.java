package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

public interface HoughLine {

    /**
     * 
     * @return true only if this line has been classified as an element of the
     *         shape
     */
    public abstract boolean isShape();

    /**
     * 
     * @return true only if this line has been classified as an element of the
     *         content
     */
    public abstract boolean isContent();

    /**
     * 
     * @return true only if this line has not been classified as an element of
     *         the content neither as an element of the shape
     */
    public abstract boolean isUnknown();

    /**
     * @return RGB-color
     */
    public abstract int color();

    /**
     * draws this line on the image using automatically selected RGB color
     */
    public abstract void draw(final BufferedImage image);

    /**
     * pretty-print for this line
     */
    public abstract String print();

}