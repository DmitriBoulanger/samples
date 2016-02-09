package de.dbo.samples.web.jetty;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jetty.runner.Runner;
import org.eclipse.jetty.runner.Runner.Classpath;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.PathResource;

/**
 * Set-up (configuration) of Jetty  server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class JettySetUp  {
    
    private int port = 8080 /* port to listen on (default 8080)")*/;
    private int stopPort = 8888 /* port to listen for stop command */;
    
    private List<File> wars = new ArrayList<File>();
    
    private static final String ARG_PREFIX = "--";
    private static final String ARG_SUFFIX = " ";

    public JettySetUp() {
	 
    }
    
    public void configure(final Runner runner) throws Exception {
	final List<String> args =  new ArrayList<String>();

	for (File war:wars) {
	   args.add(Resource.newResource(war).toString());
	}
	
	
	runner.configure(args.toArray(args.toArray(new String[args.size()])));
	
    }
    
    private static final String arg(final String name) {
	return ARG_PREFIX + name + ARG_SUFFIX;
    }

 
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getStopPort() {
        return stopPort;
    }

    public void setStopPort(int stopPort) {
        this.stopPort = stopPort;
    }

    public List<File> getWars() {
        return wars;
    }

    public void setWars(List<File> wars) {
        this.wars = wars;
    }

}
