package de.dbo.samples.image.houghtransform.signature.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.data.ImageCollections;
import de.dbo.samples.image.houghtransform.testimpl.GuiShowUp;

public final class SignaturePerformanceError extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(SignaturePerformanceError.class);

    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "signature-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUpSignature(ImageCollections.SIGNATURE_PERFORMANCE_ERROR, CTX, ctx);

        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0) + " ms.");
    }
}
