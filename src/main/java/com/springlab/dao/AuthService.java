package com.springlab.dao;

import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.User;

public interface AuthService {
	/**
	 * Verify login information
	 * @param username
	 * @param password
	 * @return userid of the account if valid, null if not
	 */
	public Integer checkLogin(String username,String password);
	/**
	 * Register new user (not approved yet)
	 * @param user
	 * @return id of new user
	 */
	public Integer registerNewUser(Login log,User user);
	
	/**
	 * Find user id by name
	 * @param name- username used for logged in 
	 * @return id of the user
	 */
	public Integer findUserIdByName(String name);
}
