package de.dbo.samples.image.houghtransform.api;

/**
 * Image info and its optional descriptions. Instances are delivered by an Image
 * Provider and are used in the developing/tests In production mode instances of
 * this class are not used
 *
 * @author D. Boulanger ITyX GmbH
 *
 */
public final class OMRImageInfo {

    public final String          name;
    public final OMRCategory     category;
    public final String          description;
    public final boolean         biNeeded;
    public final OMRImageQuality quality;
    public final boolean         junit;

    public OMRImageInfo() {
        this(null, null, null, false, OMRImageQuality.NORMAL);
    }

    public OMRImageInfo(final String name) {
        this(name, null, null, false, OMRImageQuality.NORMAL);
    }

    /**
     *
     * @param name
     *            name of the image
     * @param expected
     *            category of the image
     *
     * @see OMRCategory
     */
    public OMRImageInfo(final String name, final OMRCategory category) {
        this(name, category, null, false, OMRImageQuality.NORMAL);
    }

    public OMRImageInfo(final String name, final OMRCategory category, boolean biNeeded) {
        this(name, category, null, biNeeded, OMRImageQuality.NORMAL);
    }

    public OMRImageInfo(final String name, final OMRCategory category, boolean biNeeded, OMRImageQuality quality) {
        this(name, category, null, biNeeded, quality);
    }

    public OMRImageInfo(final String name, final OMRCategory category, boolean biNeeded, OMRImageQuality quality, boolean junit) {
        this(name, category, null, biNeeded, quality, junit);
    }

    public OMRImageInfo(final String name, final OMRCategory category, final String description) {
        this(name, category, description, false, OMRImageQuality.NORMAL);
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
     * @see OMRCategory
     */
    public OMRImageInfo(final String name, final OMRCategory category, final String description, boolean biNeeded, OMRImageQuality quality) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.biNeeded = biNeeded;
        this.quality = quality;
        this.junit = true;
    }

    public OMRImageInfo(final String name, final OMRCategory category, final String description, boolean biNeeded, OMRImageQuality quality, boolean junit) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.biNeeded = biNeeded;
        this.quality = quality;
        this.junit = junit;
    }

}
