package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.core.Util;

/**
 * Hough transform for searching circles
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class HoughAlgorithmCircle extends HoughAbstraction {

    private final int accSize;
    private final int r;
    private final int r2;
    private final int r2Min;
    private final int r2Max;

    public HoughAlgorithmCircle(final Integer width, final Integer height,
            final CategorizerConfiguration cfg, HoughLines houghLines) {
        super(width, height, cfg, houghLines);
        
        this.r = (int) super.getRadius();
        this.r2 = this.r * this.r;
        this.r2Min = (int) (((this.r) * cfg.getContentLineCenterTolerance())
            * ((this.r) * cfg.getContentLineCenterTolerance()));
        this.r2Max = (int) (((this.r) * cfg.getShapeLineCenterTolerance())
            * ((this.r) * cfg.getShapeLineCenterTolerance()));
        this.accSize = cfg.getShapeLineCntTotal();
    }

    @Override
    protected final void initialise() {
        this.houghArraySizeX = this.getWidth();
        this.houghArraySizeY = this.getHeight();
        this.houghArray = new int[houghArraySizeX][houghArraySizeY];
        for (int x = 0; x < houghArraySizeX; x++) {
            for (int y = 0; y < houghArraySizeY; y++) {
                this.houghArray[x][y] = 0;
            }
        }

        // cache the values of sin and cos for faster processing
        this.sinCache = new double[360];
        this.cosCache = this.sinCache.clone();
        for (int t = 0; t < 360; t++) {
            double realTheta = t * thetaStep;
            this.sinCache[t] = Math.sin(realTheta);
            this.cosCache[t] = Math.cos(realTheta);
        }
    }

    @Override
    public final void addPoint(int x, int y) {
        // use only black pixels in the radius-corridor
        final int centerDistance2 = (int) Util.distance2(x, y, getCenterX(), getCenterY());
        if (centerDistance2 <= r2Min || centerDistance2 >= r2Max) {
            return;
        }
        // make all circles with radius r that contain the point (x,y)
        int x0, y0;
        for (int theta = 0; theta < 360; theta++) {
            x0 = (int) Math.round(x - this.r * cosCache[theta]);
            y0 = (int) Math.round(y - this.r * sinCache[theta]);
            if (x0 > 0 && y0 > 0
                    && x0 < this.getWidth() && y0 < this.getHeight()) {
                this.houghArray[x0][y0] += 1;
            }
        }
        numPoints++;
    }

    @Override
    public final void generateLines() throws CategorizerException {
        // Only proceed if the hough array is not empty
        if (this.numPoints == 0) {
            return;
        }

        final int[] results = new int[this.accSize * 3];
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                final int value = this.houghArray[x][y];
                if (value < threshold) {
                    continue;
                }

                // if its higher than lowest value add it and then sort
                if (value > results[(this.accSize - 1) * 3]) {

                    // add to bottom of array
                    results[(this.accSize - 1) * 3] = value;
                    results[(this.accSize - 1) * 3 + 1] = x;
                    results[(this.accSize - 1) * 3 + 2] = y;

                    // shift up until its in the right place
                    int i = (this.accSize - 2) * 3;
                    while ((i >= 0) && (results[i + 3] > results[i])) {
                        for (int j = 0; j < 3; j++) {
                            int temp = results[i + j];
                            results[i + j] = results[i + 3 + j];
                            results[i + 3 + j] = temp;
                        }
                        i = i - 3;
                        if (i < 0) {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = this.accSize - 1; i >= 0; i--) {
            final int value = results[i * 3];
            final int centerX = results[i * 3 + 1];
            final int centerY = results[i * 3 + 2];
            Rectangle rectangle = circle(this.r, centerX, centerY);

            final HoughLine houghLine = houghLineInstance(rectangle, value);
            houghLines.add(houghLine);

        }
        houghLines.complete();
    }

    private HoughLine houghLineInstance(Rectangle rectangle, int peak)
            throws CategorizerException {
        final String classname = cfg.getShapeLineClassname();
        if (null == classname || 0 == classname.trim().length()) {
            throw new CategorizerException(
                    CategorizerException.CONFIG_CORRECTNESS,
                    "No class-name for shape-line found in the transform configuration");
        }
        try {
            final Class<?> clazz = Class.forName(classname);
            final Class<?>[] types = {Rectangle.class, Integer.class,
                    Hough.class};
            final Object[] params = {rectangle, new Integer(peak), this};
            final Constructor<?> constructor = clazz.getConstructor(types);
            return (HoughLine) constructor.newInstance(params);
        }
        catch(Exception e) {
            throw new CategorizerException(CategorizerException.SYSTEM,
                    "Cannot create instance for " + classname, e);
        }
    }

    private final Rectangle circle(int r, int centerX, int centerY) {
        final Polygon polygon = new Polygon();

        polygon.addPoint(centerX, centerY + r);
        polygon.addPoint(centerX, centerY - r);
        polygon.addPoint(centerX + r, centerY);
        polygon.addPoint(centerX - r, centerY);

        int x = 1;
        int y = (int) (Math.sqrt(r2 - 1) + 0.5);

        while (x < y) {
            polygon.addPoint(centerX + x, centerY + y);
            polygon.addPoint(centerX + x, centerY - y);
            polygon.addPoint(centerX - x, centerY + y);
            polygon.addPoint(centerX - x, centerY - y);
            polygon.addPoint(centerX + y, centerY + x);
            polygon.addPoint(centerX + y, centerY - x);
            polygon.addPoint(centerX - y, centerY + x);
            polygon.addPoint(centerX - y, centerY - x);
            x += 1;
            y = (int) (Math.sqrt(r2 - x * x) + 0.5);
        }
        if (x == y) {
            polygon.addPoint(centerX + x, centerY + y);
            polygon.addPoint(centerX + x, centerY - y);
            polygon.addPoint(centerX - x, centerY + y);
            polygon.addPoint(centerX - x, centerY - y);
        }
        return polygon.getBounds();
    }

    /**
     * Gets the hough array as an image, in case you want to have a look at it.
     * This method is only needed for developing/debugging/testing
     */
    @Override
    public BufferedImage getHoughArrayImage() {
        final int max = getMaxHoughValue().value;
        final BufferedImage image = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int t = 0; t < this.getWidth(); t++) {
            for (int r = 0; r < this.getHeight(); r++) {
                int houghValue = this.houghArray[t][r];
                double value = 255 * ((double) houghValue) / max;
                int v = 255 - (int) value;
                int c = new Color(v, v, v).getRGB();
                image.setRGB(t, r, c);
            }
        }
        return image;
    }

}
