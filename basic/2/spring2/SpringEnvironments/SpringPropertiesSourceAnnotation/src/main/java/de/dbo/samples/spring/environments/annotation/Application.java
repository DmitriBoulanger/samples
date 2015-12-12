package de.dbo.samples.spring.environments.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class Application {
    
    @Autowired
    private ApplicationConfiguration configuration;

    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }
    
}
