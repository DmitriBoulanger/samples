package de.dbo.samples.spring.autowiring.special.abstraction.v2;

/*
 * Version V2: Java-code is Spring-independent (no annotation is used)
 */

/**
 * Simple POJO extending the Abstraction that has a field to be autowired.
 * This field is annotated as autowired but the data to be inserted is spring-independent (no annotations).
 * Note the concrete class is not aware of the spring-context that can be used for concrete classes. 
 * 
 * 
 * The magic of the autowiring: field in the abstraction (declared as autowired) makes the job.
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

    public void setData(Data data) {
        this.data = data;
    }

}
