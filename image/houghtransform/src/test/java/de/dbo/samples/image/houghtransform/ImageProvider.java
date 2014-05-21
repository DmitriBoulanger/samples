package de.dbo.samples.image.houghtransform;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
import de.dbo.samples.image.houghtransform.api.OMRCategory;
import de.dbo.samples.image.houghtransform.api.OMRImageInfo;
import de.dbo.samples.image.houghtransform.api.OMRImageQuality;
import de.dbo.samples.image.houghtransform.filter.PointBasedThresholdFilter;

/**
 * Image resources for tests and development. The resources are located in the
 * src/test/resources/data
 *
 * @author boulanger
 *
 */
public final class ImageProvider {
    private static final Logger        log          = LoggerFactory.getLogger(ImageProvider.class);

    public static final String         PERFORMANCE_SUBDIR = "performance/";

    private static final String        RESOURCE_DIR       = "de/ityx/contex/omr/houghtransform/data/";
    private static final String        PNG                = ".png";

    private final Vector<OMRImageInfo> imageInfos   = new Vector<OMRImageInfo>();
    private final String               data;

    public ImageProvider(final ImageCollections collection) throws Exception {
        this(collection, PERFORMANCE_SUBDIR);
    }

    public ImageProvider(final ImageCollections collection, String performanceSubdir) throws Exception {
        switch (collection) {

        // TEST --------------------------------------------------------------
            case TEST:
                this.data = "brackets/test/";
                test();
                break;

            // SIGNATURE ------------------------------------------------------------
            case SIGNATURE_TEST0:
                this.data = "signature/test/0/";
                signature_test(0);
                break;
            case SIGNATURE_TEST1:
                this.data = "signature/test/1/";
                signature_test(1);
                break;
            case SIGNATURE_TEST2:
                this.data = "signature/test/2/";
                signature_test(2);
                break;

            case SIGNATURE_PROBLEM:
                this.data = "signature/test/problem/";
                signature_test(99);
                break;

            case SIGNATURE_PERFORMANCE_UNCHECKED:
                this.data = "signature/" + performanceSubdir + "unchecked/";
                signature_test(100);
                break;

            case SIGNATURE_PERFORMANCE_CHECKED:
                this.data = "signature/" + performanceSubdir + "checked/";
                signature_test(101);
                break;

            case SIGNATURE_PERFORMANCE_ERROR:
                this.data = "signature/" + performanceSubdir + "error/";
                signature_test(102);
                break;

            // BOX ----------------------------------------------------------------
            case BOX_TEST0:
                this.data = "box/test/0/";
                box_test(0);
                break;
            case BOX_TEST1:
                this.data = "box/test/1/";
                box_test(1);
                break;
            case BOX_FEATURES:
                this.data = "box/features/";
                box_features();
                break;
            case BOX_SAMPLES_NORMAL0:
                this.data = "box/samples/normal/0/";
                box_samplesnormal(0);
                break;
            case BOX_SAMPLES_NORMAL1:
                this.data = "box/samples/normal/1/";
                box_samplesnormal(1);
                break;
            case BOX_SAMPLES_NORMAL2:
                this.data = "box/samples/normal/2/";
                box_samplesnormal(2);
                break;
            case BOX_SAMPLES_NORMAL3:
                this.data = "box/samples/normal/3/";
                box_samplesnormal(3);
                break;
            case BOX_SAMPLES_LOW0:
                this.data = "box/samples/low/0/";
                box_sampleslow(0);
                break;
            case BOX_SAMPLES_NORMAL_NEGATIVE:
                this.data = "box/samples/normal/negative/";
                box_samplesnormalnegative();
                break;

            // BRACKETS --------------------------------------------------------

            case BRACKETS_TEST:
                this.data = "brackets/test/";
                brackets_test();
                break;

            case BRACKETS_SAMPLES_NORMAL0:
                this.data = "brackets/samples/normal/0/";
                brackets_samplesnormal(0);
                break;

            default:
                throw new RuntimeException("Cannot recognize test-selection: " + collection.name());

                // CIRCLE -------------------------------------------------------------

            case CIRCLE_TEST0:
                this.data = "circle/test/0/";
                circle_test(0);
                break;
            case CIRCLE_TEST1:
                this.data = "circle/test/1/";
                circle_test(1);
                break;
            case CIRCLE_FEATURES:
                this.data = "circle/features/";
                circle_features();
                break;
            case CIRCLE_SAMPLES_NORMAL0:
                this.data = "circle/samples/normal/0/";
                circle_samplesnormal(0);
                break;
            case CIRCLE_SAMPLES_LOW0:
                this.data = "circle/samples/low/0/";
                circle_sampleslow(0);
                break;

        }
    }

    // TEST -------------------------------------------------------------------

    private final void test() {
        this.imageInfos.add(new OMRImageInfo("top_left", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("bottom_right", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("left_right", OMRCategory.UNCHECKED));
    }

    // SINATURE ---------------------------------------------------------------

    private static final void png(final String image) throws Exception {
        if (!image.endsWith(PNG)) {
            throw new Exception("Illegal image name: " + image);
        }
    }

    private final void signature_test(final int collection) throws Exception {
        switch (collection) {

        // PERFORMANCE
            case 100:
                final List<String> uncheckedImages = files(data);
                for (final String name : uncheckedImages) {
                    png(name);
                    this.imageInfos.add(new OMRImageInfo(name.replaceAll(PNG, ""), OMRCategory.UNCHECKED));
                }
                break;

            case 101:
                final List<String> checkedImages = files(data);
                for (final String name : checkedImages) {
                    png(name);
                    this.imageInfos.add(new OMRImageInfo(name.replaceAll(PNG, ""), OMRCategory.CHECKED));
                }
                break;

            case 102:
                final List<String> errorImages = files(data);
                for (final String name : errorImages) {
                    png(name);
                    this.imageInfos.add(new OMRImageInfo(name.replaceAll(PNG, ""), OMRCategory.UNCHECKED));
                }
                break;

            case 0:
                this.imageInfos.add(new OMRImageInfo("4", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("46", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("303", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("307", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("314", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("317", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("319", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("68", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("69", OMRCategory.CHECKED));
                break;

            case 1:
                this.imageInfos.add(new OMRImageInfo("319", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("331", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("8", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("91", OMRCategory.CHECKED));
                break;

            case 2:
                this.imageInfos.add(new OMRImageInfo("cross", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("normal", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("left", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("weak", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("bad", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("bad2", OMRCategory.UNCHECKED));
                break;

            case 99:
                this.imageInfos.add(new OMRImageInfo("306", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("303", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("317", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("319", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("8", OMRCategory.CHECKED));
                break;
        }

    }

    // BOX ----------------------------------------------------------------

    private final void box_test(final int collection) {
        switch (collection) {
            case 0:

                this.imageInfos.add(new OMRImageInfo("kross", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("kross_01", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("kross_10", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("top_left", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("bottom_right", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("top_bottom", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("left_right", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("circle", OMRCategory.UNKNOWN, null, false, null, false/* no JUnit-test */));

                break;

            case 1:

                this.imageInfos.add(new OMRImageInfo("box", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("box_small_bad_postion", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("box_kross", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));

                this.imageInfos.add(new OMRImageInfo("empty_black", OMRCategory.UNKNOWN, null, false, null, false/* no JUnit-test */));

                break;

        }

    }

    private final void box_features() {
        this.imageInfos.add(new OMRImageInfo("box", OMRCategory.UNCHECKED));
        this.imageInfos.add(new OMRImageInfo("box_done", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("box_done2", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("box_done3", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("box_ball", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("box_circle", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("box_kross", OMRCategory.CHECKED));
    }

    private final void box_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s08", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s09", OMRCategory.UNCHECKED));
                break;
            case 1:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s08", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s09", OMRCategory.CHECKED));
                break;

            case 2:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED, "Very poor image. If transforamtion is not precise, classification fails"));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s08", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s09", OMRCategory.UNCHECKED));
                break;

            case 3:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.UNCHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.UNCHECKED, true /*needs binary filter*/));
                break;
        }
    }

    private final void box_sampleslow(final int collection) {
        switch (collection) {

            case 0:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s08", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s09", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                break;
        }
    }

    private final void box_samplesnormalnegative() {
        this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED));

        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("empty", OMRCategory.UNKNOWN));
    }

    // BRACKETS --------------------------------------------------------

    private final void brackets_test() {
        this.imageInfos.add(new OMRImageInfo("kross", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("kross_01", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("kross_10", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("top_left", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("bottom_right",
                OMRCategory.UNKNOWN));
        this.imageInfos
                .add(new OMRImageInfo("top_bottom", OMRCategory.UNKNOWN));
        this.imageInfos.add(new OMRImageInfo("left_right",
                OMRCategory.UNCHECKED));
        this.imageInfos
                .add(new OMRImageInfo("brackets", OMRCategory.UNCHECKED));
        this.imageInfos.add(new OMRImageInfo("brackets_kross",
                OMRCategory.CHECKED));
    }

    private final void brackets_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.UNCHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.UNKNOWN,
                        true, OMRImageQuality.LOW));

                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.UNKNOWN,
                        true, OMRImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new OMRImageInfo("s08", OMRCategory.UNKNOWN,
                        true, OMRImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new OMRImageInfo("s09", OMRCategory.UNKNOWN,
                        true, OMRImageQuality.LOW, false/* no JUnit-test */));
                break;
        }
    }

    // CIRCLE -------------------------------------------------------------

    private final void circle_test(int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new OMRImageInfo("circle", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("circle_bad",
                        OMRCategory.UNCHECKED));
                this.imageInfos
                        .add(new OMRImageInfo("circle_bad2", OMRCategory.CHECKED));
                this.imageInfos
                        .add(new OMRImageInfo("circle_done", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("circle_done2",
                        OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("circle_kross",
                        OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("circle_check",
                        OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("box",
                        OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("circle_kross_big",
                        OMRCategory.CHECKED));
                break;

            case 1:
                this.imageInfos.add(new OMRImageInfo("kross", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("kross_01", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("kross_10", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("top_left", OMRCategory.UNKNOWN));
                this.imageInfos.add(new OMRImageInfo("bottom_right",
                        OMRCategory.UNKNOWN));
                this.imageInfos
                        .add(new OMRImageInfo("top_bottom", OMRCategory.UNKNOWN));
                this.imageInfos
                        .add(new OMRImageInfo("left_right", OMRCategory.UNKNOWN));
                this.imageInfos
                        .add(new OMRImageInfo("circle", OMRCategory.UNKNOWN, null, false, null
                                , false/* no JUnit-test */));
                this.imageInfos
                        .add(new OMRImageInfo("empty_black", OMRCategory.UNKNOWN, null, false, null
                                , false/* no JUnit-test ! */));

                break;
        }

    }

    private static final List<String> files(final String directory) throws Exception {
        final String resource = RESOURCE_DIR + directory;
        log.trace("reading file-names from " + resource + " ...");
        try {
            final List<String> ret = IOUtils.readLines(ClassLoader.getSystemClassLoader().getResourceAsStream(resource), Charsets.UTF_8);
            log.info("reading file-names from " + resource + ": " + ret.size() + " files found");
            return ret;
        }
        catch(Exception e) {
            throw new Exception("Can't read data from " + resource);
        }
    }

    private final void circle_features() {
        this.imageInfos.add(new OMRImageInfo("circle", OMRCategory.UNCHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_done", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_done2", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_done3", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_ball", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_circle", OMRCategory.CHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_dirty", OMRCategory.UNCHECKED));
        this.imageInfos.add(new OMRImageInfo("circle_kross", OMRCategory.CHECKED));
        return;
    }

    private final void circle_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s02", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s03", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s04", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s05", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s06", OMRCategory.CHECKED));
                //                this.imageInfos.add(new OMRImageInfo("s07", OMRCategory.UNCHECKED));

                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED));
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.UNCHECKED));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED));
                break;
        }
    }

    private final void circle_sampleslow(final int collection) {
        switch (collection) {

            case 0:
                this.imageInfos.add(new OMRImageInfo("s00", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                this.imageInfos.add(new OMRImageInfo("s01", OMRCategory.CHECKED,
                        true, OMRImageQuality.LOW));
                break;
        }
    }

    /**
     * directory with image samples (collection of images)
     */
    public String printDataCollectionName() {
        final String dot = ".";
        final String ret = this.data.replace("/", ".");
        if (ret.endsWith(dot)) {
            return ret.substring(0, ret.lastIndexOf(dot));
        }
        else {
            return ret;
        }
    }

    /**
     * image names with expected categories
     *
     * @see OMRCategory
     */
    public Vector<OMRImageInfo> getImageInfos() {
        return this.imageInfos;
    }

    public final BufferedImage getImageFromResource(final OMRImageInfo info) throws OMRCategorizerException {
        final String imageName = info.name;
        final String filename = imageName + PNG;
        final String resource = RESOURCE_DIR + this.data + filename;

        final BufferedImage image;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
            if (null == is) {
                throw new OMRCategorizerException(OMRCategorizerException.SYSTEM, "stream for  resource " + resource + " is null");
            }
            image = javax.imageio.ImageIO.read(is);
        }
        catch(Exception e) {
            throw new OMRCategorizerException(OMRCategorizerException.SYSTEM, "while reading " + resource, e);
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                }
                catch(IOException e) {
                    throw new OMRCategorizerException(OMRCategorizerException.SYSTEM
                            , "while closing " + resource, e);
                }
            }
        }
        //		if (info.biNeeded) {
        return toBinaryImage(image);
        //		} else {
        //			return image;
        //		}
    }

    private static BufferedImage toBinaryImage(final BufferedImage image) {
        return new PointBasedThresholdFilter().filter(image, null);
    }

    //	private static BufferedImage toBinaryImage(final BufferedImage image) {
    //		final BufferedImage binaryImage = new BufferedImage(
    //				image.getWidth(null), image.getHeight(null),
    //				BufferedImage.TYPE_BYTE_BINARY);
    //		final Graphics2D g = (Graphics2D) binaryImage.getGraphics();
    //		g.drawImage(image, 0, 0, null);
    //		g.dispose();
    ////		return binaryImage;
    //		return toNormalImage(binaryImage);
    //	}
    //
    //	private static BufferedImage toNormalImage(final BufferedImage image) {
    //		final BufferedImage blackAndWhiteImage = new BufferedImage(
    //				image.getWidth(null), image.getHeight(null),
    //				BufferedImage.TYPE_INT_ARGB);
    //		final Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
    //		g.drawImage(image, 0, 0, null);
    //		g.dispose();
    //		return blackAndWhiteImage;
    //	}
}
