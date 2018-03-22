package com.web.sj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.sj.service.IUserService;

@Controller
@PreAuthorize("permitAll")
public class SignupController {
	
	@Autowired public IUserService userService;
	
	
	@RequestMapping(value="/signup", method= RequestMethod.GET)
	public ModelAndView registrationForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("signUpspage");
		return model;
	}
	
}
