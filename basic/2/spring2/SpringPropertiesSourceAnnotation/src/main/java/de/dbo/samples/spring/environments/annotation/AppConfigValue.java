package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;
import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBeanEnv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:properties/app.properties")
public class AppConfigValue {
    
    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        return new TestBeanEnv(env);
    }
    
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:ppc.xml");
	final PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = ctx.getBean(PropertyPlaceholderConfigurer.class);
	return propertyPlaceholderConfigurer;
    }
    
}
