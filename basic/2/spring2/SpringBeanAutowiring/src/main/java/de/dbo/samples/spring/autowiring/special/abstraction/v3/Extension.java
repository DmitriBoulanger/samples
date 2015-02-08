package de.dbo.samples.spring.autowiring.special.abstraction.v3;

/*
 * Version V3: Java-code is Spring-independent (no annotation is used)
 */

/**
 * Simple POJO extending the Abstraction that has a field to be autowired
 * but the super-class itself has nothing to-do with the Spring (annotations).
 * In this way the abstraction is not aware of the spring-context that can be used for concrete classes. 
 * The data to be inserted is also spring-independent (no annotations)
 * 
 * The magic of the autowiring: the Spring-context uses the name of the protected field in the super-class.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 */
public final class Extension extends Abstraction {

    public Data getData() {
        return data;
    }

    public void setData(final Data data) {
        this.data = data;
    }

}
