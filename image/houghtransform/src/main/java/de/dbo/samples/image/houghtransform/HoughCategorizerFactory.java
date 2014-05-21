package de.dbo.samples.image.houghtransform;

import de.dbo.samples.image.houghtransform.api.HTCategorizer;
import de.dbo.samples.image.houghtransform.api.HTException;
import de.dbo.samples.image.houghtransform.api.HTCategory;
import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.core.Categorizer;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.hough.HoughAlgorithmCircle;
import de.dbo.samples.image.houghtransform.core.hough.HoughAlgorithmLinear;

/**
 * Marker-image categorization. There are sever types of markers that can be
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
 * This cropped image is used as input image in second-phase categorizer.
 *
 * The second-phase categorizer uses the same algorithms as the first phase
 * above. However, it looks for content-lines. If there are enough content-lines
 * and they satisfy some constraints, then the CHECKED-category is returned.
 * Otherwise, the UNCHECKED-category is returned
 *
 * BRACKETS-Marker Categorization
 *
 * CIRCLE-Marker Categorization.
 *
 * The categorizer uses the circle Hough-algorithm in the first phases and the
 * linear Hough-algorithm in the second phase
 *
 * @see Marker
 * @see HTCategory
 * @see CategorizerConfiguration
 * @see HoughAlgorithmLinear
 * @see HoughAlgorithmCircle
 *
 * @author D.Boulanger ITyX GmbH
 */

public final class HoughCategorizerFactory {

    public static final HTCategorizer newInstance(final Marker marker)
            throws HTException {
        if (null == marker) {
            throw new HTException(HTException.CONFIG_INITILIZATION
                    , "OMR-Marker is NULL! Cannot create instance of the HTCategorizer");
        }
        switch (marker) {

            case SIGNATURE:
                return new Categorizer("omr-signature-cfg.xml");

            case BOX:
                return new Categorizer("omr-box-cfg.xml");

            case CIRCLE:
                return new Categorizer("omr-circle-cfg.xml");

            case BRACKETS:
                return new Categorizer("omr-brackets-cfg.xml");

            default:
                throw new HTException(
                        HTException.CONFIG_INITILIZATION,
                        "Can't create categorizer instance for the marker-type "
                                + marker.name());
        }
    }
}
