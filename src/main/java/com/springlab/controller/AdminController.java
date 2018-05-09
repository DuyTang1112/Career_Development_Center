package com.springlab.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.dao.AdminService;
import com.springlab.dao.Service;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.Post;
import com.springlab.hibernate.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	AdminService adm=Service.getInstance();
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView adminPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;
	}
	
	@RequestMapping(value = "/approveUser", method = RequestMethod.GET)
	public ModelAndView approveUser(@RequestParam(value = "id", required = false) Integer id) {
		if (id!=null) {
			
		}
		List<Login> loginList=adm.getLogin();
		/*
		for (Iterator<Login> iter= loginList.iterator();iter.hasNext();) {
			if (iter.next().getApproved()==true) iter.remove();
		};
		*/
		ModelAndView model = new ModelAndView();
	  
		model.setViewName("approveUser");
		model.addObject("loginList",loginList);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/approveAjax", method=RequestMethod.GET)
	public String approveUserAjax(@RequestParam(value = "id", required = true) Integer id) {
		System.out.println(id);
		adm.approveUser(id);
		return "User id #"+id+" is approved";
	}
	
	@ResponseBody
	@RequestMapping(value="/denyAjax", method=RequestMethod.GET)
	public String denyUserAjax(@RequestParam(value = "id", required = true) Integer id) {
		//System.out.println(id);
		adm.deleteUser(id);
		return "User id #"+id+" is denied";
	}
	
	@RequestMapping(value = "/approvePost", method = RequestMethod.GET)
	public ModelAndView approvePost() {
		List<Post> postList=adm.getAllPosts();
		//----remove all approved post
		List<String> names=new ArrayList<>();
		for (Iterator<Post> iter= postList.iterator();iter.hasNext();) {
			Post p=iter.next();
			//if (p.getApproved()==true) iter.remove();
			//else {
				User u=adm.getUser(p.getUserid());
				names.add(u.getFirstname()+" "+u.getLastname());
			//}
		};
		ModelAndView model = new ModelAndView();
	  
		model.setViewName("approvePost");
		model.addObject("postList",postList);
		model.addObject("names",names);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/approvePostAjax", method=RequestMethod.GET)
	public String approvePostAjax(@RequestParam(value = "id", required = true) Integer id) {
		System.out.println(id);
		adm.approvePost(id);
		return "Post #"+id+" is approved";
	}
	
	@ResponseBody
	@RequestMapping(value="/denyPostAjax", method=RequestMethod.GET)
	public String denyPostAjax(@RequestParam(value = "id", required = true) Integer id) {
		//System.out.println(id);
		adm.deletePost(id);
		return "Post #"+id+" is denied";
	}
}
