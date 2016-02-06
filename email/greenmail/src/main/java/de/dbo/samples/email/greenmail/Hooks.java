package de.dbo.samples.email.greenmail;

/**
 * Hooks to run before server start-up
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Hooks {
    
    /**
     * action to be done before server start-up
     */
    void beforeStart();
}
