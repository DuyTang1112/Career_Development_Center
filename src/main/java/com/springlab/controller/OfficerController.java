package com.springlab.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.dao.AdminService;
import com.springlab.dao.Service;
import com.springlab.hibernate.model.User;
import com.springlab.model.SearchQuerry;


@Controller
@RequestMapping("/recruit")
public class OfficerController {
	AdminService adminService=Service.getInstance();
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView Search() {
		ModelAndView model=new ModelAndView();
		model.setViewName("RecruitSearch");
		return model;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ModelAndView Search(@ModelAttribute SearchQuerry querry) {
		System.out.println(querry);
		//---get user list
		List<User> userlist=adminService.getUsers();
		//-----filter results
		for(Iterator<User> iter=userlist.iterator();iter.hasNext();) {
			if (!iter.next().matchCategory(querry)) iter.remove();
		}
		System.out.println(userlist);
		ModelAndView model=new ModelAndView();
		model.addObject("results", userlist);
		model.setViewName("RecruitSearch");
		return model;
	}
}
