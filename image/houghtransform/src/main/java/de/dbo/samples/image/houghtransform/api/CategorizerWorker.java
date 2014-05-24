package de.dbo.samples.image.houghtransform.api;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Internal (non-application) Categorizer Worker.
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface CategorizerWorker {
	
    /**
     * final image classification/categorization
     * 
     * @return image category (type).
     */
    public abstract Category category();

    /**
     * Mode of this worker. It can be an initial mode (shape finder) or it can
     * be content categorization (content analyzing). Typically the initial mode
     * is used to find the marker shape and generate the corresponding shape filter. 
     * On the other hand, the content analyzing is aimed at discovering
     * whether the content of the shape has an expected marker (if yes, the shape is checked)
     * 
     * @return categorizer mode
     * @see Category
     */
    public abstract CategorizerMode getMode();

    /**
     * 
     * @return true only if the expected shape found
     */
    public abstract boolean isShapeFound();

    /**
     * 
     * @return true only if the expected shape found and it can be used as a
     *         shape-filter for subsequent processing
     * 
     */
    public abstract boolean isShapeFilterWelldefined();

    /**
     * 
     * @return non-null filtered image only if the expected shape found and it
     *         can be used as a shape-filter
     * 
     */
    public abstract BufferedImage getShapeFilteredImage()
            throws CategorizerException;

    /**
     * 
     * @return non-null cropped image only if the expected shape found and it
     *         can be used as a shape-filter
     * 
     */
    public BufferedImage getShapeCroppedImage() throws CategorizerException;

    /**
     * 
     * @return non-null filter image only if the expected shape found and it can
     *         be used as a shape-filter
     * 
     */
    public abstract ShapeFilter getShapeFilter()
            throws CategorizerException;


    /**
     * Basic description of the transformation/categorization
     * 
     * @return vector of text-lines
     */
    public abstract Vector<String> description();

    public abstract Shape getShape();

}