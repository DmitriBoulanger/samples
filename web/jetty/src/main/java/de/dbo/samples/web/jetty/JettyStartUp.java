package de.dbo.samples.web.jetty;

import org.eclipse.jetty.runner.Runner;

/**
 * SMTP/IMAP configuration (set-up) builder
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class JettyStartUp { 
    
    private JettySetUp setUp;
    
    public JettyStartUp (JettySetUp setUp) {
	this.setUp = setUp;
    }

    public void doIt() throws Exception {
	final Runner jetty = new Runner();
	setUp.configure(jetty);
	jetty.run();
	
    }

    public JettySetUp getSetUp() {
        return setUp;
    }

    public void setSetUp(JettySetUp setUp) {
        this.setUp = setUp;
    }
    
    
   
}