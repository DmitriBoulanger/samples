package de.dbo.samples.spring.rmi.server;

import de.dbo.samples.spring.rmi.services.cache.CacheService;
import de.dbo.samples.spring.rmi.services.manager.ManagerService;
import de.dbo.samples.spring.rmi.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
 
/**
 * RMI User Service Implementation
 *
 */
public class RMIUserServiceImpl implements RMIUserService {
	private static Logger log = LoggerFactory.getLogger(RMIUserServiceImpl.class);
	
	// Remote Cache Service is injected...
	CacheService cacheService;
	
	ManagerService managerService;
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
	public boolean addUser(User user) {
		getCacheService().getUserMap().put(user.getId(), user);
		log.debug("User has been added to cache. User : "+getCacheService().getUserMap().get(user.getId()));
		return true;
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
	public boolean deleteUser(User user) {
		getCacheService().getUserMap().remove(user.getId());
		log.debug("User has been deleted from cache. User : "+user);
		return true;
	}

	/**
	 * Get User List
	 * 
	 * @return List user list
	 */
	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		list.addAll(getCacheService().getUserMap().values());
		log.debug("User List : "+list);
		return list;
	}
	

	/**
	 * Get RMI User Service
	 * 
	 * @return RMIUserService RMI User Service
	 */
	public CacheService getCacheService() {
		return cacheService;
	}

	/**
	 * Set RMI User Service
	 * 
	 * @param RMIUserService RMI User Service
	 */
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

}
