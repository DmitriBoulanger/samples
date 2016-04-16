package de.dbo.samples.mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import de.dbo.samples.mockito.Employee;

import org.mockito.InOrder;
import org.junit.Before;
import org.junit.Test;


public class MockitoSpyExample {
    
	private Employee spyEmp;
	private Employee emp;
	private static final String FIRST_NAME = "Joe";
	private static final String LAST_NAME = "M";
	private static final int AGE = 35;
	
	@Before
	public void buildSpy() {
		emp = new Employee(FIRST_NAME, LAST_NAME, AGE);
		spyEmp = spy(emp);
	}
	
	@Test
	public void verifySpyEffectOnRealInstance() {
		spyEmp.setAge(20);
		assertFalse(emp.getAge() == spyEmp.getAge());
	}
	
	@Test
	public void verifyEmployeeDetails() {
		System.out.println("Full name:" + spyEmp.getFullName());
		System.out.println("Age:" + spyEmp.getAge());
		
		InOrder inOrder = inOrder(spyEmp);
		
		System.out.println("Verify emp.getFullName() calls getFirstName() and then getLastName()");
		inOrder.verify(spyEmp).getFirstName();
		inOrder.verify(spyEmp).getLastName();
		
		System.out.println("Verify emp.getAge() is called");
		verify(spyEmp).getAge();
		
		assertEquals(spyEmp.getFirstName(), FIRST_NAME);
		assertEquals(spyEmp.getLastName(), LAST_NAME);
		assertEquals(spyEmp.getFullName(), FIRST_NAME + " " + LAST_NAME);
		assertEquals(spyEmp.getAge(), AGE);
		
		System.out.println("Verify emp.getFullName() called twice");
		verify(spyEmp, times(2)).getFullName();
	}
	
	@Test
	public void spyEmployeeName() {
		final String I_AM = "I am";
		final String THE_SPY = "the Spy";
		System.out.println("Train employee to return " + I_AM + " when emp.getFirstName() is called");
		when(spyEmp.getFirstName()).thenReturn(I_AM);
		System.out.println("Full Name: " + spyEmp.getFullName());
		assertEquals(I_AM + " M", spyEmp.getFullName());
		
		System.out.println("Train employee to return " + THE_SPY + " when emp.getLastName() is called");
		when(spyEmp.getLastName()).thenReturn(THE_SPY);
		System.out.println("Full Name: " + spyEmp.getFullName());
		assertEquals(I_AM + " " + THE_SPY, spyEmp.getFullName());
	}
	
	@Test
	public void spySkillUsingWhenThenReturn() {
		when(spyEmp.getSkill(0)).thenReturn("SPY");
		assertEquals("SPY", spyEmp.getSkill(0));
	}
	
	@Test
	public void spySkillUsingDoWhen() {
		doReturn("SPY").when(spyEmp).getSkill(0);
		assertEquals("SPY", spyEmp.getSkill(0));
	}
}