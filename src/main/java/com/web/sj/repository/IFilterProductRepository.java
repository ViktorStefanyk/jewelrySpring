package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Product;

public interface IFilterProductRepository {
	
	public List<Product> getProductsByParameter(String productCategory, List<Integer> productGemstones, 
												List<Integer> productType, List<Integer> productMaterial,
												Integer productMinPrice, Integer productMaxPrice,
												List<Integer> size, List<Integer> productGender);
	
	public List<Product> getAvailableQuantityProducts(String productCategory, List<Integer> productGemstones, 
												List<Integer> productType, List<Integer> productMaterial,
												Integer productMinPrice, Integer productMaxPrice,
												List<Integer> size, List<Integer> productGender);
	

}
