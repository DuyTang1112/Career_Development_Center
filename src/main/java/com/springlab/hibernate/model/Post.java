package com.springlab.hibernate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Post")
public class Post implements Comparable<Post>{
	private Integer postid;
	private Integer userid;
	private String header;
	private String contents;
	private String images;
	private Date time;
	private Boolean approved;
	
	public Post() {
		super();
	}
	public Post(Integer userid, String header, String content, String images, Date time, Boolean approved) {
		super();
		this.userid = userid;
		this.header = header;
		this.contents = content;
		this.images = images;
		this.time = time;
		this.approved = approved;
	}
	@Id
	public Integer getPostid() {
		return postid;
	}
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	@Override
	public int compareTo(Post o) {
		// TODO Auto-generated method stub
		return time.compareTo(o.time);
	}
	@Override
	public String toString() {
		return "Post [postid=" + postid + ", userid=" + userid + ", header=" + header + ", content=" + contents
				+ ", images=" + images + ", time=" + time + ", approved=" + approved + "]\n";
	}
	
}
