package com.springlab.dao;

import java.util.List;

import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.Role;
import com.springlab.hibernate.model.User;

public interface AdminService {
	
	/**
	 * Approve an user registration
	 * @param userid
	 */
	public void approveUser(Integer userid);
	
	/**
	 * Approve a post
	 * @param postid
	 */
	public void approvePost(Integer postid);
	
	/**
	 * Delete an user
	 * @param userid
	 */
	public void deleteUser(Integer userid);
	
	/**
	 * Delete a post
	 * @param postid
	 */
	public void deletePost(Integer postid);
	
	/**
	 * Get all users
	 * @return
	 */
	public List<User> getUsers();
	
	/**
	 * Get info of an user
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
	
	/**
	 * Get all login info
	 * @return
	 */
	public List<Login> getLogin();
	
	/**
	 * Get all posts
	 * @return all posts
	 */
	public List<Post> getAllPosts();
	
	/**
	 * Get rolename based on roleid
	 * @param roleid
	 * @return rolename
	 */
	public String getRole(Integer roleid);
	
	/**
	 * Get dept name of a user
	 * @param userid
	 * @return
	 */
	public String getDeptName(Integer userid);
	
	/**
	 * Update user info
	 * @param u
	 */
	public void UpdateUserInfo(User u);
}
