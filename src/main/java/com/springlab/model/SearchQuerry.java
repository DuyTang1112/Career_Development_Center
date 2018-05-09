package com.springlab.model;

public class SearchQuerry {
	private String querry;
	private Integer deptid;
	private String major;
	public SearchQuerry() {
		
	}
	
	public SearchQuerry(String querry, Integer deptid, String major) {
		super();
		this.querry = querry;
		this.deptid = deptid;
		this.major = major;
	}

	public String getQuerry() {
		return querry;
	}
	public void setQuerry(String querry) {
		this.querry = querry;
	}
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public boolean isEmpty() {
		return (querry==null || querry.isEmpty() ) &&
				(major==null || major.isEmpty()) &&
				(deptid==null || deptid==0);
	}
	@Override
	public String toString() {
		return "SearchQuerry [querry=" + querry + ", deptid=" + deptid + ", major=" + major + "]";
	}
	
}
