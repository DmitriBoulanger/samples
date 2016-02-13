package de.dbo.samples.web.jetty;

import java.io.File;

import org.junit.Test;
 

public class JettyJUnit  {
 
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
