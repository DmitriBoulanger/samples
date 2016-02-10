package de.dbo.samples.web.jetty;

import static org.junit.Assert.*;

import static de.dbo.samples.web.jetty.JettyPrint.print;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class JettyJUnit  {
    private static final Logger log = LoggerFactory.getLogger(JettyJUnit.class);

    @Test
    public void test() throws Throwable {
	JettySetUp setUp = new JettySetUp();
	setUp.getWars().add(new File("D:/JAVA/WORKSPACES/ws/samples.git/basic/1/maven/00_Simple/00_Simple-2-Web/target/00_Simple-2-Web.war"));
//	setUp.getWars().add(new File("D:/JAVA/WORKSPACES/ws/samples.git/basic/1/maven/06_Enterprise/06_Enterprise-Web/target/06_Enterprise-Web.war"));
	
	JettyStartUp startUp = new JettyStartUp(setUp);
	startUp.doIt();
        Thread.sleep(1000000);
    }
   
}
