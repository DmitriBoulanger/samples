package com.javapapers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.javapapers.model.Resource;

@Configuration
@PropertySources
({
    @PropertySource(value="classpath:default.properties"),
    @PropertySource(value="classpath:application.properties",ignoreResourceNotFound=true)
})
public class ApplicationConfig {

    @Value("${restapi.url}")
    private String restAPIUrl;

    @Bean
    protected Resource database() {
	Resource resource = new Resource();
	resource.setUrl(restAPIUrl);
	return resource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	return new PropertySourcesPlaceholderConfigurer();
    }
}
