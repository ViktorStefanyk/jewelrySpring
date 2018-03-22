package com.web.sj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CatalogController {
	
	@RequestMapping(value="/{category}", method = RequestMethod.GET)
	public ModelAndView filterByCategory(@PathVariable("category") String productCategory) {
		ModelAndView model = new ModelAndView();
		model.addObject("category", productCategory);
		model.setViewName("catalogpage");
		return model;
	}

}
