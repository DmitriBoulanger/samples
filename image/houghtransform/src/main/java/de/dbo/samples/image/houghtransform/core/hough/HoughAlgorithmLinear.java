package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;

/*

 Implementation of the Linear Hough-Algorithm

 This algorithm is used to find straight lines in an image
 Its implementation is based on original code from: http://homepages.inf.ed.ac.uk/rbf/HIPR2/hough.htm
 See also: Finding Straight Lines with the Hough Transform http://vase.essex.ac.uk/software/HoughTransform/

 If you represent a line as:  x*cos(theta) + y*sin (theta) = r and you know values of x and y,
 you can calculate all the values of r by going through all the possible values of theta.
 If you plot the values of r on a graph for every value of theta you get a sinusoidal curve.
 This is the Hough transformation.

 The hough transform works by looking at a number of such x,y coordinates, which are usually found by some kind of edge detection.
 Each of these coordinates is transformed into an r, theta curve. This curve is discreet,
 so we actually only look at a certain discrete number of theta values.
 "Accumulator" cells in a hough array along this curve are incremented for X and Y coordinate.

 The accumulator space is plotted rectangularly with theta on one axis and r on the other.
 Each point in the array represents an (r, theta) value which can be used to represent a line using the formula above.

 Once all the points have been added the transform, it should be full of curves.
 The algorithm then searches for local peaks in the array.
 The higher the peak the more values of x and y crossed along that curve, so high peaks give good indications of a line.

 */

public final class HoughAlgorithmLinear extends HoughAbstraction {

    /**
     * Initializes the hough transform. The dimensions of the input image are
     * needed in order to initialize the hough array.
     *
     * @param width
     *            The width of the image
     * @param height
     *            The height of the image
     * @param houghLines
     *            accumulator for extracted hough-lines
     */
    public HoughAlgorithmLinear(final Integer width, final Integer height, final CategorizerConfiguration cfg, final HoughLines houghLines) {
        super(width, height, cfg, houghLines);
    }

    /**
     * Completes the initialization of this instance. The method is called by
     * the constructor in the super-class
     */
    @Override
    protected final void initialise() {

        // Create the hough-array
        this.houghArraySizeX = this.cfg.getMaxTheta();
        this.houghArraySizeY = this.doubleHeight;
        this.houghArray = new int[houghArraySizeX][houghArraySizeY];

        // Cache the values of sin and cos for faster processing
        this.sinCache = new double[this.cfg.getMaxTheta()];
        this.cosCache = this.sinCache.clone();
        for (int t = 0; t < this.cfg.getMaxTheta(); t++) {
            double realTheta = t * this.thetaStep;
            this.sinCache[t] = Math.sin(realTheta);
            this.cosCache[t] = Math.cos(realTheta);
        }
    }

    /**
     * Adds a single black point to the hough transform. You can use this method
     * directly if your data isn't represented as a buffered image.
     */
    @Override
    protected final void addPoint(int x, int y) {

        // Go through each value of theta
        for (int t = 0; t < this.cfg.getMaxTheta(); t++) {

            // work out the r values for each theta step
            int r = (int) (((x - this.getCenterX()) * this.cosCache[t]) + ((y - this.getCenterY()) * this.sinCache[t]));

            // this copes with negative values of r
            r += this.houghHeight;

            if (r < 0 || r >= this.doubleHeight) {
                continue;
            }

            // Increment the hough array
            this.houghArray[t][r]++;
        }

        this.numPoints++;
    }

    /**
     * generates lines saving them in the corresponding HoughLines-instance
     */
    @Override
    public final void generateLines(final BufferedImage image) throws CategorizerException {

        // Only proceed if the hough array is not empty
        if (this.numPoints == 0) {
            return;
        }

        // Search for local peaks above threshold to draw
        final int neighbourhoodSize = this.cfg.neighbourhoodSize(this.getWidth(), this.getHeight());

        // Now only consider points above threshold
        for (int t = 0; t < this.cfg.getMaxTheta(); t++) {
            loop: for (int r = neighbourhoodSize; r < this.doubleHeight - neighbourhoodSize; r++) {

                if (this.houghArray[t][r] > threshold) {

                    int peak = this.houghArray[t][r];

                    // Check that this peak is indeed the local maximum
                    for (int dx = -neighbourhoodSize; dx <= neighbourhoodSize; dx++) {
                        for (int dy = -neighbourhoodSize; dy <= neighbourhoodSize; dy++) {
                            int dt = t + dx;
                            int dr = r + dy;
                            if (dt < 0)
                                dt = dt + this.cfg.getMaxTheta();
                            else if (dt >= this.cfg.getMaxTheta())
                                dt = dt - this.cfg.getMaxTheta();
                            if (this.houghArray[dt][dr] > peak) {
                                // found a bigger point nearby, skip
                                continue loop;
                            }
                        }
                    }

                    // calculate the true value of theta and create the
                    // hough-line
                    final double theta = t * this.thetaStep;
                    final HoughLine houghLine = houghLineInstance(theta, r, peak);
                    ((HoughLineAbstraction)houghLine).collectPixels(image);

                    // add the line to the vector
                    this.houghLines.add(houghLine);
                }
            }
        }
        this.houghLines.complete();
    }

    // this linear hough-algorithms needs the corresponding linear HoughLines instance
    private HoughLine houghLineInstance(double theta, double r, int peak) throws CategorizerException {
        final String classname = cfg.getShapeLineClassname();
        if (null == classname || 0 == classname.trim().length()) {
            throw new CategorizerException(CategorizerException.CONFIG_CORRECTNESS,
                    "No class-name for shape-line found in the transform configuration");
        }
        try {
            final Class<?> lineClass = Class.forName(classname);
            final Class<?>[] filterParameterTypes =
            {Double.class, Double.class, Integer.class, Hough.class};
            final Constructor<?> constructor = lineClass.getConstructor(filterParameterTypes);
            final Object[] lineParameters = {new Double(theta), new Double(r), new Integer(peak), this};
            return (HoughLine) constructor.newInstance(lineParameters);
        }
        catch(Exception e) {
            throw new CategorizerException(CategorizerException.SYSTEM,
                    "Cannot create linear HoughLine-instance for " + classname, e);
        }
    }

    /**
     * Gets the hough array as an image, in case you want to have a look at it.
     * This methods is only needed for developing/debugging/testing
     */
    @Override
    public final BufferedImage getHoughArrayImage() {
        final int max = getMaxHoughValue().value;
        final BufferedImage image = new BufferedImage(this.cfg.getMaxTheta(), this.doubleHeight, BufferedImage.TYPE_INT_ARGB);
        for (int t = 0; t < this.cfg.getMaxTheta(); t++) {
            for (int r = 0; r < this.doubleHeight; r++) {
                double value = 255 * ((double) this.houghArray[t][r]) / max;
                int v = 255 - (int) value;
                int c = new Color(v, v, v).getRGB();
                image.setRGB(t, r, c);
            }
        }
        return image;
    }

    @Override
    public final String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.print());
        sb.append(" hough-height=" + this.houghHeight);
        return sb.toString();
    }

}
