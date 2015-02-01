package de.dbo.samples.spring.rmi.services.cache;

import de.dbo.samples.spring.rmi.user.User;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Cache Service Implementation
 *
 */
@Component
public class CacheServiceImpl implements CacheService {

    /*
     * User Map singleton instance is injected...
     */
    @Autowired(required=true)
    private ConcurrentHashMap<Long, User> userMap;
    
    
    //
    // Getters and Setters
    //

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
     * @param ConcurrentHashMap
     *            User Map
     */
    public void setUserMap(ConcurrentHashMap<Long, User> userMap) {
	this.userMap = userMap;
    }

}
