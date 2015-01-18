package com.journaldev.spring.autowiring.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.journaldev.spring.autowiring.service.EmployeeAutowiredByConstructorService;
import com.journaldev.spring.autowiring.service.EmployeeAutowiredByTypeService;
import com.journaldev.spring.autowiring.service.EmployeeService;

public class SpringMain {
	private static final Logger log = LoggerFactory.getLogger(SpringMain.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		EmployeeService serviceByName = ctx.getBean("employeeServiceByName", EmployeeService.class);
		
		log.info("Autowiring byName. Employee Name="+serviceByName.getEmployee().getName());
		
		EmployeeService serviceByType = ctx.getBean("employeeServiceByType", EmployeeService.class);
		
		log.info("Autowiring byType. Employee Name="+serviceByType.getEmployee().getName());
		
		EmployeeService serviceByConstructor = ctx.getBean("employeeServiceConstructor", EmployeeService.class);
		
		log.info("Autowiring by Constructor. Employee Name="+serviceByConstructor.getEmployee().getName());
		
		//printing hashcode to confirm all the objects are of different type
		log.info(serviceByName.hashCode()+"::"+serviceByType.hashCode()+"::"+serviceByConstructor.hashCode());
		
		//Testing @Autowired annotations
		EmployeeAutowiredByTypeService autowiredByTypeService = ctx.getBean("employeeAutowiredByTypeService",EmployeeAutowiredByTypeService.class);
		
		log.info("@Autowired byType. Employee Name="+autowiredByTypeService.getEmployee().getName());

		EmployeeAutowiredByConstructorService autowiredByConstructorService = ctx.getBean("employeeAutowiredByConstructorService",EmployeeAutowiredByConstructorService.class);
		
		log.info("@Autowired by Constructor. Employee Name="+autowiredByConstructorService.getEmployee().getName());

		ctx.close();
	}

}
