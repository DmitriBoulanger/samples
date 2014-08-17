package de.dbo.samples.pico.simple;

import java.util.ArrayList;
import java.util.List;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class JuicerExample {

	public Juicer juicerUsingPico() {
		
		MutablePicoContainer pico = new DefaultPicoContainer();  
		pico.addComponent(Apple.class);  
		pico.addComponent(Juicer.class);  
		pico.addComponent(Peeler.class); 
		
	    Juicer juicer = (Juicer) pico.getComponent(Juicer.class);  
	    
	    return juicer; 
	}
	
	public Juicer juicer() {
		Peelable peelable = new Apple();  
		Peeler peeler = new Peeler(peelable);  
		Juicer juicer = new Juicer(peelable, peeler);  
		return juicer;  
	}
	

}
