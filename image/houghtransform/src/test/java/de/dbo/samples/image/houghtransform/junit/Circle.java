package de.dbo.samples.image.houghtransform.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRMarker;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

/**
 * Tests of the samples
 *
 * @author boulanger
 */
public class Circle extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(Circle.class);

    @Test
    public void circle_test() throws Exception {
        final String title = "Circle Test";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.CIRCLE_TEST0)
                    , title, OMRMarker.CIRCLE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void circle_features() throws Exception {
        final String title = "Circle Features";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.CIRCLE_FEATURES)
                    , title, OMRMarker.CIRCLE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void circle_samples_normal_0() throws Exception {
        final String title = "Circle Samples normal 0";
        log.debug(title + " ...");
        try {
            final ImageProvider imageProvider = new ImageProvider(ImageCollections.CIRCLE_SAMPLES_NORMAL0);
            processAndAssertImages(imageProvider, title, OMRMarker.CIRCLE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

}
