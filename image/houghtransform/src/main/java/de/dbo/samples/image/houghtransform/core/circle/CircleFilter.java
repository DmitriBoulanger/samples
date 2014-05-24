package de.dbo.samples.image.houghtransform.core.circle;

import java.awt.Rectangle;

import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.core.hough.HoughFilter;
import de.dbo.samples.image.houghtransform.core.hough.Util;

/**
 * A filter which drops all pixels outside the shape.
 */

public final class CircleFilter extends HoughFilter {

    private final double centerX;
    private final double centerY;
    private final double radius2;

    public CircleFilter(final Shape shape, Integer marginprocent) {
        super(shape, marginprocent);
        final double diagonal = Util
                .diagonal(rectangle.width, rectangle.height);
        centerX = (rectangle.x) + ((rectangle.width) * 0.5D);
        centerY = (rectangle.y) + ((rectangle.height) * 0.5D);
        radius2 = (diagonal * 0.5D) * (diagonal * 0.5D);
    }

    @Override
    protected int[] filterPixels(int width, int height, int[] inPixels, final Rectangle transformedSpace) {
        int index = 0;
        int pixel;
        final int[] outPixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = (Util.distance2(x, y, centerX, centerY) < radius2) ? inPixels[index]
                        : WHITE_COLOR_RGB;
                outPixels[index++] = pixel;
            }
        }
        return outPixels;
    }
}
