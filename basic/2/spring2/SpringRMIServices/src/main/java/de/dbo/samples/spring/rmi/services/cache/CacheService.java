package de.dbo.samples.spring.rmi.services.cache;

import de.dbo.samples.spring.rmi.user.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache Service
 *
 */
public interface CacheService {

	/**
	 * Get User Map
	 * 
	 * @return ConcurrentHashMap User Map
	 */
	public ConcurrentHashMap<Long, User> getUserMap();
	
}
