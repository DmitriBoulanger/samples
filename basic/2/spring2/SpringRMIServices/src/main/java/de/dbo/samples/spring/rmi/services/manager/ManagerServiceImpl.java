package de.dbo.samples.spring.rmi.services.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerServiceImpl implements ManagerService {
	private static final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);
	
	@Override
	public boolean shutdown() {
		 log.info("SHUT DOWN ...");
		 return true;
	}

}
