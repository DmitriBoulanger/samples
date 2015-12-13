package de.dbo.samples.spring.environments.annotation.cfgutil;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class SpringExceptionHandler {
    
    /**
     * @param e problem while running Spring
     * @throws Throwable if the problem behind exception cannot be identifed
     */
    public static void process(final Throwable e) throws Throwable {
	if (e instanceof BeanDefinitionStoreException) {
	    processSpring((BeanDefinitionStoreException) e);
	}
	throw e;
    }
    
    private static void processSpring(final BeanDefinitionStoreException beanDefinitionStoreException) {
	final Throwable cause = beanDefinitionStoreException.getCause();
	if (null==cause) {
	    return;
	}

	// cannot find XML-resource (or file)
	if (cause instanceof FileNotFoundException) {
	    throw new RuntimeException(cause.getMessage());
	}
    }
}
