package de.dbo.samples.gui.swing.treetable.origins.xexamples.treetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data extends ArrayList<Department> {
	private static final long serialVersionUID = -7664209020131938648L;
	
	public Data() {
	
	List<Employee> empList = new ArrayList<Employee>();
	empList.add(new Employee(1, "Ram", Calendar.getInstance().getTime(),"emp2"));
	empList.add(new Employee(2, "Krishna", Calendar.getInstance().getTime(), "emp3"));

	// create and add the first department with its list of Employee objects
	add(new Department(1, "Sales", empList));

	List<Employee> empList2 = new ArrayList<Employee>();
	empList2.add(new Employee(3, "Govind",Calendar.getInstance().getTime(), "emp2"));
	empList2.add(new Employee(4, "Kiran", Calendar.getInstance().getTime(),"emp1"));
	empList2.add(new Employee(5, "Karthik", Calendar.getInstance().getTime(), "emp2"));

	// create and add the first department with its list of Employee objects
	add(new Department(2, "Marketing", empList2));
	}

}
