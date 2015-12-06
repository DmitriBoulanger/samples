package de.dbo.samples.spring.environments.jcg.test;

import de.dbo.samples.spring.environments.env.GenericEnv;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;
import de.dbo.samples.spring.environments.jcg.prop.JmsProperties;

import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
abstract class TestAbstraction extends TestCase {
	
    @Autowired
    private GenericEnv env;

    @Autowired
    private DatabaseProperties dbProp;

    @Autowired
    private JmsProperties jmsProp;

    protected final void print() {
	
	System.out.println("Environment        : " + env.toString());
	System.out.println(dbProp.print());
	System.out.println(jmsProp.print());
    }

    public abstract void testAppProperties();

}
