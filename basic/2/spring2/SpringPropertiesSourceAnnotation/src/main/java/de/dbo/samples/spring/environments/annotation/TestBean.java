package de.dbo.samples.spring.environments.annotation;

import org.springframework.core.env.Environment;

public class TestBean {
    
    private final String name;
    
    public TestBean(final Environment env) {
	this.name = env.getProperty("testbean.name");
    }

    public String getName() {
        return name;
    }
 
}
