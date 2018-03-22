package com.web.sj.controller;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.sj.service.IProductService;
import com.web.sj.service.IUserService;

@Controller
public class UserController {
	
	@Autowired public IProductService productService;
	
	@Autowired public IUserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
		System.out.println("Date: " + date.format(new Date()));
		model.setViewName("homepage");
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/contactus")
	public ModelAndView showContactUsPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("contactUs", "Наши контакты");
		model.setViewName("contactUspage");
		return model;		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delivery")
	public ModelAndView showDeliveryAndPaymentPage() {
		ModelAndView model = new ModelAndView();		
		model.setViewName("deliveryANDpaymentpage");
		return model;
	}

}
