package de.dbo.samples.image.houghtransform.core.circle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.testimpl.GuiShowUp;

public class CircleTest extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(CircleTest.class);

    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "omr-circle-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUp(ImageCollections.CIRCLE_TEST0, CTX, ctx);
        showUp(ImageCollections.CIRCLE_TEST1, CTX, ctx);

        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0)
                + " ms.");
    }

}
