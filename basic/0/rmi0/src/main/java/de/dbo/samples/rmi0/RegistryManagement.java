package de.dbo.samples.rmi0;

import static de.dbo.tools.utils.print.Print.line;

import de.dbo.samples.rmi0.server.ServerImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistryManagement {
    private static final Logger log = LoggerFactory.getLogger(RegistryManagement.class);

    public static final boolean openServer(final String name) throws RemoteException {
	return openServer(name, 1099);
    }

    public static final boolean closeServer(final String name) throws RemoteException, NotBoundException {
	return closeServer(name, 1099);
    }

    public static final boolean closeServers() throws RemoteException, NotBoundException {
	return closeServers(1099);
    }

    public static final boolean openServer(final String name, int port) throws RemoteException {
	log.info(printServer(name, port) + " openning ...");
	Registry registry;
	try {
	    registry = LocateRegistry.createRegistry(port);
	    log.info(printRegistery(port) + " created");
	} catch (ExportException e) {
	    log.info(printRegistery(port) + " exists. LocateRegistry.createRegistry(" + port + "): " + print(e));
	    registry = LocateRegistry.getRegistry(port);
	} catch (Exception e) {
	    throw new RuntimeException("Unexpected error while creating RMI-registry at port " + port, e);
	}

	if (isInRegistery(registry, name)) {
	    log.info(printServer(name, port) + " is already running. Registry:" + port + ": " + print(registry));
	    return false;
	}
	registry.rebind(name, new ServerImpl());
	log.info(printServer(name, port) + " created and it is running. Registry:" + port + ": " + print(registry));
	return true;
    }

    public static final boolean closeServer(final String name, int port) throws RemoteException, NotBoundException {
	log.info(printServer(name, port) + port + " closing...");
	Registry registry;
	try {
	    registry = LocateRegistry.getRegistry(port);
	    log.info(printRegistery(port) + " exists");
	} catch (ExportException e) {
	    log.info(printRegistery(port) + " not found. LocateRegistry.getRegistry(" + port + "): " + print(e), e);
	    return false;
	} catch (Exception e) {
	    throw new RuntimeException("Unexpected error while getting RMI-registry at port " + port, e);
	}

	if (isInRegistery(registry, name)) {
	    registry.unbind(name);
	    log.info(printServer(name, port) + " was closed. Registry@" + port + ": " + print(registry));
	    return true;
	} else {
	    log.info(printServer(name, port) + " was not closed since it has not been running");
	    return false;
	}
    }

    public static final boolean closeServers(int port) throws RemoteException, NotBoundException {
	log.info("Servers:" + port + " closing ...");
	Registry registry;
	try {
	    registry = LocateRegistry.getRegistry(port);
	    log.info(printRegistery(port) + " exists");
	} catch (ExportException e) {
	    log.info(printRegistery(port) + " not found. LocateRegistry.getRegistry(" + port + "): " + print(e), e);
	    return false;
	} catch (Exception e) {
	    throw new RegistryManagementException("Unexpected error while getting RMI-registry at port " + port, e);
	}

	final String[] names = registry.list();
	if (null != names && 0 != names.length) {
	    for (String name : names) {
		closeServer(name, port);
	    }
	    return true;
	}

	return false;
    }

    public static final boolean isInRegistery(final Registry registry, final String name) throws AccessException,
	    RemoteException {
	return null != registry.list() && Arrays.asList(registry.list()).contains(name);
    }

    public static final String print(final Registry registry) throws AccessException, RemoteException {
	return "[" + line(registry.list()).toString().trim() + "]";
    }

    //
    // HELPERS
    //

    private static final String print(ExportException e) {
	return e.getClass().getName() + " " + e.getMessage();
    }

    private static final String printServer(String name, int port) {
	return "Server@" + name + ":" + port;
    }

    private static final String printRegistery(int port) {
	return "Registry:" + port;
    }

}
