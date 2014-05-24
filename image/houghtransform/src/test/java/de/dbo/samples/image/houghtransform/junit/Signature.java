package de.dbo.samples.image.houghtransform.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.data.ImageCollections;
import de.dbo.samples.image.houghtransform.data.ImageProvider;
import de.dbo.samples.image.houghtransform.gui.impl.JUnit;

/**
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public class Signature extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(Signature.class);

    @Test
    public void signature_test_0() throws Exception {
        final String title = "Signature Test 0";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_TEST0), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void signature_test_1() throws Exception {
        final String title = "Signature Test 1";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_TEST1), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void signature_test_2() throws Exception {
        final String title = "Signature Test 2";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_TEST2), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void signature_test_Problems() throws Exception {
        final String title = "Signature Test Problems";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_PROBLEM), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void signature_test_CHECKED() throws Exception {
        final String title = "Signature Performance - CHECKED";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_PERFORMANCE_CHECKED), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void signature_test_UNCHECKED() throws Exception {
        final String title = "Signature Performance - UNCHECKED";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.SIGNATURE_PERFORMANCE_UNCHECKED), title, Marker.SIGNATURE);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

}
