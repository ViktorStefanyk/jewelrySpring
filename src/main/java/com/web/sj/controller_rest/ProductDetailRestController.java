package com.web.sj.controller_rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.Product;
import com.web.sj.domain.Image;
import com.web.sj.domain.JewelrySize;
import com.web.sj.service.IProductService;

@Controller
@RequestMapping(value="/rest/productdetails")
public class ProductDetailRestController {
	
	@Autowired public IProductService productService;
	
	@RequestMapping(value="/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Product> getAllDetailsOfProduct(@PathVariable(value="productId") Integer productId) {
		Product product = productService.getDetailsOfProductByProductId(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/sizes/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<JewelrySize>> getAllSizeByProductId(@PathVariable(value="productId") Integer productId) {
		List<JewelrySize> jewelrySize = productService.getSizesByProductId(productId);
			if (!jewelrySize.isEmpty()) {
				return new ResponseEntity<List<JewelrySize>>(jewelrySize, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<JewelrySize>>(HttpStatus.NO_CONTENT);
			}
	}
	
	@RequestMapping(value="/images/{productId}", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getAllImagesByProductId(@PathVariable(value="productId") Integer productId) {
		List<Image> list = productService.getAllImagesByProductId(productId);
		return new ResponseEntity<List<Image>>(list, HttpStatus.OK);
	}

}
