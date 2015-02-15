package de.dbo.samples.spring.rmi;

/**
 * Service 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Service {
    
    int cube(int number);
    String ping();
}
