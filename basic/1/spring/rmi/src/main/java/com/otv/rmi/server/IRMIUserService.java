package com.otv.rmi.server;

import java.util.List;

import com.otv.user.User;

/**
 * RMI User Service Interface
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public interface IRMIUserService{

	/**
	 * Add User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
	public boolean addUser(User user);
	
	/**
	 * Delete User
	 * 
	 * @param  User user
	 * @return boolean response of the method
	 */
	public boolean deleteUser(User user);
	
	
	/**
	 * Get User List
	 * 
	 * @return List user list
	 */
	public List<User> getUserList();
	
}
