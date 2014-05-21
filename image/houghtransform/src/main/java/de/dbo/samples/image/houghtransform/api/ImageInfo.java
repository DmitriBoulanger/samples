package de.dbo.samples.image.houghtransform.api;

/**
 * Image info and its optional descriptions. Instances are delivered by an Image
 * Provider and are used in the developing/tests In production mode instances of
 * this class are not used
 *
 * @author D. Boulanger ITyX GmbH
 *
 */
public final class ImageInfo {

    public final String          name;
    public final HTCategory   category;
    public final String          description;
    public final boolean         biNeeded;
    public final ImageQuality quality;
    public final boolean         junit;

    public ImageInfo() {
        this(null, null, null, false, ImageQuality.NORMAL);
    }

    public ImageInfo(final String name) {
        this(name, null, null, false, ImageQuality.NORMAL);
    }

    /**
     *
     * @param name
     *            name of the image
     * @param expected
     *            category of the image
     *
     * @see HTCategory
     */
    public ImageInfo(final String name, final HTCategory category) {
        this(name, category, null, false, ImageQuality.NORMAL);
    }

    public ImageInfo(final String name, final HTCategory category, boolean biNeeded) {
        this(name, category, null, biNeeded, ImageQuality.NORMAL);
    }

    public ImageInfo(final String name, final HTCategory category, boolean biNeeded, ImageQuality quality) {
        this(name, category, null, biNeeded, quality);
    }

    public ImageInfo(final String name, final HTCategory category, boolean biNeeded, ImageQuality quality, boolean junit) {
        this(name, category, null, biNeeded, quality, junit);
    }

    public ImageInfo(final String name, final HTCategory category, final String description) {
        this(name, category, description, false, ImageQuality.NORMAL);
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
     * @see HTCategory
     */
    public ImageInfo(final String name, final HTCategory category, final String description, boolean biNeeded, ImageQuality quality) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.biNeeded = biNeeded;
        this.quality = quality;
        this.junit = true;
    }

    public ImageInfo(final String name, final HTCategory category, final String description, boolean biNeeded, ImageQuality quality, boolean junit) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.biNeeded = biNeeded;
        this.quality = quality;
        this.junit = junit;
    }

}
