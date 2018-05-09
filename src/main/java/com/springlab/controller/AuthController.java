package com.springlab.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.dao.AuthService;
import com.springlab.dao.Service;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.User;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	AuthService authservice=Service.getInstance();
	
	@RequestMapping(value= {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("msg", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("Login");
		System.out.println(System.getProperty("user.dir"));
		return model;
	}
	
	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public String loginVerify(@ModelAttribute("newLogin") Login login) {
		System.out.println(login);
		return "Login";
	}
	
	@RequestMapping(value={"/register"}, method = RequestMethod.POST)
	public String loginRegister(@ModelAttribute("newLogin") Login login,
			@ModelAttribute("newUser") User user) {
		System.out.println(login);
		System.out.println(user);
		Integer id=authservice.registerNewUser(login, user);
		System.out.println("New user registered:"+id);
		return "redirect:login";
	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
	  model.setViewName("403");
	  return model;

	}
}
