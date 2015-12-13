package de.dbo.samples.spring.environments.annotation.cfg;

import static de.dbo.samples.spring.environments.annotation.cfg.cfgutil.SpringPrint.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    private ApplicationConfiguration configuration;

    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }
    
    public void init() {
	log.info("" + print(configuration.getEnvironment()) );
    }
}
