package de.dbo.samples.image.houghtransform.signature.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.testimpl.GuiShowUp;

public final class Signature0 extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(Signature0.class);

    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "omr-signature-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUpSignature(ImageCollections.SIGNATURE_TEST0, CTX, ctx);
        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0) + " ms.");
    }
}
