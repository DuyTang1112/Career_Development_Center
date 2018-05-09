package com.springlab.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.dao.AuthService;
import com.springlab.dao.Service;
import com.springlab.dao.StudentService;
import com.springlab.hibernate.model.Login;
import com.springlab.hibernate.model.User;

@Controller
@RequestMapping("/student")
public class StudentController {
	AuthService authService=Service.getInstance();
	StudentService studentService=Service.getInstance();
	@RequestMapping(value="/uploadRes",method=RequestMethod.GET)
	public ModelAndView uploadResume() {
		return new ModelAndView("uploadRes");
	}
	
	@RequestMapping(value="/uploadRes",method=RequestMethod.POST)
	public ModelAndView uploadResume(
			@RequestParam(value="file",required=false) MultipartFile file) throws IOException {
		if (file!=null && file.getOriginalFilename().length()!=0) {
			//-----find current userid
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Integer userid=authService.findUserIdByName(auth.getName());
			//---saving the file to file system
			
			String root="D:\\Schoolwork\\Spring 2018\\Java EE\\2- SpringLab - second part\\src\\main\\webapp\\WEB-INF\\resume";
			File dir = new File(root);
			if (dir.exists()) dir.mkdirs();
			String filename=file.getOriginalFilename();
			File saveFile=new File(root +File.separator+ filename);
			
			byte[] bytes = file.getBytes();  
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(saveFile));  
		    stream.write(bytes);  
		    stream.flush();  
		    stream.close();
		    
		    //-----saving to database
		    studentService.uploadResume(userid, filename);
		    
		    //returning the view
		    ModelAndView model=new ModelAndView();
		    model.addObject("msg", "Update successfully");
		    model.setViewName("uploadRes");
		    return model;
		}
		else {
			ModelAndView model=new ModelAndView();
		    model.addObject("msg", "No resume is uploaded");
		    model.setViewName("uploadRes");
		    return model;
			
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editProfile() {
		//-----find current userid
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		User info=studentService.getStudentInfo(userid);
		ModelAndView model=new ModelAndView();
		String deptname=studentService.getDeptName(userid);
		model.addObject("info", info);
		model.addObject("deptname", deptname);
		model.setViewName("ProfileEdit");
		
		return model;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView editProfile(@ModelAttribute User user) {
		System.out.println(user);
		studentService.UpdateStudentInfo(user);
		//-----find current userid
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userid=authService.findUserIdByName(auth.getName());
		User info=studentService.getStudentInfo(userid);
		String deptname=studentService.getDeptName(userid);
		ModelAndView model=new ModelAndView();
		model.addObject("info", info);
		model.addObject("deptname", deptname);
		model.addObject("msg", "Update successfully");
		model.setViewName("ProfileEdit");
		
		return model;
	}
}
