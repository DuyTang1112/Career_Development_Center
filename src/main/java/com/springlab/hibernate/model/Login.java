package com.springlab.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Login")
public class Login {
	private Integer userid;
	private String username;
	private String password;
	private Boolean approved;
	
	public Login() {
		approved=false;
	}
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		approved=false;
	}
	@Id
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Login [userid=" + userid + ", username=" + username + ", password=" + password + ", approved="
				+ approved + "]\n";
	}
	
}
