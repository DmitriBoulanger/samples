package de.dbo.samples.image.houghtransform.performance;


import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.data.ImageCollectionCatalog;
import de.dbo.samples.image.houghtransform.data.ImageProvider;
import de.dbo.samples.image.houghtransform.junitImpl.ImageAssertions;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Signature performance.
 * Data-location is specified in performance.properties
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class SignaturePerformance extends ImageAssertions {
    private static final Logger log = LoggerFactory.getLogger(SignaturePerformance.class);

    public static final void main(final String[] args) throws Exception{
    	new  SignaturePerformance().checked();
    	new  SignaturePerformance().unchecked();
    }
  
    public SignaturePerformance() {
 
    }

    public void checked() throws Exception {
        final String title = "Signature-CHECKED Performance";
        log.info(title + " ...");
        try {
            doIt(title, new ImageProvider(ImageCollectionCatalog.SIGNATURE_PERFORMANCE_CHECKED));
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    public void unchecked() throws Exception {
        final String title = "Signature-UNCHECKED Performance";
        log.info(title + " ...");
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
            if (result < 0.001) {
                log.debug("No errors in " + title + ": " + df.format(result));
            }
            else {
            	final DecimalFormat df2 = new DecimalFormat("0.00");
            	final double procent = result * 100.0D;
                log.error("Errors in " + title + ": " + df2.format(procent) + " %");
            }
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }
}
