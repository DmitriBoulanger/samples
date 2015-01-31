package de.dbo.samples.rmi0;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client call of the server (service)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Client {
	private static final Logger log = LoggerFactory.getLogger(ServerStartUp.class);

  public static void main(String args[]) 
		  throws MalformedURLException, RemoteException, NotBoundException {
	  
      final Server server = (Server) Naming.lookup("server");
      final String result = server.sayHello();
      log.info(result);
      
  }

}

