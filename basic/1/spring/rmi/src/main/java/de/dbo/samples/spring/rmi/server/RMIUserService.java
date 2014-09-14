package de.dbo.samples.spring.rmi.server;

import de.dbo.samples.spring.rmi.user.User;

import java.util.List;

/**
 * RMI User Service Interface
 *
 */
public interface RMIUserService{

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
