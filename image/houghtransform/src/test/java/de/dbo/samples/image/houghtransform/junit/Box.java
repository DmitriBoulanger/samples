package de.dbo.samples.image.houghtransform.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRMarker;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

/**
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public class Box extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(Box.class);

    @Test
    public void box_test_0_basic_elements_no_shape() throws Exception {
        final String title = "Box Test 0";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_TEST0)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_test_1_shapes_and_bad_shapes() throws Exception {
        final String title = "Box Test 1";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_TEST1)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_features() throws Exception {
        final String title = "Box Features";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_FEATURES)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_samples_normal_0() throws Exception {
        final String title = "Box Samples normal 0";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_SAMPLES_NORMAL0)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_samples_normal_1() throws Exception {
        final String title = "Box Samples normal 1";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_SAMPLES_NORMAL1)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_samples_normal_2() throws Exception {
        final String title = "Box Samples normal 2";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_SAMPLES_NORMAL2)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_samples_normal_3() throws Exception {
        final String title = "Box Samples normal 3";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_SAMPLES_NORMAL3)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void box_samples_low_0() throws Exception {
        final String title = "Box Samples low 0";
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_SAMPLES_LOW0)
                    , title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

}
