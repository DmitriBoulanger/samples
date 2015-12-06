package de.dbo.samples.spring.environments.annotation.cfgbeans;

import de.dbo.samples.spring.environments.annotation.Tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public final class TestBean  {
    
    @Value("${testbean.value:NULL}")
    private String value;
    
    @Value("${testbean.value2:NULL from @Value}")
    private String value2;
    
    private final String name;
    
    private final String env;
    
    public TestBean(final Environment env) {
	this.env = Tool.print(env).toString();
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
    
    public final StringBuilder print() {
  	final StringBuilder sb = new StringBuilder("\nTest bean:");
     	sb.append("\n\t - test-bean name:   " + getName());
     	sb.append("\n\t - test-bean value:  " + getValue());
     	sb.append("\n\t - test-bean value2: " + getValue2());
     	return sb;
      }
   
}
