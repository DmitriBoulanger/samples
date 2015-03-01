package de.dbo.samples.spring.autowiring.special.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/de/dbo/samples/spring/autowiring/special/properties/app.properties")
public class AppConfig {
    
    @Autowired
    private Environment env;

    @Bean
    public AppBean testBean() {
	AppBean testBean = new AppBean();
	testBean.setName(env.getProperty("testbean.name"));
	return testBean;
    }
    
    public Environment getEnvironment() {
	return env;
    }
}
