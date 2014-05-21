package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.image.BufferedImage;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerWorker;
import de.dbo.samples.image.houghtransform.api.ImageQuality;

public interface HoughTransform extends CategorizerWorker {

    public abstract int imageWidth();

    public abstract int imageHeight();

    public abstract CategorizerConfiguration configuration();

    public abstract ImageQuality imageQuality();

    public abstract String imageName();

    public abstract BufferedImage image();

    public abstract BufferedImage imageFiltered();

    public abstract Hough hough();

}