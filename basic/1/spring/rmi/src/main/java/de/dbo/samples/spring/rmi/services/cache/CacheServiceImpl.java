package de.dbo.samples.spring.rmi.services.cache;

import de.dbo.samples.spring.rmi.user.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache Service Implementation
 *
 */
public class CacheServiceImpl implements CacheService {

	//User Map is injected...
	ConcurrentHashMap<Long, User> userMap;

	/**
	 * Get User Map
	 * 
	 * @return ConcurrentHashMap User Map
	 */
	public ConcurrentHashMap<Long, User> getUserMap() {
		return userMap;
	}

	/**
	 * Set User Map
	 * 
	 * @param ConcurrentHashMap User Map
	 */
	public void setUserMap(ConcurrentHashMap<Long, User> userMap) {
		this.userMap = userMap;
	}
		
}
