package de.dbo.samples.image.houghtransform.gui.signature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.data.ImageCollectionCatalog;
import de.dbo.samples.image.houghtransform.guiImpl.GuiShowUp;

public final class SignatureTest extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(SignatureTest.class);

    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "signature-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUpSignature(ImageCollectionCatalog.SIGNATURE_PROBLEM, CTX, ctx);

        //        showUp2(ImageCollections.SIGNATURE_TEST0, CTX, ctx);
        //        showUp2(ImageCollections.SIGNATURE_TEST1, CTX, ctx);
        //        showUp2(ImageCollections.SIGNATURE_TEST2, CTX, ctx);

        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0) + " ms.");
    }
}
