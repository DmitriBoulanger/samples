package de.dbo.samples.java8.tutorial0.lambda.factory;

public interface PersonFactory<P extends Person> {
    
   public P create(String firstName, String lastName);
}
