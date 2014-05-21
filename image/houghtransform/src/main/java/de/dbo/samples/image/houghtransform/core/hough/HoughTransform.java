package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.api.OMRCategorizerWorker;
import de.dbo.samples.image.houghtransform.api.OMRImageQuality;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;

public interface HoughTransform extends OMRCategorizerWorker {

    public abstract int imageWidth();

    public abstract int imageHeight();

    public abstract CategorizerConfiguration configuration();

    public abstract OMRImageQuality imageQuality();

    public abstract String imageName();

    public abstract BufferedImage image();

    public abstract BufferedImage imageFiltered();

    public abstract Hough hough();

}