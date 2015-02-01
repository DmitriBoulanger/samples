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
	RMIServerStarter.run();
	RMIServiceClient.run();
	RMIServerDestroyer.run();
    }
}
