package de.dbo.samples.image.houghtransform.api;

import java.awt.image.BufferedImage;

/**
 * Public interface of ORM-Categorizers.
 *
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public interface Categorizer {

    public abstract Category getCategory(final BufferedImage image) throws HoughTransformException;

}