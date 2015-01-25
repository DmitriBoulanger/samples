package de.dbo.samples.spring.autowiring;

import de.dbo.samples.spring.autowiring.service.EmployeeAutowiredByConstructorService;
import de.dbo.samples.spring.autowiring.service.EmployeeAutowiredByTypeService;
import de.dbo.samples.spring.autowiring.service.EmployeeService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class SprintAutowireJUnitTest {
	private static final Logger log = LoggerFactory.getLogger(SprintAutowireJUnitTest.class);
	
	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void initSpringBasedAppliction() {
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		assertNotNull(ctx);
	}

	@Test
	public void test() {
		
		final EmployeeService serviceByName = ctx.getBean("employeeServiceByName", EmployeeService.class);
		assertNotNull(serviceByName);
		log.info("Autowiring byName. Employee Name="+serviceByName.getEmployee().getName());
		
		final EmployeeService serviceByType = ctx.getBean("employeeServiceByType", EmployeeService.class);
		assertNotNull(serviceByType);
		log.info("Autowiring byType. Employee Name="+serviceByType.getEmployee().getName());
		
		final EmployeeService serviceByConstructor = ctx.getBean("employeeServiceConstructor", EmployeeService.class);
		assertNotNull(serviceByConstructor);
		log.info("Autowiring by Constructor. Employee Name="+serviceByConstructor.getEmployee().getName());
		
		// Printing hash-code to confirm all the objects are of different type
		log.info("Different insances of the service:"
				+"\n\t - serviceByName        " + serviceByName.hashCode()
				+"\n\t - serviceByType        " + serviceByType.hashCode()
				+"\n\t - serviceByConstructor " + serviceByConstructor.hashCode());
		
		assertNotEquals(serviceByName.hashCode(), serviceByType.hashCode());
		assertNotEquals(serviceByName.hashCode(), serviceByConstructor.hashCode());
		assertNotEquals(serviceByType.hashCode(), serviceByConstructor.hashCode());
		
		// Testing @Autowired annotations
		
		final EmployeeAutowiredByTypeService autowiredByTypeService = ctx.getBean("employeeAutowiredByTypeService",EmployeeAutowiredByTypeService.class);
		assertNotNull(autowiredByTypeService);
		log.info("@Autowired byType. Employee Name=" + autowiredByTypeService.getEmployee().getName());

		final EmployeeAutowiredByConstructorService autowiredByConstructorService = ctx.getBean("employeeAutowiredByConstructorService",EmployeeAutowiredByConstructorService.class);
		assertNotNull(autowiredByConstructorService);
		log.info("@Autowired by Constructor. Employee Name=" + autowiredByConstructorService.getEmployee().getName());
		
		log.info("Different insances of the service:"
				+"\n\t - autowiredByTypeService        " + autowiredByTypeService.hashCode()
				+"\n\t - autowiredByConstructorService " + serviceByType.hashCode());
		assertNotEquals(autowiredByTypeService.hashCode(), serviceByType.hashCode());
	}
	
	@After
	public void colseSpringBasedAppliction() {
		assertNotNull(ctx);
		ctx.close();
		ctx = null;
	}

}
