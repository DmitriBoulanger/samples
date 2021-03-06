package de.dbo.samples.image.houghtransform.api;

/**
 * Mode of a categorizer worker. Categorization consists of one or two phases (steps).
 * Steps are performed by instances of the categorizer-worker.
 * Typically the the first step is done with the marker-shape recognition, while
 * the second step is the content recognition. The content recognition is aimed
 * at objects inside the shape that has been discovered during the first step.
 * Any categorizer worker has the mode indicating what it is doing
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public enum CategorizerMode {
    SHAPE, 
    CONTENT
}
