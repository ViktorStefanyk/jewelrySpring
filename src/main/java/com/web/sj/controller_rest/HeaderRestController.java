package com.web.sj.controller_rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.Category;
import com.web.sj.domain.Customer;
import com.web.sj.service.IProductService;
import com.web.sj.service.IUserService;

@Controller
@RequestMapping(value="/rest/header")
public class HeaderRestController {
	
	@Autowired public IProductService productService;
	@Autowired public IUserService userService;
	
	@RequestMapping(value="/catalog", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Category>> getAllCategories() {
		
		List<Category> categories = productService.getAllCategories();
		
		if (categories.isEmpty()) {
			return new ResponseEntity<List<Category>> (HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Category>> (categories, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/loggedname", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Customer>> getFullNameCustomer() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		List<Customer> customer = userService.getCustomerFirstAndLastName(name);
		if (!customer.equals("anonymousUser")) {
			
			return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);

	}

}
