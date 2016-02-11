
package de.dbo.samples.web.jetty;

import static de.dbo.tools.utils.print.Print.padLeft;
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
public final class JettyStartUp extends Runner { 
    
    private JettySetUp setUp;
    
    public JettyStartUp (JettySetUp setUp) {
	this.setUp = setUp;
    }

    public void doIt() throws Exception {

	setUp.configure(this);
	final Runner myRunner = this;
	Runnable runnable = new Runnable() {
	    
	    @Override
	    public void run() {
		try {
		    myRunner.run();
		} catch (Exception e) {
		    
		    e.printStackTrace();
		}
		
		
	    }
	};
	
	final Thread daemon = new Thread(runnable);
	daemon.start();
	
	
	final long start = System.currentTimeMillis();
	while (!super._server.isStarted()) {
	    Thread.sleep(100);
	    System.out.println(
		    padLeft("" + (System.currentTimeMillis()-start), 7) +":" 
	     + "   started = "   + padLeft( ""+(super._server.isStarted()), 5)
	     + "   running = "   + padLeft( ""+(super._server.isRunning()), 5)
	     + "   starting = "   + padLeft( ""+(super._server.isStarting()), 5)
	     );
	}
	
	System.out.println( padLeft("" + (System.currentTimeMillis()-start), 7) 
		+ ":   =========== STARTED =============");
	System.out.println(
		    padLeft("" + (System.currentTimeMillis()-start), 7) +":" 
	     + "   started = "   + padLeft( ""+(super._server.isStarted()), 5)
	     + "   running = "   + padLeft( ""+(super._server.isRunning()), 5)
	     + "   starting = "   + padLeft( ""+(super._server.isStarting()), 5)
	     );

    }

    public JettySetUp getSetUp() {
        return setUp;
    }

    public void setSetUp(JettySetUp setUp) {
        this.setUp = setUp;
    }
    
    
   
}