package de.dbo.samples.image.houghtransform.data;

import static de.dbo.samples.image.houghtransform.data.Util.PNG;
import static de.dbo.samples.image.houghtransform.data.Util.filenames;

import java.util.List;
import java.util.Vector;

import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.ImageQuality;

/**
 * Manual categorization of the images
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
final class ImageCollectionCategorization {

    final Vector<ImageInfo> imageInfos;
    final String            data;

    ImageCollectionCategorization(final String data, final Vector<ImageInfo> imageInfos) {
        this.data = data;
    	this.imageInfos = imageInfos;
    }

    // SINATURE ---------------------------------------------------------------

    final void signature_performance_test(final ImageCollectionCatalog collection) throws Exception {
        switch (collection) {

           // PERFORMANCE
            case SIGNATURE_PERFORMANCE_UNCHECKED:
                final List<String> uncheckedImages = filenames(data);
                for (final String name : uncheckedImages) {
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.UNCHECKED));
                }
                break;

            case SIGNATURE_PERFORMANCE_CHECKED:
                final List<String> checkedImages = filenames(data);
                for (final String name : checkedImages) {
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.CHECKED));
                }
                break;

            case SIGNATURE_PERFORMANCE_ERROR_UNCHECKED:
                final List<String> errorUnchekedImages = filenames(data);
                for (final String name : errorUnchekedImages) {
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.UNCHECKED));
                }
                break;

            case SIGNATURE_PERFORMANCE_ERROR_CHECKED:
                final List<String> errorChekedImages = filenames(data);
                for (final String name : errorChekedImages) {
                    this.imageInfos.add(new ImageInfo(name.replaceAll(PNG, ""), Category.CHECKED));
                }
                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for signature_performance_test");
        }
    }


    final void signature_test(final ImageCollectionCatalog collection) throws Exception {
        switch (collection) {

            case SIGNATURE_TEST0:
                this.imageInfos.add(new ImageInfo("004", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("046", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("303", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("307", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("068", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("069", Category.CHECKED));
                break;

            case SIGNATURE_TEST1:
            	this.imageInfos.add(new ImageInfo("314", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("317", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("331", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("008", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("091", Category.CHECKED));
                break;

            case SIGNATURE_TEST2:
                this.imageInfos.add(new ImageInfo("cross", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("normal", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("empty", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("left", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("weak", Category.CHECKED));
                break;

            case SIGNATURE_PROBLEM:
                this.imageInfos.add(new ImageInfo("306", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("303", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("317", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("319", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("009", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("029", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("035", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("095", Category.CHECKED));
                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for signature_test");
        }

    }

    // BOX ----------------------------------------------------------------

    final void box_test(final ImageCollectionCatalog collection) {
        switch (collection) {
            case BOX_TEST0:

                this.imageInfos.add(new ImageInfo("kross", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_01", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("kross_10", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("top_left", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("bottom_right", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("top_bottom", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("left_right", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("circle", Category.UNKNOWN, null, null, false/* no JUnit-test */));

                break;

            case BOX_TEST1:

                this.imageInfos.add(new ImageInfo("box", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("box_small_bad_postion", Category.UNKNOWN));
                this.imageInfos.add(new ImageInfo("box_kross", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));

                this.imageInfos.add(new ImageInfo("empty_black", Category.UNKNOWN, null, null, false/* no JUnit-test */));

                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for box_test");

        }

    }

    final void box_features() {
        this.imageInfos.add(new ImageInfo("box", Category.UNCHECKED));
        this.imageInfos.add(new ImageInfo("box_done", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_done2", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_done3", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_ball", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("box_circle", Category.CHECKED));
        this.imageInfos.add(new ImageInfo("empty", Category.UNKNOWN));
        this.imageInfos.add(new ImageInfo("box_kross", Category.CHECKED));
    }

    final void box_samplesnormal(final ImageCollectionCatalog collection) {
        switch (collection) {
            case BOX_SAMPLES_NORMAL0:
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
            case BOX_SAMPLES_NORMAL1:
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

            case BOX_SAMPLES_NORMAL2:
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

            case BOX_SAMPLES_NORMAL3:
                this.imageInfos.add(new ImageInfo("s00", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s04", Category.UNCHECKED));
                break;

                default:
                	throw new RuntimeException(collection.path()+" is incorrect for box_samplesnormal");
        }
    }

    final void box_sampleslow(final ImageCollectionCatalog collection) {
        switch (collection) {

            case BOX_SAMPLES_LOW0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s05", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s08", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s09", Category.CHECKED,
                        ImageQuality.LOW));
                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for box_sampleslow");
        }
    }

    final void box_samplesnormalnegative() {
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


    final void brackets_test(final ImageCollectionCatalog collection) {
        switch (collection) {

        case BRACKETS_TEST:

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
            break;


            case BRACKETS_SAMPLES_NORMAL0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.UNCHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s04", Category.UNKNOWN,
                        ImageQuality.LOW));

                this.imageInfos.add(new ImageInfo("s07", Category.UNKNOWN,
                        ImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new ImageInfo("s08", Category.UNKNOWN,
                        ImageQuality.LOW, false/* no JUnit-test */));
                this.imageInfos.add(new ImageInfo("s09", Category.UNKNOWN,
                        ImageQuality.LOW, false/* no JUnit-test */));
                break;

                default:
                	throw new RuntimeException(collection.path()+" is incorrect for brackets_test");
        }
    }

    // CIRCLE -------------------------------------------------------------

    final void circle_test(final ImageCollectionCatalog  collection) {
        switch (collection) {
            case CIRCLE_TEST0:
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

            case CIRCLE_TEST1:
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
                        .add(new ImageInfo("circle", Category.UNKNOWN, null, null
                                , false/* no JUnit-test */));
                this.imageInfos
                        .add(new ImageInfo("empty_black", Category.UNKNOWN, null, null
                                , false/* no JUnit-test ! */));

                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for circle_test");
        }

    }

    final void circle_features() {
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

    final void circle_samplesnormal(final ImageCollectionCatalog collection) {
        switch (collection) {
            case CIRCLE_SAMPLES_NORMAL0:
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s02", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s03", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s04", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s05", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s06", Category.CHECKED));

                this.imageInfos.add(new ImageInfo("s07", Category.UNCHECKED
                		, null, ImageQuality.NORMAL, false /* no JUnit-test!*/));

                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                this.imageInfos.add(new ImageInfo("s00", Category.UNCHECKED));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED));
                break;

            default:
            	throw new RuntimeException(collection.path()+" is incorrect for circle_samplesnormal");
        }
    }

    final void circle_sampleslow(final ImageCollectionCatalog collection) {
        switch (collection) {

            case CIRCLE_SAMPLES_LOW0:
                this.imageInfos.add(new ImageInfo("s00", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                this.imageInfos.add(new ImageInfo("s01", Category.CHECKED,
                        ImageQuality.LOW));
                break;


                default:
                	throw new RuntimeException(collection.path()+ " is incorrect for circle_sampleslow");
        }
    }
}
