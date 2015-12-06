package de.dbo.samples.spring.environments.annotation.cfgbeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestBeanEnv implements TestBean {
    
    @Value("${testbean.value:NULL}")
    private String value;
    
//    @Value("${testbean.name:NULL}")
    private String name;
    
//    @Value("${testbean.env:NULL}")
    private String env;
    
    public TestBeanEnv(final Environment env) {
	this.env = env.toString();
	this.name = env.getProperty("testbean.name");
    }
    
    public TestBeanEnv() {
   	 
    }

    /* (non-Javadoc)
     * @see de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean#getName()
     */
    @Override
    public String getName() {
        return name;
    }
    
    /* (non-Javadoc)
     * @see de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean#getEnv()
     */
    @Override
    public String getEnv() {
        return env;
    }

    /* (non-Javadoc)
     * @see de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean#getValue()
     */
    @Override
    public String getValue() {
        return value;
    }
   
}
