package de.dbo.samples.util0;

/**
 * Printable objects.
 * Any printable object can be printed as a column of lines
 * or as a single line
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public interface Printable {

    public abstract StringBuilder printlines();

    public abstract StringBuilder printline();
}
