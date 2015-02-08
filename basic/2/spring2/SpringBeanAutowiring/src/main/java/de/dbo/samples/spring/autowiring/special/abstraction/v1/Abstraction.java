package de.dbo.samples.spring.autowiring.special.abstraction.v1;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Abstraction {
    
    @Autowired
    protected Data data;

}
