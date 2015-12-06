package de.dbo.samples.spring.environments.annotation.cfgbeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestBeanValue implements TestBean {
    
    @Value("${testbean.value:NULL}")
    private String value;
    
    @Value("${testbean.name:NULL}")
    private String name;
    
    @Value("${testbean.env:NULL}")
    private String env;
    
    public TestBeanValue(final Environment env) {
	this.env = env.toString();
	this.name = env.getProperty("testbean.name");
    }
    
    public TestBeanValue() {
   	 
    }

    public String getName() {
        return name;
    }
    
    public String getEnv() {
        return env;
    }

    public String getValue() {
        return value;
    }
   
}
