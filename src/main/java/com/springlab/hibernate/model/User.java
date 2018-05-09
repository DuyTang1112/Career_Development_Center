package com.springlab.hibernate.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.springlab.dao.HibernateDao;
import com.springlab.model.SearchQuerry;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "userid",unique=true,nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	
	private String firstname;
	private String lastname;
	private String major;
	private String address;
	private String resume;
	private String phone;
	private Integer deptid;
	private Integer roleid;
	private String email;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User() {
		
	}
	public User(String firstname, String lastname, String major, String address, String resume, String phone,
			Integer deptid, Integer roleid,String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.major = major;
		this.address = address;
		this.resume = resume;
		this.phone = phone;
		this.deptid = deptid;
		this.roleid = roleid;
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Id
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	public boolean matchCategory(SearchQuerry q) {
		boolean check=false;
		if (getRoleid()!=HibernateDao.ROLE_STUDENT) return false;
		if (q.isEmpty()) return true;
		String querry=q.getQuerry().toLowerCase();
		String major=q.getMajor().toLowerCase();
		Integer deptid=q.getDeptid();

		if (querry!=null && querry.trim().length()!=0) {
			String[] words=q.getQuerry().toLowerCase().split(" ");
			System.out.println("words "+Arrays.toString(words));
			for (String wrd:words) {
				
				String w=wrd.trim();
				if (w.isEmpty()) continue;
				if ((getFirstname()!=null && getFirstname().toLowerCase().contains(w))||
						(getLastname()!=null &&	getLastname().toLowerCase().contains(w))||
						(getAddress()!=null && getAddress().toLowerCase().contains(w))||
						(getPhone()!=null && getPhone().toLowerCase().contains(w))||
						(getEmail()!=null && getEmail().toLowerCase().contains(w))||
						(getMajor()!=null && getMajor().toLowerCase().contains(w))||
						(getDescription()!=null && getDescription().toLowerCase().contains(w))) 
				{
					check=true;
					break;
				}
				
			}
		}
		if (major!=null&&major.length()!=0) {
			check=(getMajor()!=null && getMajor().toLowerCase().contains(major));
		}
		if (deptid!=null && deptid!=0) {
			check=getDeptid()!=null && getDeptid()==deptid;
		}
		return check;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", major=" + major
				+ ", address=" + address + ", resume=" + resume + ", phone=" + phone + ", deptid=" + deptid
				+ ", roleid=" + roleid + ", email=" + email + "]\n";
	}
	
	
}
