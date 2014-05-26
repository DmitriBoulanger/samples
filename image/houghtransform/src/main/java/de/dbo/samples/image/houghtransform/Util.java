package de.dbo.samples.image.houghtransform;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.filter.MandatoryPointBasedThresholdFilter;

public final class Util {

	 public static final BufferedImage applyMandatoryFilter(final BufferedImage imageOrigin) {
	    	return new MandatoryPointBasedThresholdFilter().filter(imageOrigin, null);
	 }

    public static final Rectangle whiteBorder(final BufferedImage image, double percent) {
        if (percent < 0.001D) {
            return null;
        }
        final int deltaX = (int) ((image.getWidth()) * percent);
        final int deltaY = (int) ((image.getHeight()) * percent);
        return new Rectangle(deltaX, deltaY, image.getWidth() - 2 * deltaX, image.getHeight() - 2 * deltaY);
    }

}
