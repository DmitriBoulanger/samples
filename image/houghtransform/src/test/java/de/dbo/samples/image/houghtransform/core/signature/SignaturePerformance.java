package de.dbo.samples.image.houghtransform.core.signature;


import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRMarker;
import de.dbo.samples.image.houghtransform.testimpl.JUnit;


public final class SignaturePerformance extends JUnit {
    private static final Logger log = LoggerFactory.getLogger(SignaturePerformance.class);

    public static final void main(final String[] args) throws Exception {
        final SignaturePerformance signaturePerformance = new SignaturePerformance();
        signaturePerformance.checked();
        signaturePerformance.unchecked();
    }

    private final String performanceSubdir;

    /**
     * Default data-set located in the default directory with performance-data (PNG-images)
     */

    public SignaturePerformance() throws Exception {
        this.performanceSubdir = performanceSubdir();
    }

    private static final String performanceSubdir() throws Exception {
        final Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("signature-performance.properties"));
        }
        catch(IOException e) {
            throw new Exception("Can't load signature-performance.properties:", e);
        }
        final String ret = properties.getProperty("performanceSubdir");
        if (null == ret) {
            throw new Exception("signature-performance.properties doesn't have value for key=performanceSubdir");
        }
        return ret;
    }

    public void checked() throws Exception {
        final String title = "Signature Performance (CHECKED)";
        log.debug(title + " ...");
        final ImageProvider imageProvider = new ImageProvider(ImageCollections.SIGNATURE_PERFORMANCE_CHECKED, performanceSubdir);
        try {
            doIt(title, imageProvider);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }


    public void unchecked() throws Exception {
        final String title = "Signature Performance (UNCHECKED)";
        log.debug(title + " ...");
        try {
            final ImageProvider imageProvider = new ImageProvider(ImageCollections.SIGNATURE_PERFORMANCE_UNCHECKED);
            doIt(title, imageProvider);
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }

    private final void doIt(final String title, final ImageProvider imageProvider) throws Exception {
        try {
            final double result = processAndCheckImages(imageProvider, title, OMRMarker.SIGNATURE);
            if (result < 0.01) {
                System.out.println("No errors in " + title + ": " + result);
            }
            else {
                System.out.println("Errors in " + title + ": " + result);
            }
        }
        catch(Exception e) {
            handleException(title, e);
        }
    }



}
