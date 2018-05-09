package com.springlab.dao;

import java.util.List;

import com.springlab.hibernate.model.Post;

public interface CommonService {
	/**
	 * 
	 * @return list of all posts
	 */
	public List<Post> getAllPosts();
	

	/**
	 * Make a post
	 * @param userid
	 * @param post id of who is posting
	 * @return the postid of the new post
	 */
	public Integer makePost(Integer userid,Post post);
	
	/**
	 * Update post in the database with image path
	 * @param postid
	 * @param imgpath
	 */
	public void AddImagePathToPost(Integer postid,String imgpath);
}
