package com.springlab.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springlab.hibernate.model.Department;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.User;

@org.springframework.stereotype.Service
public class Service implements AdminService, AuthService, StudentService,CommonService {
	private static Service instance=null;
	
	@Autowired
	private Hibernate HibernateDAO;
	
	private Service() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("sqlserver.xml");
		HibernateDAO = context.getBean(HibernateDao.class);
	}

	public static<T> T getInstance() {
		if (instance==null) {
			System.out.println("null instance");
			instance=new Service();
			}
		return (T)instance;
	}
	

	@Override
	public Integer checkLogin(String username, String password) {
		List<Login> loginList=HibernateDAO.getLoginInfo();
		for (Login log:loginList) {
			if (log.getUsername().equals(username) &&
					log.getPassword().equals(password) &&
					log.getApproved()==true) {
				return log.getUserid();
			}
		}
		return null;
	}
	
	@Override
	public String getDeptName(Integer userid) {
		User u=getUser(userid);
		//System.out.println(u);
		List<Department> dept=HibernateDAO.getDepartment();
		for (Department d:dept) {
			if (d.getDeptid()==u.getDeptid()) {
				return d.getDeptname();
			}
		}
		return null;
	}

	@Override
	public Integer registerNewUser(Login log, User user) {
		
		//add new user first and then add login info
		Integer userid=HibernateDAO.addUser(user,false);
		log.setUserid(userid);
		HibernateDAO.addLogin(log, false);
		return userid;
	}

	@Override
	public void approveUser(Integer userid) {
		
		Login log=HibernateDAO.getLogin(userid);
		if (log!=null) {
			log.setApproved(true);
			HibernateDAO.addLogin(log, true);
		}
	}

	@Override
	public void approvePost(Integer postid) {
		Post post=HibernateDAO.getOnePost(postid);
		if (post!=null) {
			post.setApproved(true);
			HibernateDAO.addPost(post, true);
		}
	}

	@Override
	public void deleteUser(Integer userid) {
		HibernateDAO.deleteLogin(userid);
		HibernateDAO.deleteUser(userid);
		List<Post> listposts=HibernateDAO.getPost();
		//---- delete all posts associate with this user
		for (Iterator<Post> iter=listposts.iterator();iter.hasNext();) {
			Post p=iter.next();
			if (p.getUserid().equals(userid)) {
				HibernateDAO.deletePost(p.getPostid());
			}
		}
	}

	@Override
	public void deletePost(Integer postid) {
		
		HibernateDAO.deletePost(postid);

	}
	
	@Override
	public void UpdateStudentInfo(User u) {
		HibernateDAO.addUser(u, true);
	}
	
	

	@Override
	public void UpdateUserInfo(User u) {
		HibernateDAO.addUser(u, true);
		
	}

	@Override
	public List<User> getUsers() {
		return HibernateDAO.getUsers();
	}

	@Override
	public User getUser(Integer id) {
		return HibernateDAO.getUser(id);
		
	}
	
	
	@Override
	public List<Login> getLogin() {
		
		return HibernateDAO.getLoginInfo();
	}
	
	@Override
	public Integer findUserIdByName(String name) {
		List<Login> user=getLogin();
		for (Login u:user) {
			if (u.getUsername().equals(name)) {
				return u.getUserid();
			}
		}
		return null;
	}

	@Override
	public User getStudentInfo(Integer id) {
		User u=HibernateDAO.getUser(id);
		if (u.getRoleid()!=HibernateDao.ROLE_STUDENT) return null;
		return u;
	}
	
	
	
	@Override
	public void uploadResume(Integer userid, String resume) {
		User u=HibernateDAO.getUser(userid);
		u.setResume(resume);
		HibernateDAO.addUser(u, true);
	}

	@Override
	public List<Post> getAllPosts() {
		
		return HibernateDAO.getPost();
	}
	
	

	@Override
	public String getRole(Integer roleid) {
		return HibernateDAO.getOneRole(roleid).getRolename();
	}

	@Override
	public Integer makePost(Integer userid, Post post) {
		// TODO Auto-generated method stub
		post.setUserid(userid);
		post.setTime(new Date());
		post.setApproved(false);
		return HibernateDAO.addPost(post, false);
	}
	
	
	
	@Override
	public void AddImagePathToPost(Integer postid, String imgpath) {
		Post p=HibernateDAO.getOnePost(postid);
		p.setImages(imgpath);
		HibernateDAO.addPost(p, true);
	}

	/**
	 * for testing purpose
	 * @param args
	 */
	public static void main(String[] args) {
		
		Service service=Service.getInstance();
		//service.approveUser(2);
		System.out.println(service.getDeptName(5));
	}
}
