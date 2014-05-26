package de.dbo.samples.image.houghtransform.data;

import static de.dbo.samples.image.houghtransform.Util.applyMandatoryFilter;
import static de.dbo.samples.image.houghtransform.data.Util.PNG;
import static de.dbo.samples.image.houghtransform.data.Util.performanceSubdir;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;

/**
 * Image collections for tests and development.
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
public final class ImageProvider {

    private final Vector<ImageInfo> imageInfos   = new Vector<ImageInfo>();
    private final String            resourceDir;
    private final String            signaturePerformanceSubdir;

	private static final String resourceDir(final ImageCollectionCatalog collection,
			final String performanceSubdir) {
		final String root = collection.path();
		switch (collection) {
			case SIGNATURE_PERFORMANCE_CHECKED:
				return root + performanceSubdir + "checked/";
			case SIGNATURE_PERFORMANCE_UNCHECKED:
				return root + performanceSubdir + "unchecked/";
            case SIGNATURE_PERFORMANCE_ERROR_CHECKED:
                return root + performanceSubdir + "error/checked/";
            case SIGNATURE_PERFORMANCE_ERROR_UNCHECKED:
                return root + performanceSubdir + "error/unchecked/";

		default:
			return root;
		}
	}

    public ImageProvider(final ImageCollectionCatalog collection)
    		throws Exception {
    	signaturePerformanceSubdir = performanceSubdir(collection);

    	resourceDir = resourceDir(collection,signaturePerformanceSubdir);
    	final ImageCollectionCategorization categorization = new ImageCollectionCategorization(resourceDir, imageInfos);

        switch (collection) {

            // SIGNATURE ------------------------------------------------------------
            case SIGNATURE_TEST0:
            case SIGNATURE_TEST1:
            case SIGNATURE_TEST2:
            case SIGNATURE_PROBLEM:
                categorization.signature_test(collection);
                break;

            case SIGNATURE_PERFORMANCE_UNCHECKED:
            case SIGNATURE_PERFORMANCE_CHECKED:
            case SIGNATURE_PERFORMANCE_ERROR_UNCHECKED:
            case SIGNATURE_PERFORMANCE_ERROR_CHECKED:
            	if (null==signaturePerformanceSubdir) {
            		return;
            	}
                categorization.signature_performance_test(collection);
                break;

            // BOX ----------------------------------------------------------------
            case BOX_TEST0:
            case BOX_TEST1:
            	categorization.box_test(collection);
                break;

            case BOX_FEATURES:
            	categorization.box_features();
                break;

            case BOX_SAMPLES_NORMAL0:
            case BOX_SAMPLES_NORMAL1:
            case BOX_SAMPLES_NORMAL2:
            case BOX_SAMPLES_NORMAL3:
            	categorization.box_samplesnormal(collection);
                break;

            case BOX_SAMPLES_LOW0:
            	categorization.box_sampleslow(collection);
                break;

            case BOX_SAMPLES_NORMAL_NEGATIVE:
            	categorization.box_samplesnormalnegative();
                break;

            // BRACKETS --------------------------------------------------------

            case BRACKETS_TEST:
            	categorization.brackets_test(collection);
                break;

            case BRACKETS_SAMPLES_NORMAL0:
            	categorization.brackets_test(collection);
                break;

           // CIRCLE -------------------------------------------------------------

            case CIRCLE_TEST0:
            case CIRCLE_TEST1:
            	categorization.circle_test(collection);
                break;

            case CIRCLE_FEATURES:
            	categorization.circle_features();
                break;

            case CIRCLE_SAMPLES_NORMAL0:
            	categorization.circle_samplesnormal(collection);
                break;

            case CIRCLE_SAMPLES_LOW0:
            	categorization.circle_sampleslow(collection);
                break;

            default:
                throw new RuntimeException("Cannot recognize image-collection: " + collection.name());

        }
    }

    /**
     * directory with image samples (collection of images)
     */
    public String printDataCollectionName() {
        final String dot = ".";
        final String ret = this.resourceDir.replace("/", ".");
        if (ret.endsWith(dot)) {
            return ret.substring(0, ret.lastIndexOf(dot));
        }
        else {
            return ret;
        }
    }

    /**
     * image-names with expected categories
     *
     * @see Category
     */
    public Vector<ImageInfo> getImageInfos() {
        return this.imageInfos;
    }

    public final BufferedImage getImageFromResource(final ImageInfo info) throws CategorizerException {
        final String imageName = info.name;
        final String filename = imageName + PNG;
        final String resource = this.resourceDir + filename;

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
                    throw new CategorizerException(CategorizerException.SYSTEM, "while closing " + resource, e);
                }
            }
        }
        return applyMandatoryFilter(image);
    }
}
