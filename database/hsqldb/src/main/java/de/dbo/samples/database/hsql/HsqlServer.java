package de.dbo.samples.database.hsql;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.SmartLifecycle;

import java.io.IOException;
import java.util.Properties;

public final class HsqlServer implements SmartLifecycle {
    private static final Logger log = LoggerFactory.getLogger(HsqlServer.class);

    private HsqlServerConfiguration configuration;
    
    private Server server;
    private boolean running = false;

//    public HsqlServer(Properties props) {
//	properties = new HsqlProperties(props);
//    }
    
    public HsqlServer() {
	
    }

    @Override
    public boolean isRunning() {
	if (server != null) {
	    server.checkRunning(running);
	}
	return running;
    }

    @Override
    public void start() {
	if (server == null) {
	    log.info("Starting HSQL server ...");
	    server = new Server();
	    try {
		server.setProperties(configuration.hsqlProperties());
		server.start();
		running = true;
	    } catch (AclFormatException afe) {
		log.error("Error starting HSQL server: ", afe);
	    } catch (IOException e) {
		log.error("Error starting HSQL server: ", e);
	    }
	} else {
	    log.debug("Ignoring start (HSQL server is already running)");
	}
    }

    @Override
    public void stop() {
	log.info("Stopping HSQL server ...");
	if (server != null) {
	    server.stop();
	    running = false;
	} else {
	    log.debug("Ignoring stop (HSQL server is not running)");
	}
    }

    @Override
    public int getPhase() {
	return 0;
    }

    @Override
    public boolean isAutoStartup() {
	return true;
    }

    @Override
    public void stop(Runnable runnable) {
	stop();
	runnable.run();
    }


    //
    // Getters and Setters
    //
    
    public HsqlServerConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(HsqlServerConfiguration configuration) {
        this.configuration = configuration;
    }

    
   
    
    
    
    
}
