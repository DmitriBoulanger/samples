package de.dbo.samples.image.houghtransform.api;

/**
 * Image info and its optional descriptions. Instances are delivered by an Image
 * Provider and are used in the developing/tests In production mode instances of
 * this class are not used
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class ImageInfo {

    public final String       name;
    public final Category     category;
    public final String       description;
    public final ImageQuality quality;
    public final boolean      junit;

    public ImageInfo() {
        this(null, null, null, ImageQuality.NORMAL, true);
    }

    public ImageInfo(final String name) {
        this(name, null, null, ImageQuality.NORMAL, true);
    }
    
    public ImageInfo(final String name, boolean junit) {
        this(name, null, null, ImageQuality.NORMAL, junit);
    }

    /**
     *
     * @param name
     *            name of the image
     * @param expected
     *            category of the image
     *
     * @see Category
     */
   
    
    public ImageInfo(final String name, final Category category) {
        this(name, category, null, ImageQuality.NORMAL, true);
    }

    public ImageInfo(final String name, final Category category, final ImageQuality quality) {
        this(name, category, null, quality, true);
    }

    public ImageInfo(final String name, final Category category, ImageQuality quality, boolean junit) {
        this(name, category, null, quality, junit);
    }

    public ImageInfo(final String name, final Category category, final String description) {
        this(name, category, description, ImageQuality.NORMAL, true);
    }

    /**
     *
     * @param name
     *            name of the image
     * @param expected
     *            category of the image
     * @param description
     *            special comments
     * @param biNeeded
     *            is the black-white transformation needed
     *
     * @see Category
     */
   
    public ImageInfo(final String name, final Category category, final String description, 
    		final ImageQuality quality, boolean junit) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.quality = quality;
        this.junit = junit;
    }

}
