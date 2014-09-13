package com.otv.cache.service;

import java.util.concurrent.ConcurrentHashMap;

import com.otv.user.User;

/**
 * Cache Service Implementation
 * 
 * @author  onlinetechvision.com
 * @since   6:04:49 PM
 * @version 1.0.0
 *
 */
public class CacheService implements ICacheService {

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
