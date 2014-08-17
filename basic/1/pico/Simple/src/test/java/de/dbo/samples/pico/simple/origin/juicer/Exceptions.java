package de.dbo.samples.pico.simple.origin.juicer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.injectors.AbstractInjector.CyclicDependencyException;


public class Exceptions {
	
	@Test(expected=CyclicDependencyException.class)
	public void testCircular() {  
	    MutablePicoContainer mpc = new PicoBuilder().build();  
	    mpc.addComponent(List.class, ArrayList.class);  
	    mpc.addComponent(Set.class, HashSet.class);  
	      
	    try {
	    	@SuppressWarnings("unused")
			List<?> components = mpc.getComponent(List.class); //Throws a CircularDependencyException  
		} catch (CyclicDependencyException e) {
			System.err.println("Pico-Container ERROR: " + e.getMessage() );
			throw e;
		}
	}  

}
