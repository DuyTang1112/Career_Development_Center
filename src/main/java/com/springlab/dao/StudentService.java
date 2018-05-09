package com.springlab.dao;

import com.springlab.hibernate.model.User;

public interface StudentService {
	
	/**
	 * Get information of a student
	 * @param id
	 * @return User object contains info about the student, null if student does not exist
	 */
	public User getStudentInfo(Integer id);
	
	/**
	 * Update resume info for an user
	 * @param userid - the user who is updating
	 * @param resume - file name
	 */
	public void uploadResume(Integer userid, String resume);
	
	/**
	 * Get dept name of a student
	 * @param userid
	 * @return
	 */
	public String getDeptName(Integer userid);
	
	/**
	 * Update student info
	 * @param u
	 */
	public void UpdateStudentInfo(User u);
}
