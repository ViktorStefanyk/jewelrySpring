package com.web.sj.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.sj.domain.Category;
import com.web.sj.service.IProductService;
import com.web.sj.service.IUserService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired public IUserService userService;
	@Autowired public IProductService productService;
	
	public String getCurrentUsername() {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      return name;
	}
	
	@RequestMapping
	public String get(HttpServletRequest request) {
		return "redirect:/cart/" + request.getSession(true).getId();
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ModelAndView getCart(@PathVariable(value = "cartId") String cartId) {
		ModelAndView model = new ModelAndView();
		List<Category> categoryList = productService.getAllCategories();
		model.addObject("categories", categoryList);
		model.addObject("cartId", cartId);
		model.setViewName("cartpage");
		return model;
	}
	
	@RequestMapping(value="/checkout")
	public ModelAndView getContactData(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String cartId = request.getSession(true).getId();
		model.addObject("cartId", cartId);
		if(getCurrentUsername().equals("anonymousUser")) {
			model.setViewName("checkoutpage");
		} else {
			model.setViewName("checkoutsecondpage");
		}
		
		return model;
	}
	
	@RequestMapping(value="/checkout/stepsecond")
	public ModelAndView setDeliveryAndPaymentData(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String cartId = request.getSession(true).getId();
		model.addObject("cartId", cartId);
		
		model.setViewName("orderConfirming");
		
		return model;
	}
	
	@RequestMapping(value="/checkout/orderconfirmed")
	public ModelAndView orderConfirmed() {
		ModelAndView model = new ModelAndView();
		model.setViewName("thanksForOrder");
		
		return model;
	}

}
