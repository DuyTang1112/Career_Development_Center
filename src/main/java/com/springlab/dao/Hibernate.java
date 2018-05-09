package com.springlab.dao;

import java.util.List;

import com.springlab.hibernate.model.Department;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.Role;
import com.springlab.hibernate.model.User;

/**
 * Interface provides capability of a data access layer to database 
 * @author Duy Tang
 *
 */
public interface Hibernate {
	/**
	 * Get the list of all departments
	 * @return list of all departments
	 */
	public List<Department> getDepartment();
	
	/**
	 * Get the list of all users
	 * @return list of all users
	 */
	public List<User> getUsers();
	
	
	/**
	 * Get info of an user
	 * @param id
	 * @return User object
	 */
	public User getUser(Integer id);
	
	/**
	 * Get the list of login infos
	 * @return list of login infos
	 */
	public List<Login> getLoginInfo();
	
	/**
	 * get login info of an user
	 * @param userid
	 * @return
	 */
	public Login getLogin(Integer userid);
	
	/**
	 * Get the list of posts
	 * @return list of posts
	 */
	public List<Post> getPost();
	
	/**
	 * Get one post
	 * @param postid
	 * @return post if exist, else null
	 */
	public Post getOnePost(Integer postid);
	
	/**
	 * Get the list of Roles
	 * @return list of roles
	 */
	public List<Role> getRoles();
	
	/**
	 * Get role object based on id
	 * @param roleid
	 * @return
	 */
	public Role getOneRole(Integer roleid);
	/**
	 * Add new user or update existing user
	 * @param user, update - true if updating, false if not
	 * @return id of user added or null if updated
	 */
	public Integer addUser(User user,boolean update);
	
	/**
	 * Add new user or update existing post
	 * @param post, update - true if updating, false if not 
	 * @return id of post added or null if updated
	 */
	public Integer addPost(Post post,boolean update);
	
	/**
	 * Add new login info or update existing one
	 * @param login, update - true if updating, false if not 
	 * @return userid added or null if updated
	 */
	public Integer addLogin(Login login,boolean update);
	
	/**
	 * Delete user from the database
	 * @param userid
	 */
	public void deleteUser(Integer userid);
	
	/**
	 * Delete login info of an user from the database
	 * @param userid
	 */
	public void deleteLogin(Integer userid);
	
	/**
	 * Delete post from the database
	 * @param postid
	 */
	public void deletePost(Integer postid);
	
	
}
