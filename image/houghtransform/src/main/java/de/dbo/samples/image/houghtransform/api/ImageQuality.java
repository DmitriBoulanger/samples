package de.dbo.samples.image.houghtransform.api;

/**
 * Quality of the marker-image. Marker-images are classified using number of
 * pixels, i.e. the length of the image diagonal is taken as an image quality
 *
 * @author D.Boulanger ITyX GmbH
 */
public enum ImageQuality {
    TOO_LARGE
    , HIGH // 100x100
    , NORMAL // 70x70
    , LOW // 30x30
    , TOO_SMALL
}
