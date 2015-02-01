package de.dbo.samples.rmi0;

import static de.dbo.samples.rmi0.Client.callServer;
import static de.dbo.samples.rmi0.ServerManager.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Open/close tests of RMI-servers
 * 
 * @author Dmitri Boulanger, Hombach
 *
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 *
 */
public class ServerStartUpJUnit {

    /**
     * Opening and closing of the RMI-server
     */
    @Test
    public void serverOpenClose() 
	    throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException, URISyntaxException {
	assertTrue("Server wasn't opened", openServer("server"));
	assertNotNull("Server call returns null", callServer("server"));
	assertFalse("Server was opened but it has been already running", openServer("server"));
	assertNotNull("Server call returns null", callServer("server"));
	assertTrue("Server was not closed", closeServer("server"));
	assertFalse("Server was closed but it has not been already running", closeServer("server"));
	
	// clean-up
	closeServers();
    }

    /**
     * Opening two different RMI-servers
     */
    @Test
    public void serverOpenX1X2() 
	    throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException, URISyntaxException  {
	openServer("x1");
	final String x1 = callServer("x1");
	assertNotNull("X1-Server call returns null", x1);
	openServer("x2");
	final String x2 = callServer("x2");
	assertNotNull("X2-Server call returns null", x2);

	assertNotEquals("The same response from two different servers", x1, x2);
	
	// clean-up
	closeServers();
    }

    /**
     * Opening again the same RMI-server
     */
    @Test
    public void serverOpenXX() 
	    throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException, URISyntaxException  {
	assertTrue("Server wasn't opened", openServer("x"));
	final String x1 = callServer("x");
	assertNotNull("Server call returns null", x1);
	assertFalse("Server was opened but it has been already running", openServer("x"));
	final String x2 = callServer("x");
	assertNotNull("Server call returns null", x2);

	assertEquals("Different responses from the same server", x1, x2);
	assertTrue("Server was not closed", closeServer("x"));
	
	// clean-up
	closeServers();
    }

    /**
     * Closing and reopening of a RMI-server
     */
    @Test
    public void openCloseServerXX() 
	    throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException, URISyntaxException  {
	assertTrue("Server wasn't opened", openServer("x"));
	final String x1 = callServer("x");
	assertNotNull("Server x1-response is null", x1);
	assertTrue("Server was not closed", closeServer("x"));
	assertTrue("Server wasn't opened after closing", openServer("x"));
	final String x2 = callServer("x");
	assertNotNull("Server response is null after re-opening", x2);

	assertNotEquals("The same response from two different servers", x1, x2);
	assertTrue("Server was not closed finally", closeServer("x"));
	
	// clean-up
	closeServers();
    }

    /**
     * Opening RMI-servers using different ports 
     */
    @Test
    public void serverOpenX1X2DifferentPorts() 
	    throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException, URISyntaxException  {
	
	assertTrue("Server wasn't opened", openServer("x", 1001));
	final String x1 = callServer("x",1001);
	assertNotNull("X1-Server call returns null", x1);
	
	assertTrue("Server wasn't opened", openServer("x", 1002));
	final String x2 = callServer("x",1002);
	assertNotNull("X2-Server call returns null", x2);

	assertNotEquals("The same response from servers using different ports", x1, x2);
	
	// clean-up
	assertTrue("X1-Servers were not closed", closeServers(1001));
	assertTrue("X2-Servers were not closed", closeServers(1002));
    }

}
