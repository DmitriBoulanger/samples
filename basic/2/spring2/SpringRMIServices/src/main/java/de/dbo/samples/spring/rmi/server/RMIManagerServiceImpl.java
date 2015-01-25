package de.dbo.samples.spring.rmi.server;

import de.dbo.samples.spring.rmi.services.manager.ManagerService;

public class RMIManagerServiceImpl implements RMIManagerService {
	
	private ManagerService managerService;

	@Override
	public boolean shutdown() {
		return managerService.shutdown();
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
}
