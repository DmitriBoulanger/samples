package de.dbo.samples.spring.autowiring.service;

import de.dbo.samples.spring.autowiring.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeAutowiredByTypeService {

	/* Autowired annotation on variable/setters is equivalent to autowire="byType" */
	@Autowired
	private Employee employee;
	
//	@Autowired
	public void setEmployee(Employee emp){
		this.employee=emp;
	}
	
	public Employee getEmployee(){
		return this.employee;
	}
}
