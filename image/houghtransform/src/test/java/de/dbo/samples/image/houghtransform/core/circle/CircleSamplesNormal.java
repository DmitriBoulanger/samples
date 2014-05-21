package de.dbo.samples.image.houghtransform.core.circle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.data.ImageCollections;
import de.dbo.samples.image.houghtransform.testimpl.GuiShowUp;

public class CircleSamplesNormal extends GuiShowUp {
    private static final Logger log = LoggerFactory.getLogger(CircleSamplesNormal.class);

    /**
     * starts GUI to show sample-collections of the marker-images
     *
     * @throws Exception
     * @see ImageCollections
     */
    public static void main(String[] args) throws Exception {
        final long start0 = System.currentTimeMillis();

        final String CTX = "omr-circle-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        showUp(ImageCollections.CIRCLE_SAMPLES_NORMAL0, CTX, ctx);

        log.info("finished. Elapsed: " + (System.currentTimeMillis() - start0)
                + " ms.");
    }

}
