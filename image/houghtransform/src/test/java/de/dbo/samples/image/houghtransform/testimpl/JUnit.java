package de.dbo.samples.image.houghtransform.testimpl;

import static junit.framework.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.CategorizerFactory;
import de.dbo.samples.image.houghtransform.api.Categorizer;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.Marker;
import de.dbo.samples.image.houghtransform.data.ImageProvider;

/**
 * Base abstraction for tests
 *
 * @author boulanger
 */
public abstract class JUnit {
    protected static final Logger log = LoggerFactory.getLogger(JUnit.class);

    protected final void processAndAssertImages(final ImageProvider imageProvider, final String title
            , final Marker marker) throws Exception {
        final long start0 = System.currentTimeMillis();
        final Vector<ImageInfo> imagesInfos = imageProvider.getImageInfos();
        long elapsedAccumulazed = 0;
        int cnt = 0;
        for (final ImageInfo info : imagesInfos) {
            if (!info.junit) {
                continue;
            }
            cnt++;
            final String name = info.name;
            final String messageHead = "Image=" + name.toUpperCase();
            log.debug(messageHead + " ... ");
            final BufferedImage image = imageProvider.getImageFromResource(info);
            final long start = System.currentTimeMillis();
            final Categorizer categorizer = CategorizerFactory.newInstance(marker);
            final Category categoryExpected = info.category;
            final Category category = categorizer.getCategory(image);
            final long elapsed = System.currentTimeMillis() - start;
            elapsedAccumulazed += elapsed;
            final Boolean ok = new Boolean(category == categoryExpected);
            log.debug(messageHead + " Categoty=" + category
                    + " (" + ok.toString().toUpperCase() + ")"
                    + " Elapsed: " + elapsed + " ms.");
            assertTrue("Incorrect category for " + name.toUpperCase() + " from "
                    + imageProvider.printDataCollectionName()
                    + "( has  " + category
                    + "  but expected " + categoryExpected + ")"
                    , ok);
        }
        final long elapsedTotal = System.currentTimeMillis() - start0;
        log.info(title + " done. Elapsed total: " + elapsedTotal + " ms." + " Average: " + elapsedAccumulazed / cnt + " ms.");
    }

    protected final double processAndCheckImages(final ImageProvider imageProvider, final String title
            , final Marker marker) throws Exception {
        int total = 0;
        int bad = 0;
        final long start0 = System.currentTimeMillis();
        final Vector<ImageInfo> imagesInfos = imageProvider.getImageInfos();
        long elapsedAccumulazed = 0;
        int cnt = 0;
        for (final ImageInfo info : imagesInfos) {
            if (!info.junit) {
                continue;
            }
            cnt++;
            final String name = info.name;
            final String messageHead = "Image=" + name.toUpperCase();
            log.debug(messageHead + " ... ");
            final BufferedImage image = imageProvider.getImageFromResource(info);
            final long start = System.currentTimeMillis();
            final Categorizer categorizer = CategorizerFactory.newInstance(marker);
            final Category categoryExpected = info.category;
            final Category category = categorizer.getCategory(image);
            final long elapsed = System.currentTimeMillis() - start;
            elapsedAccumulazed += elapsed;
            final Boolean ok = new Boolean(category == categoryExpected);

            log.debug(messageHead + " Categoty=" + category
                    + " (" + ok.toString().toUpperCase() + ")"
                    + " Elapsed: " + elapsed + " ms.");
            total++;
            if (!ok) {
                bad++;
                log.error(messageHead + " Categoty=" + category
                        + " (" + ok.toString().toUpperCase() + ")"
                        + " Elapsed: " + elapsed + " ms.");
            }

        }
        final long elapsedTotal = System.currentTimeMillis() - start0;
        log.info(title + " done. Elapsed total: " + elapsedTotal + " ms." + " Average: " + elapsedAccumulazed / cnt + " ms. Images: " + cnt);
        return 0 != total ? ((double) bad) / ((double) total) : Double.MAX_VALUE;
    }

    protected static final void handleException(String title, Exception e) throws Exception {
        log.error("while processing images in " + title, e);
        throw e;
    }
}
