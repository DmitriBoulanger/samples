package de.dbo.samples.image.houghtransform.performance;


import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.data.ImageCollectionCatalog;
import de.dbo.samples.image.houghtransform.data.ImageProvider;
import de.dbo.samples.image.houghtransform.impl.ImageAssertions;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SignaturePerformance extends ImageAssertions {
    private static final Logger log = LoggerFactory.getLogger(SignaturePerformance.class);

    public static final void main(final String[] args) throws Exception{
    	log.info("Checked performance ...");
    	new  SignaturePerformance().checked();
    	log.info("Unchecked performance ...");
    	new  SignaturePerformance().unchecked();
    }
   
    /**
     * Default data-set located in the default directory with performance-data (PNG-images)
     */

    public SignaturePerformance() throws Exception {
 
    }

    public void checked() throws Exception {
        final String title = "Signature Performance (CHECKED)";
        log.debug(title + " ...");
        try {
            doIt(title, new ImageProvider(ImageCollectionCatalog.SIGNATURE_PERFORMANCE_CHECKED));
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    public void unchecked() throws Exception {
        final String title = "Signature Performance (UNCHECKED)";
        log.debug(title + " ...");
        try {
            doIt(title, new ImageProvider(ImageCollectionCatalog.SIGNATURE_PERFORMANCE_UNCHECKED));
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    private final void doIt(final String title, final ImageProvider imageProvider) throws Exception {
        final DecimalFormat df = new DecimalFormat("0.00");
    	try {
            final double result = processAndCheckImages(imageProvider, title, Marker.SIGNATURE);
            if (result < 0.01) {
                log.debug("No errors in " + title + ": " + df.format(result));
            }
            else {
                log.error("Errors in " + title + ": " + df.format(result));
            }
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }
}
