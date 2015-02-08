package de.dbo.samples.spring.autowiring;

import static de.dbo.tools.utils.print.Print.*;

import de.dbo.samples.spring.autowiring.model.Employee;
import de.dbo.samples.spring.autowiring.service.EmployeeAutowiredByConstructorService;
import de.dbo.samples.spring.autowiring.service.EmployeeAutowiredByTypeService;
import de.dbo.samples.spring.autowiring.service.EmployeeService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class SpringAutowireJUnit {
    private static final Logger log = LoggerFactory.getLogger(SpringAutowireJUnit.class);

    private ClassPathXmlApplicationContext ctx;

    @Before
    public void initSpringBasedAppliction() {
	ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void test() {

	final EmployeeService serviceByName = ctx.getBean("employeeServiceByName", EmployeeService.class);
	assertNotNull(serviceByName);
	log.info("Autowiring byName. Data Name=" + serviceByName.getEmployee().getName());

	final EmployeeService serviceByType = ctx.getBean("employeeServiceByType", EmployeeService.class);
	assertNotNull(serviceByType);
	log.info("Autowiring byType. Data Name=" + serviceByType.getEmployee().getName());

	final EmployeeService serviceByConstructor = ctx.getBean("employeeServiceConstructor", EmployeeService.class);
	assertNotNull(serviceByConstructor);
	log.info("Autowiring by Constructor. Data Name=" + serviceByConstructor.getEmployee().getName());

	// Printing hash-code to confirm all the objects are of different type
	log.info("Different insances of the service:" + "\n\t - serviceByName        " + serviceByName.hashCode()
		+ "\n\t - serviceByType        " + serviceByType.hashCode() + "\n\t - serviceByConstructor "
		+ serviceByConstructor.hashCode());

	assertNotEquals(serviceByName.hashCode(), serviceByType.hashCode());
	assertNotEquals(serviceByName.hashCode(), serviceByConstructor.hashCode());
	assertNotEquals(serviceByType.hashCode(), serviceByConstructor.hashCode());

	// Testing @Autowired annotations

	final EmployeeAutowiredByTypeService autowiredByTypeService = ctx.getBean("employeeAutowiredByTypeService",
		EmployeeAutowiredByTypeService.class);
	assertNotNull(autowiredByTypeService);
	log.info("@Autowired byType. Data Name=" + autowiredByTypeService.getEmployee().getName());

	final EmployeeAutowiredByConstructorService autowiredByConstructorService = 
		ctx.getBean("employeeAutowiredByConstructorService", EmployeeAutowiredByConstructorService.class);
	assertNotNull(autowiredByConstructorService);
	log.info("@Autowired by Constructor. Data Name=" + autowiredByConstructorService.getEmployee().getName());

	log.info("Different insances of the service:" + "\n\t - autowiredByTypeService        "
		+ autowiredByTypeService.hashCode() + "\n\t - autowiredByConstructorService "
		+ serviceByType.hashCode());
	assertNotEquals(autowiredByTypeService.hashCode(), serviceByType.hashCode());
    }
    
    @Test
    public void stange() {
	
	final ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("spring.xml");
	assertEquals(
		ctx1.getBean("employee", Employee.class),
		ctx1.getBean("employee", Employee.class));
	assertNotEquals(
		ctx1.getBean("employee", Employee.class),
		ctx1.getBean("employee2", Employee.class));
	final Employee employeeCTX1 = ctx1.getBean("employee", Employee.class);
	ctx1.close();
	
	final ClassPathXmlApplicationContext ctx2 = new ClassPathXmlApplicationContext("spring.xml");
	final Employee employeeCTX2 = ctx2.getBean("employee", Employee.class);
	ctx2.close();
	
	assertNotNull(employeeCTX1);
	assertNotNull(employeeCTX2);
	assertNotEquals(employeeCTX1,employeeCTX2);
    }
    
    @Test
    public void environment() {
	final ConfigurableEnvironment configurableEnvironment = ctx.getEnvironment();
	log.info ("SystemEnvironment: " +  lines(configurableEnvironment.getSystemEnvironment()) );
	log.info ("SystemProperties:  " +  lines(configurableEnvironment.getSystemProperties()) );
	log.info ("DefaultProfiles:   " +  line(configurableEnvironment.getDefaultProfiles()) );
	log.info ("ActiveProfiles:    " +  line(configurableEnvironment.getActiveProfiles()) );
    }

    @After
    public void colseSpringBasedAppliction() {
	assertNotNull(ctx);
	ctx.close();
	ctx = null;
    }

}
