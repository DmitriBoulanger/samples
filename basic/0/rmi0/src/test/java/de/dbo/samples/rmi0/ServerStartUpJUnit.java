package de.dbo.samples.rmi0;

import static de.dbo.samples.rmi0.ServerStartUp.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerStartUpJUnit {
	
	@Test
	public void openClose() 
			throws RemoteException, NotBoundException {
		openServer("x", 1099);
		
		assertTrue("Server wasn't opened", openServer("server", 1099));
		assertFalse("Server was opened but it has been already running", openServer("server", 1099));
		assertTrue("Server was not closed",closeServer("server", 1099));
		assertFalse("Server was closed but it has been already running",closeServer("server", 1099));
		
		
	}

}
