package com.web.sj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.sj.domain.Customer;
import com.web.sj.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired public IUserService userService;
	
	public String getCurrentUsername() {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      return name;
	  }
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Неверное имя пользователя или пароль!");
		}
		model.setViewName("loginpage");
		return model;
	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		if(!getCurrentUsername().equals("anonymousUser")) {
			model.addObject("username", getCurrentUsername());
		}
		// Получить текущее имя пользователя.
		if (!getCurrentUsername().equals("anonymousUser")) {
			List<Customer> customer = userService.getCustomerFirstAndLastName(getCurrentUsername());
			model.addObject("fullname", customer);
		}
		model.setViewName("error403page");
		return model;

	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }	        
	    model.setViewName("redirect:/");

		return model;
	}
	
	

}
