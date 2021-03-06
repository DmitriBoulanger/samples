package de.dbo.samples.image.houghtransform.core.shapes.signature;

import static de.dbo.samples.image.houghtransform.core.Constants.WHITE_COLOR_RGB;

import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.core.hough.HoughFilter;

import java.awt.Rectangle;

/**
 * A filter which drops all pixels outside the box.
 */

public final class SignatureFilter extends HoughFilter {

    private static final int BORDER = 30;

    public SignatureFilter(final Shape shape, Integer marginprocent) {
        super(shape, marginprocent);
    }

    @Override
    protected int[] filterPixels(int width, int height, int[] inPixels, Rectangle transformedSpace) {
        int index = 0;
        int pixel;
        final Rectangle rect = new Rectangle(
                transformedSpace.x + BORDER, transformedSpace.y + BORDER,
                transformedSpace.width - 2 * BORDER, transformedSpace.height - 2 * BORDER);

        final int[] outPixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //                pixel = rectangle.contains(x, y) ? inPixels[index] : WHITE_COLOR_RGB;
                pixel = rect.contains(x, y) ? inPixels[index] : WHITE_COLOR_RGB;
                outPixels[index++] = pixel;
            }
        }
        return outPixels;
    }
}
