package com.web.sj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.web.sj.service.ICartService;
import com.web.sj.service.IProductService;
import com.web.sj.service.IUserService;

@Controller
public class ProductDetailController {
	
	@Autowired public IProductService productService;
	@Autowired public IUserService userService;
	@Autowired public ICartService cartService;
		
	@RequestMapping(value="/details/{productId}", method = RequestMethod.GET)
	public ModelAndView getDetailsOfProduct(@PathVariable(value="productId") Integer productId) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("productId", productId);

		model.setViewName("detailspage");
		return model;
	}
	
}
