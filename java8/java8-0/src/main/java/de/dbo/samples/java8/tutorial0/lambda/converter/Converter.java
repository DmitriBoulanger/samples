package de.dbo.samples.java8.tutorial0.lambda.converter;

@FunctionalInterface
public interface Converter<F, T> {
    
   public T convert(F from);
}
