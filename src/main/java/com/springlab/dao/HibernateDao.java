package com.springlab.dao;


import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.hibernate.model.Department;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.Role;
import com.springlab.hibernate.model.User;

/**
 * Singleton class that deals with accessing database
 * 
 */
@Repository
public class HibernateDao implements Hibernate{
	public static final int DEPT_CPEG=1,DEPT_ENG=2,DEPT_MATH=3,DEPT_CPSC=4;
	public static final int ROLE_ADMIN=1, ROLE_FACLUTY=2,ROLE_OFFICER=3,ROLE_STUDENT=4;
	private  SessionFactory sessionFactory = null;  
	private  ServiceRegistry serviceRegistry = null;  
	
	public HibernateDao() {
		System.out.println("Hibernate DAO constructor");
	}
	public HibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		//configureSessionFactory();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Deprecated
	private SessionFactory configureSessionFactory() throws HibernateException {  
	    Configuration configuration = new Configuration();  
	    configuration.configure();  
	    
	    Properties properties = configuration.getProperties();
	    
		serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();          
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
	    
	    return sessionFactory;  
	}
	
	/**
	 * Get the list of all departments
	 * @return list of all departments
	 */
	public List<Department> getDepartment(){
		Session session = null;
		List<Department> list=null;
		try {
			session = sessionFactory.openSession();
			list = session.createQuery("from Department").list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	/**
	 * Get the list of all users
	 * @return list of all users
	 */
	public List<User> getUsers(){
		Session session = null;
		List<User> list=null;
		try {
			session = sessionFactory.openSession();
			//list = session.createQuery("from User").list();
			list = session.createCriteria(User.class).list();

			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	@Override
	public User getUser(Integer id) {
		User user=null;
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			user=(User)session.get(User.class, id);
			session.flush();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
		return user;
	}
	/**
	 * Get the list of login infos
	 * @return list of login infos
	 */
	public List<Login> getLoginInfo(){
		Session session = null;
		List<Login> list=null;
		try {
			session = sessionFactory.openSession();
			
			list = session.createQuery("from Login").list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	@Override
	public Login getLogin(Integer userid) {
		Login user=null;
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			user=(Login)session.get(Login.class, userid);
			session.flush();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
		return user;
	}
	/**
	 * Get the list of posts
	 * @return list of posts
	 */
	public List<Post> getPost(){
		Session session = null;
		List<Post> list=null;
		try {
			session = sessionFactory.openSession();
			
			list = session.createQuery("from Post").list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	@Override
	public Post getOnePost(Integer postid) {
		Post post=null;
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			post=(Post)session.get(Post.class, postid);
			session.flush();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
		return post;
	}
	/**
	 * Get the list of Roles
	 * @return list of roles
	 */
	public List<Role> getRoles(){
		Session session = null;
		List<Role> list=null;
		try {
			session = sessionFactory.openSession();
			list = session.createQuery("from Role").list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	@Override
	public Role getOneRole(Integer roleid) {
		Role role=null;
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			role=(Role)session.get(Role.class, roleid);
			session.flush();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
		return role;
	}
	/**
	 * next user id to insert
	 * @return
	 */
	public Integer getNextUserID() {
		Integer max=-1;
		List<User> userlist=getUsers();
		for (User u:userlist) {
			if (u.getUserid()>max) {
				max=u.getUserid();
			}
		}
		return max+1;
	}
	/**
	 * next post id to insert
	 * @return
	 */
	public Integer getNextPostID() {
		Integer max=-1;
		List<Post> list=getPost();
		for (Post u:list) {
			if (u.getPostid()>max) {
				max=u.getPostid();
			}
		}
		return max+1;
	}
	/**
	 * Add new user or update existing user
	 * @param User user, boolean update
	 * @return id of user added or updated
	 */
	
	public Integer addUser(User user,boolean update) {
		Session session = null;
		Transaction tx=null;
		Integer id=null;
		try {
			//if adding new user
			if (!update) {
				int nextid=getNextUserID();
				user.setUserid(nextid);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Saving to the database
			if (update) session.update(user);
			else id=(Integer)session.save(user);
			// Committing the change in the database.
			session.flush();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return id;
	}
	/**
	 * Add new user or update existing post
	 * @param post, update 
	 * @return id of post added or updated
	 */
	
	public Integer addPost(Post post,boolean update) {
		Session session = null;
		Transaction tx=null;
		Integer id=null;
		try {
			if (!update) {
				int nextid=getNextPostID();
				post.setPostid(nextid);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Saving to the database
			if (update) session.update(post);
			else id=(Integer)session.save(post);
			// Committing the change in the database.
			session.flush();
			
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return id;
	}
	
	@Override
	
	public Integer addLogin(Login login, boolean update) {
		Session session = null;
		Transaction tx=null;
		Integer id=null;
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Saving to the database
			if (update) session.update(login);
			else id=(Integer)session.save(login);
			// Committing the change in the database.
			session.flush();
			
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		return id;
	}
	@Override
	
	public void deleteUser(Integer userid) {
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			User user=(User)session.get(User.class, userid);
			session.delete(user);
			// Committing the change in the database.
			session.flush();
			
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
	}
	
	@Override
	public void deleteLogin(Integer userid) {
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Login user=(Login)session.get(Login.class, userid);
			session.delete(user);
			// Committing the change in the database.
			session.flush();
			
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}	
		
	}
	@Override
	public void deletePost(Integer postid) {
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Post post=(Post)session.get(Post.class, postid);
			session.delete(post);
			// Committing the change in the database.
			session.flush();
			
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			
		} finally{
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	
	/**
	 * Main method for testing purpose
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
