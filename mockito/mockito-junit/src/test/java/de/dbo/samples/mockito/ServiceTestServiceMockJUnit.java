package de.dbo.samples.mockito;

import static org.mockito.Mockito.*;

import de.dbo.samples.mockito.TestService;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * The Class ServiceTestServiceMockJUnit.
 */
public class ServiceTestServiceMockJUnit {

    /**
     * Test 1.
     */
    @Test
    public void test1_SimpleInt() {
	// create mock
	TestService serviceMock = Mockito.mock(TestService.class);

	// define return value for method getUniqueId()
	when(serviceMock.getUniqueId()).thenReturn(43);

	// use mock in test....
	assertEquals(serviceMock.getUniqueId(), 43);
    }

    /**
     * Test 2. More than one return value. Demonstrates the return of multiple
     * values
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void test2_MoreThanOneReturnValue() {
	final Iterator i = mock(Iterator.class);
	when(i.next()).thenReturn("Mockito").thenReturn("is neat!!");
	String result = i.next() + " " + i.next();
	assertEquals("Mockito is neat!!", result);
    }

    /**
     * Test 3. Return value dependent on method parameter.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void test3_ReturnValueDependentOnMethodParameter() {
	Comparable c = mock(Comparable.class);
	when(c.compareTo("Mockito")).thenReturn(1);
	when(c.compareTo("Eclipse")).thenReturn(2);
	// assert
	assertEquals(1, c.compareTo("Mockito"));
    }

    /**
     * Test 4. Return value in dependent on method parameter.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void test4_ReturnValueInDependentOnMethodParameter() {
	final Comparable c = mock(Comparable.class);
	when(c.compareTo(anyInt())).thenReturn(-1);
	assertEquals(-1, c.compareTo(9));
    }

    /**
     * Test 5. Configuring service-mock and testing/verifying it
     */
    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void test5_VerifyMockedService() {

	// -------------------------------------------------------------------
	// Creation and configuration of the service-mock
	final TestService test = Mockito.mock(TestService.class);
	when(test.getUniqueId()).thenReturn(43);
	// -------------------------------------------------------------------

	// -------------------------------------------------------------------
	// Test-sequence of method calls in the mocked-service, 
	// e.g. testing-method called on the mock with parameter 12
	// -------------------------------------------------------------------
	test.testing(12);
	test.getUniqueId();
	test.getUniqueId();
	test.someMethod("Hello World");
	test.someMethod("called at least once");
	test.someMethod("called at least twice");
	test.someMethod("called five times");
	test.someMethod("called at most 3 times");
	
	//
	// Verification
	//

	// check if testing-method was called with the parameter 12
	verify(test).testing(Matchers.eq(12));

	// was the getUniqueId-method called twice?
	verify(test, times(2)).getUniqueId();

	// other alternatives for verifying 
	// the number of method calls for a method
	verify(test, never()).someMethod("never called");
	verify(test, atLeastOnce()).someMethod("called at least once");

	// fails because we didn't met the conditions.
	verify(test, atLeast(2)).someMethod("called at least twice");
	verify(test, times(5)).someMethod("called five times");
	verify(test, atMost(3)).someMethod("called at most 3 times");
    }

}
