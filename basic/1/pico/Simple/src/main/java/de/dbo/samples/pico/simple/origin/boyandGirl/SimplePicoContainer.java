package de.dbo.samples.pico.simple.origin.boyandGirl;

/*
 * MytablePicoContainer.java
 *
 * Created on October 3, 2005, 1:59 PM
 *
 */
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.DefaultPicoContainer;

/**
 *
 * @author James O'Hara
 */
public class SimplePicoContainer {
    
    /** Creates a new instance of MytablePicoContainer */
    public SimplePicoContainer() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DefaultPicoContainer pico = new DefaultPicoContainer();
        
        pico.addComponent(Boy.class);
        pico.addComponent(Girl.class);
        
        Girl girl = (Girl) pico.getComponent(Girl.class);
        girl.kissSomeone();


    }
    
}
