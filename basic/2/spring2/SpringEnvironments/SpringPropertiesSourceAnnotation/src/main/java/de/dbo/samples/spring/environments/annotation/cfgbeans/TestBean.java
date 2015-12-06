package de.dbo.samples.spring.environments.annotation.cfgbeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public final class TestBean  {
    
    @Value("${testbean.value:NULL}")
    private String value;
    
    @Value("${testbean.value2:NULL}")
    private String value2;
    
    private final String name;
    
    private final String env;
    
    public TestBean(final Environment env) {
	this.env = env.toString();
	this.name = env.getProperty("testbean.name");
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
    
    public String getValue2() {
        return value2;
    }
   
}
