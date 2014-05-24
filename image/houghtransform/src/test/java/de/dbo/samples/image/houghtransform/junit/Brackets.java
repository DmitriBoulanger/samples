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
public class Brackets extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(Brackets.class);

    @Test
    public void brackets_test() throws Exception {
        final String title = "Brackets Test";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BRACKETS_TEST)
                    , title, Marker.BRACKETS);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    @Test
    public void brackets_samples_normal_0() throws Exception {
        final String title = "Brackets Samples normal 0";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BRACKETS_SAMPLES_NORMAL0)
                    , title, Marker.BRACKETS);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

}
