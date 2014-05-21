package de.dbo.samples.image.houghtransform.api;

import java.awt.Rectangle;
import java.awt.image.BufferedImageOp;

/**
 * BufferedImageOp-Filter to drop all black pixels outside the marker shape.
 * Typically these filters are used in a HT-CategorizerImpl having mode=CONTENT. A
 * shape-filter is applied as the first cleaning operation before other input
 * filters (the other elements in the filter-vector are noise-reduction, sharp,
 * etc.)
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public interface ShapeFilter extends BufferedImageOp {

    /**
     *
     * @return actual rectangle of this filter
     */
    public abstract Rectangle getRectangle();

}
