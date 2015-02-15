package de.dbo.samples.rmi0.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Actual (trivial) implementation of the server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
	private static final long serialVersionUID = 4266492076051423131L;
	private static final Logger log = LoggerFactory.getLogger(ServerImpl.class);

	public ServerImpl() throws RemoteException {

	}

	public String sayHello() {
		log.info(this.hashCode() + " processing request ... ");
		return "Hello World from " + this.hashCode() + "!";
	}
}