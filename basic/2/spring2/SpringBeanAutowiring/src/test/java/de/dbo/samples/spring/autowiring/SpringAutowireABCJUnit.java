package de.dbo.samples.spring.autowiring;

import de.dbo.samples.spring.autowiring.special.abc.A;
import de.dbo.samples.spring.autowiring.special.abc.C;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

public class SpringAutowireABCJUnit {
    
    private ClassPathXmlApplicationContext ctx;

    @Before
    public void initSpringBasedApplication() {
	ctx = new ClassPathXmlApplicationContext("abc.xml");
    }
    
    @Test
    public void test() {
	final String response = ctx.getBean(A.class).getB().getC().hallo();
	final String response2 = ctx.getBean(C.class).hallo();
	
	assertTrue("Hi!".equals(response));
	assertTrue(response.equals(response2));
	assertTrue(ctx.getBean(A.class).getB().getC()==ctx.getBean(C.class));
    }
    
    @After
    public void closeSpringBasedApplication() {
	ctx.close();
    }
}
