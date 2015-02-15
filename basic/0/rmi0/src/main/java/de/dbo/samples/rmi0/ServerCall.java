package de.dbo.samples.rmi0;

import de.dbo.samples.rmi0.server.Server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.rmi.ConnectException;
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
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 *
 */
public class ServerCall {
    private static final Logger log = LoggerFactory.getLogger(ServerCall.class);
    
    /**
     * call the default RMI-Server
     * 
     * @throws MalformedURLException
     * @throws RemoteException
     * @throws NotBoundException
     * @throws UnknownHostException
     * @throws URISyntaxException
     */
    public static void main(String args[]) 
	    throws MalformedURLException, RemoteException, NotBoundException, UnknownHostException, URISyntaxException {
	try {
	    callServer("server");
	} catch (ConnectException e) {
	    log.error("Server is not running? See: ", null!=e.getCause()? e.getCause(): e);
	}
    }

    public static String callServer(final String name) 
	    throws MalformedURLException, RemoteException, NotBoundException, UnknownHostException, URISyntaxException {
	return callServer(name,"localhost",1099);
    }
    
    public static String callServer(final String name, int port) 
	    throws MalformedURLException, RemoteException, NotBoundException, UnknownHostException, URISyntaxException {
	return callServer(name,"localhost",port);
    }
    
    public static String callServer(final String name, String host, int port) 
	    throws RemoteException, NotBoundException, UnknownHostException, URISyntaxException, MalformedURLException {
	final String uri = rmiURL(name, host, port).toString();
	final Server server = (Server) Naming.lookup(uri);
	final String result = server.sayHello();
	log.info(result);
	return result;
    }
    
    private static final URI rmiURL(final String name, final String host, int port) 
	    throws UnknownHostException, URISyntaxException {
	final String rmihost;
	if (null==host) {
	    rmihost = InetAddress.getLocalHost().getHostAddress();
	}
	else if ("localhost".equalsIgnoreCase(host)) {
	    rmihost = InetAddress.getLocalHost().getHostName();
	} else {
	    rmihost = InetAddress.getByName(host).getCanonicalHostName();
	}
	final URI uri = new URI( "rmi://"+rmihost+":"+port+"/"+ name);
	log.info("URI = " + uri);
	return uri;
    }
    
}
