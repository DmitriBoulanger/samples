package de.dbo.samples.spring.environments.annotation.cfg.cfgutil;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.BeanDefinitionStoreException;

public class SpringExceptionHandler {
    
    public static void process(final Throwable e) throws Throwable {
	if (e instanceof BeanDefinitionStoreException) {
	    processSpring((BeanDefinitionStoreException) e);
	}
	throw e;
    }
    
    private static void processSpring(final BeanDefinitionStoreException beanDefinitionStoreException) {
	final Throwable cause = beanDefinitionStoreException.getCause();

	// cannot find XML-resource (or file)
	if (null!=cause && cause instanceof FileNotFoundException) {
	    throw new RuntimeException(cause.getMessage());
	}

    }
}
