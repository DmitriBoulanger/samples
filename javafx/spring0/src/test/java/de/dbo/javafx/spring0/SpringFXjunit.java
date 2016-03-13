package de.dbo.javafx.spring0;

public class SpringFXjunit extends SpringFX {
    
    public SpringFXjunit() {
	super("spring.xml");
    }
    
    public static void main(final String[] args) {
	SpringFXjunit.launch(args);
    }
}
