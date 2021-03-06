package de.dbo.samples.image.houghtransform;

import de.dbo.samples.image.houghtransform.api.Categorizer;
import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.core.CategorizerImpl;
import de.dbo.samples.image.houghtransform.core.hough.HoughAlgorithmCircle;
import de.dbo.samples.image.houghtransform.core.hough.HoughAlgorithmLinear;

/**
 * Marker-image categorization. There are several types of markers that can be
 * categorized.
 *
 * BOX-Marker Categorization
 *
 * The categorizer uses the linear Hough-algorithm in the both phases.
 *
 * The first phase (shape recognition) searches for strait lines that are
 * categorized in box-lines, check-lines (content) and unknown-lines. If
 * shape-lines are not found or if the shape (box) is not well-defined, the
 * categorizer returns the UNKNOWN-category for the input marker.
 *
 * If the shape(box) has been found, then the second phase (content recognition)
 * runs. Firstly the shape (box) from the first phase is used as a image-filter,
 * i.e. all black pixel outside the shape are replaced with white ones. Next,
 * the shape is used to crop the corresponding rectangle from the marker image.
 * This cropped image is used as an input image in the second-phase of the categorizer.
 *
 * The second-phase categorizer uses the same algorithms as the first phase
 * above. However, it looks for content-lines. If there are enough content-lines
 * and they satisfy some constraints, then the CHECKED-category is returned.
 * Otherwise, the UNCHECKED-category is returned
 *
 * BRACKETS-Marker Categorization
 * 
 * This categorizer is very similar to the BOX-Categorizer
 *
 * CIRCLE-Marker Categorization.
 * 
 * This categorizer is also similar to the BOX-Categorizer but it uses 
 * the circle Hough-algorithm in the first phases and the
 * linear Hough-algorithm in the second phase
 * 
 * SIGNATURE-Marker Categorization
 *
 * @see Marker
 * @see Category
 * @see CategorizerConfiguration
 * @see HoughAlgorithmLinear
 * @see HoughAlgorithmCircle
 *
 * @author D.Boulanger Hombach
 */

public final class CategorizerFactory {
	
    public static final Categorizer newInstance(final Marker marker) throws CategorizerException {
        if (null == marker) {
            throw new CategorizerException(CategorizerException.CONFIG_INITILIZATION
                    , "Marker is NULL! Cannot create instance of the CategorizerImpl");
        }
        switch (marker) {

            case SIGNATURE:
                return new CategorizerImpl("signature-cfg.xml");

            case BOX:
                return new CategorizerImpl("box-cfg.xml");

            case CIRCLE:
                return new CategorizerImpl("circle-cfg.xml");

            case BRACKETS:
                return new CategorizerImpl("brackets-cfg.xml");

            default:
                throw new CategorizerException(CategorizerException.CONFIG_INITILIZATION,
                		"Can't create categorizer instance for the marker-type " + marker.name());
        }
    }
}
