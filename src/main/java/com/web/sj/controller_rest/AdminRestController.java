package com.web.sj.controller_rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.sj.domain.Category;
import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Image;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Type;
import com.web.sj.external_api.ExternalAPI;
import com.web.sj.service.IProductService;

@Controller
@RequestMapping(value="/rest/admin")
public class AdminRestController {
	
	@Autowired public IProductService productService;
	
	@RequestMapping(value="/delete/{productId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProductByProductId(@PathVariable(value = "productId") Integer productId) {
		productService.deleteProductByProductId(productId);
	}
	
	@RequestMapping(value="/add", consumes = {"multipart/form-data"},  method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addNewProductToDB(@RequestPart("product") Product product,
													@RequestPart("file") MultipartFile file,
													BindingResult result) throws IOException {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		} else {
			
			productService.insertNewProduct(product);
			Image image = ExternalAPI.sendImageToHosting(file);
			
			productService.addMainImage(product.getProductId(), image.getId(), image.getLink(), image.getDeletehash(), image.getImageIdentificationInDB());
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}	
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> updateProduct(@RequestBody @Valid Product product, 
												BindingResult result,
												Model model) throws IOException {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		} else {
			productService.updateProduct(product);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getinformation/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Product> getProductInformation(@PathVariable("productId") Integer productId) {
		Product product = productService.getProductByProductIdForUpdate(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = productService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gemstones", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Gemstone>> getAllGemstones() {
		List<Gemstone> gemstones = productService.getAllGemstones();
		return new ResponseEntity<List<Gemstone>>(gemstones, HttpStatus.OK);
	}
	
	@RequestMapping(value="/genders", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Gender>> getAllGenders() {
		List<Gender> genders = productService.getAllGenders();
		return new ResponseEntity<List<Gender>>(genders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/types", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Type>> getAllTypes() {
		List<Type> types = productService.getAllType();
		return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
	}
	
	@RequestMapping(value="/materials", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Material>> getAllMaterials() {
		List<Material> materials = productService.getAllMaterial();
		return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
	}
	
	@RequestMapping(value="/sizes", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<JewelrySize>> getAllSizesByCategory(@RequestParam String productCategory) {
		List<JewelrySize> jewelrySizes = productService.getSizesByCategory(productCategory);
		return new ResponseEntity<List<JewelrySize>>(jewelrySizes, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/selected/sizes", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<JewelrySize>> getSelectedSizesByProductId(@RequestParam Integer productId) {
		List<JewelrySize> jewelrySize = productService.getSizesByProductId(productId);
		if (!jewelrySize.isEmpty()) {
			return new ResponseEntity<List<JewelrySize>>(jewelrySize, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
	}
	
}
