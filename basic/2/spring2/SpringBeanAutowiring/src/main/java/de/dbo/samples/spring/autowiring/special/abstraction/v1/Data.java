package de.dbo.samples.spring.autowiring.special.abstraction.v1;

import org.springframework.stereotype.Component;

@Component
public class Data {

    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
