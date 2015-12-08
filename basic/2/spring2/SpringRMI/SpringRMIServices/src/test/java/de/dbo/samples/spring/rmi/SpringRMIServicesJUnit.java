package de.dbo.samples.spring.rmi;

import de.dbo.samples.spring.rmi.client.RMIServiceClient;
import de.dbo.samples.spring.rmi.server.starter.RMIServerDestroyer;
import de.dbo.samples.spring.rmi.server.starter.RMIServerStarter;

import java.rmi.RemoteException;

import org.junit.Test;

public class SpringRMIServicesJUnit {
    
    /**
     * Complete story
     */
    @Test
    public void completeStory() throws RemoteException {
	final RMIServerStarter rmiServerStarter =  new RMIServerStarter(); 
	rmiServerStarter.run();
	
	new RMIServiceClient().run();
	new RMIServerDestroyer().run();
    }
}
