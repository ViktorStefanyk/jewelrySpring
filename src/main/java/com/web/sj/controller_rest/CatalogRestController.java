package com.web.sj.controller_rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Type;
import com.web.sj.service.ICatalogService;

@Controller
@RequestMapping(value="rest/catalog")
public class CatalogRestController {
	
	@Autowired public ICatalogService catalogService;
	
	@RequestMapping(value="/products/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Product>> findProductByParameters(@PathVariable("category") String category,
			@RequestParam(value="selectedsizes", required = false) List<Integer> sizes,
			@RequestParam(value="selectedgemstones", required = false) List<Integer> gemstones,
			@RequestParam(value="selectedgenders", required = false) List<Integer> genders,
			@RequestParam(value="selectedtypes", required = false) List<Integer> types,
			@RequestParam(value="selectedmaterials", required = false) List<Integer> materials,
			@RequestParam(value="minprice", required = false) Integer minPrice,
			@RequestParam(value="maxprice", required = false) Integer maxPrice){
		List<Product> products = catalogService.findProductByParameters(category, gemstones, types, materials, minPrice, maxPrice, sizes, genders);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/quantity/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Product>> getAvailableQuantityProducts(@PathVariable("category") String category,
			@RequestParam(value="selectedsizes", required=false) List<Integer> sizes,
			@RequestParam(value="selectedgemstones", required=false) List<Integer> gemstones,
			@RequestParam(value="selectedgenders", required=false) List<Integer> genders,
			@RequestParam(value="selectedtypes", required=false) List<Integer> types,
			@RequestParam(value="selectedmaterials", required=false) List<Integer> materials){
		List<Product> products = catalogService.getAvailableQuantityProducts(category, gemstones, types, materials, null, null, sizes, genders);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="/sizes/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<JewelrySize>> getAllSizesByCategory(@PathVariable("category") String category) {
		if(!category.equals("earrings")) {
			List<JewelrySize> jewelrySizes = catalogService.getSizesByCategoryId(category);
			return new ResponseEntity<List<JewelrySize>> (jewelrySizes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/gemstones/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Gemstone>> getAllGemstonesByCategory(@PathVariable("category") String category) {
		List<Gemstone> gemstones = catalogService.getGemstonesByCategoryId(category);
		return new ResponseEntity<List<Gemstone>>(gemstones, HttpStatus.OK);
	}
	
	@RequestMapping(value="/genders/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Gender>> getAllGenderByCategory(@PathVariable("category") String category) {
		List<Gender> genders = catalogService.getGendersByCategoryId(category);
		return new ResponseEntity<List<Gender>>(genders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/types/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Type>> getAllTypeByCategory(@PathVariable("category") String category) {
		List<Type> types = catalogService.getTypesByCategoryId(category);
		return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
	}
	
	@RequestMapping(value="/materials/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Material>> getAllMaterialByCategory(@PathVariable("category") String category) {
		List<Material> materials = catalogService.getMaterialByCategoryId(category);
		return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
	}

}
