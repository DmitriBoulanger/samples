package de.dbo.samples.java8.tutorial0.lambda.factory;

/**
 * @author Benjamin Winterberg
 */
public class PersonFactoryUsage {

    public static void main(String[] args) {
	
         

        // constructor reference

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }
}
