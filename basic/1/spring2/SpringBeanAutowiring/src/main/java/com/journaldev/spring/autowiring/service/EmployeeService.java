package com.journaldev.spring.autowiring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.journaldev.spring.autowiring.model.Employee;

public class EmployeeService {
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	private Employee employee;

	// constructor is used for autowiring by constructor
	public EmployeeService(Employee emp) {
		log.info("Autowiring by constructor used");
		this.employee = emp;
	}

	// default constructor to avoid BeanInstantiationException for autowiring
	// byName or byType
	public EmployeeService() {
		log.info("Default Constructor used");
	}

	// used for autowire byName and byType
	public void setEmployee(Employee emp) {
		this.employee = emp;
	}

	public Employee getEmployee() {
		return this.employee;
	}
}
