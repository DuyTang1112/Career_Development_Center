package com.springlab.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.dao.AdminService;
import com.springlab.dao.AuthService;
import com.springlab.dao.CommonService;
import com.springlab.dao.Service;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.User;

/**
 * Controller for common contents and functionality
 * @author Duy Tang
 *
 */
@Controller
public class HomeController {
    private static final String UPLOAD_DIRECTORY ="images";  
	//StudentDao studentDao = new StudentDao();
	
	AdminService adminService=Service.getInstance();
	CommonService commService=Service.getInstance();
	AuthService authService=Service.getInstance();
	
	@RequestMapping(value= {"/","/home"})
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Career Development Center - Worst project ever");
		model.addObject("message", "Welcome to Career Development Center");
		model.setViewName("home");
		List<Post> listPosts=commService.getAllPosts();
		//System.out.println(listPosts);
		
		//filter approved post
		List<String> names=new ArrayList<>();
		Collections.sort(listPosts);
		for (Iterator<Post> iter= listPosts.iterator();iter.hasNext();) {
			Post p=iter.next();
			if (p.getApproved()==false) iter.remove();
			else {
				User u=adminService.getUser( p.getUserid());
				names.add(u.getFirstname()+" "+u.getLastname());
			}
		};
		model.addObject("listPosts",listPosts);
		model.addObject("names",names);
		
		//checking authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName()+" "+authService.findUserIdByName(auth.getName()));
		return model;
		

	}
	@RequestMapping(value= {"/makePost"},method=RequestMethod.GET)
	public String makePost() {
		
		return "NewPost";
	}
	
	@RequestMapping(value= {"/makePost"},method=RequestMethod.POST)
	public String makePost(@ModelAttribute("newLogin") Post post,
			@RequestParam(value="file",required=false) MultipartFile file) throws IOException {
		//find the current user id
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		System.out.println(userid+" "+post);
		
		//---------Save to database, get postid
		Integer postid=commService.makePost(userid, post);
		
		if (file!=null && file.getOriginalFilename().length()!=0) {
			
			//--------setting up the path
			String root="D:\\Schoolwork\\Spring 2018\\Java EE\\2- SpringLab - second part\\src\\main\\webapp\\WEB-INF\\resources";
			File dir = new File(root);
			if (dir.exists()) dir.mkdirs();
			String filename=file.getOriginalFilename();
			File saveFile=new File(root +File.separator+ filename);
			
			//--update image path in database
			commService.AddImagePathToPost(postid, filename);
			
			//---save to hard disk
			byte[] bytes = file.getBytes();  
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(saveFile));  
		    stream.write(bytes);  
		    stream.flush();  
		    stream.close(); 
		}
		return "redirect:home";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editProfile() {
		//-----find current userid
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		User info=adminService.getUser(userid);
		ModelAndView model=new ModelAndView();
		String deptname=adminService.getDeptName(userid);
		model.addObject("info", info);
		model.addObject("deptname", deptname);
		model.addObject("role", adminService.getRole(info.getRoleid()));
		model.setViewName("ProfileEdit");
		
		return model;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView editProfile(@ModelAttribute User user) {
		System.out.println(user);
		adminService.UpdateUserInfo(user);
		//-----find current userid
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		User info=adminService.getUser(userid);
		String deptname=adminService.getDeptName(userid);
		ModelAndView model=new ModelAndView();
		model.addObject("info", info);
		model.addObject("deptname", deptname);
		model.addObject("role", adminService.getRole(info.getRoleid()));
		model.addObject("msg", "Update successfully");
		model.setViewName("ProfileEdit");
		
		return model;
	}
	
	@RequestMapping("/chat")
	public ModelAndView chatRoom() {
		ModelAndView model=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		Integer roleid=adminService.getUser(userid).getRoleid();
		String role=adminService.getRole(roleid);
		model.addObject("role",role);
		model.setViewName("ChatRoom");
		return model;
	}
	
	/**
	 * Test controller for database access
	 * @param model
	 * @return
	 */
	@RequestMapping("/liststudents")
	public String listStudents(Model model)
	{
	
		//System.out.println(service.getLogin());
		/*
		authservice.registerNewUser(new Login("test","test"), 
				new User("test","test","CPEG",null,null,null,
						HibernateDao.DEPT_CPSC,HibernateDao.ROLE_OFFICER,"something@xyz.com"));
		*/
		List<User> students = adminService.getUsers();
		System.out.println(adminService.getLogin());
		System.out.println(students);
		model.addAttribute("students",students);
		
		return "listStudents";		
	}
	
	
}
