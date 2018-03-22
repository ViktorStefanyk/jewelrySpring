package com.web.sj.service;

import java.util.List;

import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Type;

public interface ICatalogService {
	
	public List<Product> findProductByParameters(String productCategory, List<Integer> productGemstones, 
												 List<Integer> productType, List<Integer> productMaterial,
												 Integer productMinPrice, Integer productMaxPrice,
												 List<Integer> sizes, List<Integer> productGender);
	public List<Product> getAvailableQuantityProducts(String productCategory, List<Integer> productGemstones, 
												 List<Integer> productType, List<Integer> productMaterial,
												 Integer productMinPrice, Integer productMaxPrice,
												 List<Integer> size, List<Integer> productGender);
	
	public List<JewelrySize> getSizesByCategoryId(String productCategory);
	
	public List<Gemstone> getGemstonesByCategoryId(String productCategory);
	
	public List<Gender> getGendersByCategoryId(String productCategory);
	
	public List<Type> getTypesByCategoryId(String productCategory);
	
	public List<Material> getMaterialByCategoryId(String productCategory);

}
