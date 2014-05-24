package de.dbo.samples.image.houghtransform.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.ImageQuality;
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

    private static final String        RESOURCE_DIR       = "de/dbo/samples/image/houghtransform/data/";
    private static final String        PNG                = ".png";

    private final Vector<ImageInfo> imageInfos   = new Vector<ImageInfo>();
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
        this.imageInfos.add(new ImageInfo("top_left", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("bottom_right", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("left_right", Category.UNCHECKED));
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
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.UNCHECKED));
                }
                break;

            case 101:
                final List<String> checkedImages = files(data);
                for (final String name : checkedImages) {
                    png(name);
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.CHECKED));
                }
                break;

            case 102:
                final List<String> errorImages = files(data);
                for (final String name : errorImages) {
                    png(name);
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.UNCHECKED));
                }
                break;

            case 0:
                this.imageInfos.add(new ImageInfo("4", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("46", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("303", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("307", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("314", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("317", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("319", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("68", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("69", Category.CHECKED));
                break;

            case 1:
                this.imageInfos.add(new ImageInfo("319", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("331", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("8", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("91", Category.CHECKED));
                break;

            case 2:
                this.imageInfos.add(new ImageInfo("cross", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("normal", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("empty", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("left", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("weak", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("bad", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("bad2", Category.UNCHECKED));
                break;

            case 99:
                this.imageInfos.add(new ImageInfo("306", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("303", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("317", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("319", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("8", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("009", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("029", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("035", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("095", Category.CHECKED));
                break;
        }

    }

    // BOX ----------------------------------------------------------------

    private final void box_test(final int collection) {
        switch (collection) {
            case 0:

                this.imageInfos.add(new ImageInfo("kross", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_01", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_10", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("top_left", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("bottom_right", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("top_bottom", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("left_right", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("circle", Category.UNKNOWN, null, false, null, false/* no JUnit-test */));

                break;

            case 1:

                this.imageInfos.add(new ImageInfo("box", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("box_small_bad_postion", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("box_kross", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));

                this.imageInfos.add(new ImageInfo("empty_black", Category.UNKNOWN, null, false, null, false/* no JUnit-test */));

                break;

        }

    }

    private final void box_features() {
        this.imageInfos.add(new ImageInfo("box", Category.UNCHECKED));
        this.imageInfos.add(new ImageInfo("box_done", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_done2", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_done3", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_ball", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_circle", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("box_kross", Category.CHECKED));
    }

    private final void box_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s04", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s06", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s07", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s08", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s09", Category.UNCHECKED));
                break;
            case 1:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s06", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s08", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s09", Category.CHECKED));
                break;

            case 2:
                this.imageInfos.add(new ImageInfo("s00", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED, "Very poor image. If transforamtion is not precise, classification fails"));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s06", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s08", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s09", Category.UNCHECKED));
                break;

            case 3:
                this.imageInfos.add(new ImageInfo("s00", Category.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new ImageInfo("s02", Category.UNCHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED, true /*needs binary filter*/));
                this.imageInfos.add(new ImageInfo("s04", Category.UNCHECKED, true /*needs binary filter*/));
                break;
        }
    }

    private final void box_sampleslow(final int collection) {
        switch (collection) {

            case 0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s05", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s08", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s09", Category.CHECKED,
                        true, ImageQuality.LOW));
                break;
        }
    }

    private final void box_samplesnormalnegative() {
        this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));

        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
    }

    // BRACKETS --------------------------------------------------------

    private final void brackets_test() {
        this.imageInfos.add(new ImageInfo("kross", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("kross_01", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("kross_10", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("top_left", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("bottom_right",
                Category.UNKNOWN));
        this.imageInfos
                .add(new ImageInfo("top_bottom", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("left_right",
                Category.UNCHECKED));
        this.imageInfos
                .add(new ImageInfo("brackets", Category.UNCHECKED));
        this.imageInfos.add(new ImageInfo("brackets_kross",
                Category.CHECKED));
    }

    private final void brackets_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.UNCHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s04", Category.UNKNOWN,
                        true, ImageQuality.LOW));

                this.imageInfos.add(new ImageInfo("s07", Category.UNKNOWN,
                        true, ImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new ImageInfo("s08", Category.UNKNOWN,
                        true, ImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new ImageInfo("s09", Category.UNKNOWN,
                        true, ImageQuality.LOW, false/* no JUnit-test */));
                break;
        }
    }

    // CIRCLE -------------------------------------------------------------

    private final void circle_test(int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new ImageInfo("circle", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("circle_bad",
                        Category.UNCHECKED));
                this.imageInfos
                        .add(new ImageInfo("circle_bad2", Category.CHECKED));
                this.imageInfos
                        .add(new ImageInfo("circle_done", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("circle_done2",
                        Category.CHECKED));
                this.imageInfos.add(new ImageInfo("circle_kross",
                        Category.CHECKED));
                this.imageInfos.add(new ImageInfo("circle_check",
                        Category.CHECKED));
                this.imageInfos.add(new ImageInfo("box",
                        Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("circle_kross_big",
                        Category.CHECKED));
                break;

            case 1:
                this.imageInfos.add(new ImageInfo("kross", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_01", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_10", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("top_left", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("bottom_right",
                        Category.UNKNOWN));
                this.imageInfos
                        .add(new ImageInfo("top_bottom", Category.UNKNOWN));
                this.imageInfos
                        .add(new ImageInfo("left_right", Category.UNKNOWN));
                this.imageInfos
                        .add(new ImageInfo("circle", Category.UNKNOWN, null, false, null
                                , false/* no JUnit-test */));
                this.imageInfos
                        .add(new ImageInfo("empty_black", Category.UNKNOWN, null, false, null
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
        this.imageInfos.add(new ImageInfo("circle", Category.UNCHECKED));
        this.imageInfos.add(new ImageInfo("circle_done", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("circle_done2", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("circle_done3", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("circle_ball", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("circle_circle", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("circle_dirty", Category.UNCHECKED));
        this.imageInfos.add(new ImageInfo("circle_kross", Category.CHECKED));
        return;
    }

    private final void circle_samplesnormal(final int collection) {
        switch (collection) {
            case 0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED));
                //                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED));

                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                break;
        }
    }

    private final void circle_sampleslow(final int collection) {
        switch (collection) {

            case 0:
                this.imageInfos.add(new ImageInfo("s00", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        true, ImageQuality.LOW));
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
     * @see Category
     */
    public Vector<ImageInfo> getImageInfos() {
        return this.imageInfos;
    }

    public final BufferedImage getImageFromResource(final ImageInfo info) throws CategorizerException {
        final String imageName = info.name;
        final String filename = imageName + PNG;
        final String resource = RESOURCE_DIR + this.data + filename;

        final BufferedImage image;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
            if (null == is) {
                throw new CategorizerException(CategorizerException.SYSTEM, "stream for  resource " + resource + " is null");
            }
            image = javax.imageio.ImageIO.read(is);
        }
        catch(Exception e) {
            throw new CategorizerException(CategorizerException.SYSTEM, "while reading " + resource, e);
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                }
                catch(IOException e) {
                    throw new CategorizerException(CategorizerException.SYSTEM
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
