package de.dbo.samples.rmi0;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Service( server) exposed to clients
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Server extends Remote {

  public String sayHello() throws RemoteException;

}

