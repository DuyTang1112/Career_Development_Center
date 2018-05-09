package com.springlab.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {
	private Integer deptid;
	private String deptname;
	public Department(String deptname) {
		this.deptname=deptname;
	}
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	public Integer getDeptid() {
		return deptid;
	}
	
	
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", deptname=" + deptname + "]\n";
	}
	
}
