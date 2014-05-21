package de.dbo.samples.image.houghtransform.core;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.api.OMRMarker;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;

/**
 *
 * @author D.Boulanger ITyX GmbH
 *
 */
public class DeveloperTestJUnit extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(DeveloperTestJUnit.class);

    @Test
    @Ignore
    public void developer_test() throws Exception {
        final String title = "Developer Test";
        log.debug(title + " ...");
        try {
            processAndAssertImages(new ImageProvider(ImageCollections.BOX_TEST0), title, OMRMarker.BOX);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

}
