package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.api.HTCategorizerWorker;
import de.dbo.samples.image.houghtransform.api.ImageQuality;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;

public interface HoughTransform extends HTCategorizerWorker {

    public abstract int imageWidth();

    public abstract int imageHeight();

    public abstract CategorizerConfiguration configuration();

    public abstract ImageQuality imageQuality();

    public abstract String imageName();

    public abstract BufferedImage image();

    public abstract BufferedImage imageFiltered();

    public abstract Hough hough();

}