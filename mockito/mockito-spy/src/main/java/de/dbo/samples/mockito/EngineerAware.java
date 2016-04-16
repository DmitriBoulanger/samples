package de.dbo.samples.mockito;

public interface EngineerAware {
	Engineer getDesignation();
	
	enum Engineer {
		DEV,QA
	}
}
