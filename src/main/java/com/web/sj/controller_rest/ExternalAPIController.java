package com.web.sj.controller_rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.sj.domain.Image;
import com.web.sj.domain.Product;
import com.web.sj.service.IProductService;

@RestController
public class ExternalAPIController {
	
	public String accessToken = "Bearer 29fb5f5e93f7efa38722ac2a102e81ab8fe3f0d0";
	
	@Autowired public IProductService productService;
	
	
/*-------------------	Uploading an image to the hosting --------------------------------------------------------------------*/ 
	@RequestMapping(value="/api.imgur.com/3/image", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addImageInDB(@RequestBody @Valid Image image,
											   BindingResult result,
											   @RequestHeader(value="Authorization") String accessToken) throws IOException {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
