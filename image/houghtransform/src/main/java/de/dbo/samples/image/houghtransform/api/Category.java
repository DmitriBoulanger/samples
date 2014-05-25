package de.dbo.samples.image.houghtransform.api;

/**
 * Types (categories) of images. Instances are used to indicate the
 * output of a marker-recognition. The CHECKED-category means that the expected
 * shape found, and the expected object found inside the shape. The
 * UNCHECKED-category means that the expected shape found but it doesn't contain any expected object.
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public enum Category {
    UNKNOWN, UNCHECKED, CHECKED
}
