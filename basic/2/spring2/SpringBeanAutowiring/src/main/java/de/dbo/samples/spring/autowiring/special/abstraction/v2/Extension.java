package de.dbo.samples.spring.autowiring.special.abstraction.v2;

/*
 * Version V2: annotated field without setter method
 */

/**
 * Simple POJO extending the Abstraction that has a field to be autowired.
 * This field is annotated as autowired but the data to be inserted is spring-independent (no annotations).
 * Note the concrete class (this) is not aware of the spring-context that can be used for concrete classes. 
 * More importantly, in this version the setter-method is not needed!
 * 
 * The magic of the autowiring: field in the abstraction (declared as autowired) makes the job.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 */
public final class Extension extends Abstraction {

    public Data dataFromSuperClassVersion2() {
        return x;
    }

//    public void setData(Data value) {
//        this.data = data;
//    }

}
