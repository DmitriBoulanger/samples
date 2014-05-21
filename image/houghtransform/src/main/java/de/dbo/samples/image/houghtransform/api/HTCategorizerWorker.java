package de.dbo.samples.image.houghtransform.api;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Categorizer Worker.
 * 
 * @author D.Boulanger ITyX GmbH
 * 
 */
public interface HTCategorizerWorker {

    /**
     * Mode of this worker. It can be an initial mode (shape finder) or it can
     * be content categorization (content analyzing). Typically the initial mode
     * is used to find the marker shape and generate the corresponding shape
     * filter. On the other hand, the content analyzing is aimed at discovering
     * whether the content of the shape has an expected market (if yes, the
     * shape is checked)
     * 
     * @return categorizer mode
     * @see HTCategory
     */
    public abstract HTCategorizerMode getMode();

    /**
     * 
     * @return true only if the expected shape found
     */
    public abstract boolean isShapeFound();

    /**
     * 
     * @return true only if the expected shape found it can be used as a
     *         shape-filter for subsequent processing
     * 
     * @see #isShapeFound()
     */
    public abstract boolean isShapeFilterWelldefined();

    /**
     * 
     * @return non-null filtered image only if the expected shape found and it
     *         can be used as a shape-filter
     * 
     * @see #isShapeFilterWelldefined()
     */
    public abstract BufferedImage getShapeFilteredImage()
            throws HTException;

    /**
     * 
     * @return non-null cropped image only if the expected shape found and it
     *         can be used as a shape-filter
     * 
     * @see #isShapeFilterWelldefined()
     */
    public BufferedImage getShapeCroppedImage() throws HTException;

    /**
     * 
     * @return non-null filter image only if the expected shape found and it can
     *         be used as a shape-filter
     * 
     * @see #isShapeFilterWelldefined()
     */
    public abstract ShapeFilter getShapeFilter()
            throws HTException;

    /**
     * image classification/categorization
     * 
     * @return image category (type).
     */
    public abstract HTCategory category();

    /**
     * Basic description of the transformation/categorization
     * 
     * @return vector of text-lines
     */
    public abstract Vector<String> description();

    public abstract Shape getShape();

}