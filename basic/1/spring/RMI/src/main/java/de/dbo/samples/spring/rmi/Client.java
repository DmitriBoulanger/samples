package de.dbo.samples.spring.rmi;

import static de.dbo.tools.utils.print.Print.lines;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Client's call of the RMI-server (service)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 *
 */
public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException {
	if (!logRegistry()) {
	    return;
	}
	
	final Client client = new Client();
	log.info("ping=" + client.ping() + " cube = " + client.cube(7));
    }

    private final ClassPathXmlApplicationContext ctx;

    public Client() {
	ctx = new ClassPathXmlApplicationContext("client.xml");
	close();
    }
    
    public static boolean logRegistry() throws RemoteException {
	final Registry registry;
	try {
	    registry = LocateRegistry.getRegistry("localhost");
	} catch (Exception e) {
	    log.error("Can't find RMI-registry at the localhost: ", e);
	    return false;
	}
	final String[] names = registry.list();
	log.info("Founf in the registry: " + lines(names));
	return true;
    }

    public void close() {
	ctx.close();
	log.info("ctx closed (next call requires refresh)");
    }
    
    public void open() {
	ctx.refresh();
   	log.info("ctx opened");
       }

    public String ping() {
	open();
	final String ret = getService().ping();
	close();
	return ret;
    }

    public int cube(int x) {
	open();
	final int ret = getService().cube(x);
	close();
	return ret;
    }
    
    private final Service getService() {
	return ctx.getBean("serviceProxy", Service.class);
    }

}
