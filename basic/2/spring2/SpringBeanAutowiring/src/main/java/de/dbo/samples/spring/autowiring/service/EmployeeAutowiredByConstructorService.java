package de.dbo.samples.spring.autowiring.service;

import de.dbo.samples.spring.autowiring.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class EmployeeAutowiredByConstructorService {

	private final Employee employee;

	/* Autowired annotation on Constructor is equivalent to autowire="constructor" */
	@Autowired(required=true)
	public EmployeeAutowiredByConstructorService(@Qualifier("employee") Employee emp){
		this.employee=emp;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
}
