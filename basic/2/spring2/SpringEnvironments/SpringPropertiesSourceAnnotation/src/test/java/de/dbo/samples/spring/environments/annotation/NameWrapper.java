package de.dbo.samples.spring.environments.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameWrapper {
    private static final Logger log = LoggerFactory.getLogger(NameWrapper.class);
    
    private  String name;
    
    public void init() {
	final StringBuilder sb = new StringBuilder("Name after init:");
	sb.append("\n\t - name  = " + name);
	log.info(sb.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
}
