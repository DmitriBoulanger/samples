package de.dbo.samples.spring.autowiring.special.abstraction.v3;

/*
 * Version V3: Java-code is Spring-independent (no annotation is used)
 */

/**
 * Simple POJO extending the Abstraction that has a field to be autowired.
 * Its super-class itself has nothing to-do with the Spring-annotations.
 * Therefore, the abstraction is not aware of the spring-context that can be used for concrete classes. 
 * The data to be inserted is also spring-independent (no annotations)
 * 
 * The magic of the autowiring: the Spring-context uses the name of the public setter-method (data).
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 */
public final class Extension extends Abstraction {

    public Data dataFromSuperClassVersion3() {
        return x;
    }

    public void setData(final Data value) { // method name is important( auto-wire by name)
        this.x = value;
    }

}
