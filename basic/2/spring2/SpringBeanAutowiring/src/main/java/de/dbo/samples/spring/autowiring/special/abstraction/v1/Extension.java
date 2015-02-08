package de.dbo.samples.spring.autowiring.special.abstraction.v1;

/*
 *  Version V1: only data to be inserted is annotated as a Spring-component
 */

/**
 * Simple POJO extending the Abstraction that has a field to be autowired
 * but the super-class itself has nothing to-do with the Spring (annotations).
 * In this way the abstraction is not aware of the spring-context that can be used for concrete classes. 
 * The data to be inserted is only declared as Spring-component
 * 
 * The magic of the autowiring: by type with data as a declared component (XML is minimal).
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 */
public final class Extension extends Abstraction {

    public Data dataFromSuperClass() {
        return x;
    }

    public void setValueInSuperClass(final Data value) { // method name is not important
        this.x = value;
    }

}
