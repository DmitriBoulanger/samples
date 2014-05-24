package de.dbo.samples.image.houghtransform.api;

import java.awt.image.BufferedImage;

/**
 * Public (application) interface of available Categorizers.
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Categorizer {

    public abstract Category getCategory(final BufferedImage image) throws CategorizerException;

}