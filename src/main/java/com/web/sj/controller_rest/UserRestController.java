package com.web.sj.controller_rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.Product;
import com.web.sj.service.IProductService;
import com.web.sj.service.IUserService;

@Controller
@RequestMapping(value= "/rest/product")
public class UserRestController {
	
	@Autowired public IProductService productService;
	@Autowired public IUserService userService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> product = productService.getAllProducts();
		if (product.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
	}	

}
