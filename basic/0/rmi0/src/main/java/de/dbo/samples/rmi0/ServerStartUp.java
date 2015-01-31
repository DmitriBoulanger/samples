package de.dbo.samples.rmi0;

import static de.dbo.tools.utils.print.Print.line;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStartUp {
	private static final Logger log = LoggerFactory.getLogger(ServerStartUp.class);

	public static void main(final String args[]) throws RemoteException {
		Thread.currentThread().setName("Server start-up");
		createServer("server", 1099);
	}

	public static final boolean createServer(final String name, int port) throws RemoteException {
			Registry registry;
			try {
				registry = LocateRegistry.createRegistry(port);
				log.info("Registry created");
			} catch (Exception e) {
				registry = LocateRegistry.getRegistry(port);
				log.info("Registry exists");
			}
			
			if (null!=registry.list() && Arrays.asList(registry.list()).contains(name)) {
				log.info("Server ["+name+"] is already running. Registry contents [" + line(registry.list()) + "]");
				return false;
			}
			registry.rebind(name, new ServerImpl());
			log.info("Registry contents [" + line(registry.list()) + "]");
			log.info("Server ["+name+"] created and it is running ... ");
			return true;
	}

}