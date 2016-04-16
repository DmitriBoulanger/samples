package de.dbo.samples.mockito;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import de.dbo.samples.mockito.EngineerAware;
import de.dbo.samples.mockito.EngineerAware.Engineer;

public class SpyOnInterfaceExample {
	@Test
	public void spyOnInterface() {
		EngineerAware engineerAware = spy(new Dev());
		assertEquals(Engineer.DEV, engineerAware.getDesignation());
		when(engineerAware.getDesignation()).thenReturn(Engineer.QA);
		assertEquals(Engineer.QA, engineerAware.getDesignation());
	}	
	private class Dev implements EngineerAware {
		@Override
		public Engineer getDesignation() {
			return Engineer.DEV;
		}
		
	}
}
