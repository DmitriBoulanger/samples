package de.dbo.samples.javafx.spring0;

import de.dbo.samples.javafx.spring0.SpringFX;

public class SpringFXjunit extends SpringFX {
    
    public SpringFXjunit() {
	super("spring.xml");
    }
    
    public static void main(final String[] args) {
	SpringFXjunit.launch(args);
    }
}
