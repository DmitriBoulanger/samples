package de.dbo.samples.spring.autowiring.special.abstraction.v2;

/**
 * Package-private super-class with Spring Autowired-annotation
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
abstract class Abstraction {
    
    @org.springframework.beans.factory.annotation.Autowired
    protected Data x; // name of the field is not important

}
