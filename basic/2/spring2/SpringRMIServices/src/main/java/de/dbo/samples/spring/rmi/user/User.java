package de.dbo.samples.spring.rmi.user;

import java.io.Serializable;

/**
 * User Bean
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = -5653695678607999093L;
	
	private long id;
	private String name;
	private String surname;
	
	/**
	 * Get User Id
	 * 
	 * @return long id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param long id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get User Name
	 * 
	 * @return long id
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set User Name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get User Surname
	 * 
	 * @return long id
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set User Surname
	 * 
	 * @param String surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
		
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Id : ").append(getId());
		strBuilder.append(", Name : ").append(getName());
		strBuilder.append(", Surname : ").append(getSurname());
		return strBuilder.toString();
	}
}
