package de.dbo.samples.rmi0;

import static de.dbo.tools.utils.print.Print.line;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStartUp {
	private static final Logger log = LoggerFactory
			.getLogger(ServerStartUp.class);

	public static void main(final String args[]) throws RemoteException {
		Thread.currentThread().setName("Server start-up");
		openServer("server", 1099);
	}

	public static final boolean openServer(final String name, int port)
			throws RemoteException {
		log.info("Server openning ...");
		Registry registry;
		try {
			registry = LocateRegistry.createRegistry(port);
			log.info("Registry created");
		} catch (Exception e) {
			registry = LocateRegistry.getRegistry(port);
			log.info("Registry exists");
		}

		if (isInRegistery(registry,name)) {
			log.info("Server [" + name
					+ "] is already running. Registry contents " + print(registry));
			return false;
		}
		registry.rebind(name, new ServerImpl());
		log.info("Registry contents [" + line(registry.list()) + "]");
		log.info("Server [" + name + "] created and it is running ... ");
		return true;
	}

	public static final boolean closeServer(final String name, int port)
			throws RemoteException, NotBoundException {
		log.info("Server closing...");
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			log.info("Registry exists");
		} catch (Exception e) {
			log.info("Registry not found", e);
			return false;
		}

		if (isInRegistery(registry,name)) {
			registry.unbind(name);
			log.info("Server was closed. Registry contents " + print(registry));
			return true;
		} else {
			log.info("Server [" + name + "] was not closed since it has not been running");
			return false;
		}
	}
	
	private static final boolean isInRegistery(final Registry registry, final String name) 
			throws AccessException, RemoteException {
		return null != registry.list()
				&& Arrays.asList(registry.list()).contains(name);
	}
	
	private static final String print(final Registry registry) 
			throws AccessException, RemoteException {
		return "["+ line(registry.list()).toString().trim() + "]";
	}

}