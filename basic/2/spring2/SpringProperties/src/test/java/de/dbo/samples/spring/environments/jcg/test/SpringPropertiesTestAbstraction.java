package de.dbo.samples.spring.environments.jcg.test;

import junit.framework.TestCase;

import de.dbo.samples.spring.environments.env.GenericEnv;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;
import de.dbo.samples.spring.environments.jcg.prop.JmsProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
abstract class SpringPropertiesTestAbstraction extends TestCase {
	
    @Autowired
    private GenericEnv env;

    @Autowired
    private DatabaseProperties dbProp;

    @Autowired
    private JmsProperties jmsProp;

    protected final void print() {
	System.out.println("Environment        : " + env.toString());
	System.out.println("Database Properties: " + dbProp.toString());
	System.out.println("JMS Properties     : " + jmsProp.toString());
    }

    public abstract void testAppProperties();

}
