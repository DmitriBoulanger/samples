package de.dbo.samples.image.houghtransform;

import de.dbo.samples.image.houghtransform.filter.MandatoryPointBasedThresholdFilter;

import java.awt.image.BufferedImage;

public final class Util {
	
	 public static final BufferedImage applyMandatoryFilter(final BufferedImage imageOrigin) {
	    	return new MandatoryPointBasedThresholdFilter().filter(imageOrigin, null);
	 }

}
