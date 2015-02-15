package de.dbo.samples.rmi0;

import static de.dbo.samples.rmi0.RegistryManagement.openServer;

import java.rmi.RemoteException;

public class ServerStratUp {
    
    public static final void main(final String[] args) throws RemoteException {
	 openServer("server");
    }

}
