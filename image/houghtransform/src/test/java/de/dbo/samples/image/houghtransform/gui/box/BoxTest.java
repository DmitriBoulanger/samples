package de.dbo.samples.image.houghtransform.gui.box;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.data.ImageCollectionCatalog;
import de.dbo.samples.image.houghtransform.gui.impl.GuiShowUp;

public final class BoxTest extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(BoxTest.class);

    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "box-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUp(ImageCollectionCatalog.BOX_TEST0, CTX, ctx);
        showUp(ImageCollectionCatalog.BOX_TEST1, CTX, ctx);

        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0) + " ms.");
    }
}
