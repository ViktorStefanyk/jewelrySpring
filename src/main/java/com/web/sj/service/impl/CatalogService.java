package com.web.sj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Type;
import com.web.sj.repository.IFilterProductRepository;
import com.web.sj.repository.IGemstoneRepository;
import com.web.sj.repository.IGenderRepository;
import com.web.sj.repository.IMaterialRepository;
import com.web.sj.repository.ISizeRepository;
import com.web.sj.repository.ITypeRepository;
import com.web.sj.service.ICatalogService;

@Service
public class CatalogService implements ICatalogService {
	
	@Autowired public IFilterProductRepository filterProductRepository;
	@Autowired public ISizeRepository sizeRepository;
	@Autowired public IGemstoneRepository gemstoneRepository;
	@Autowired public IGenderRepository genderRepository;
	@Autowired public ITypeRepository typeRepository;
	@Autowired public IMaterialRepository materialRepository;
	
	
	public List<Product> findProductByParameters(String productCategory, List<Integer> productGemstones,
			List<Integer> productType, List<Integer> productMaterial, Integer productMinPrice, Integer productMaxPrice,
			List<Integer> sizes, List<Integer> productGender) {
		return filterProductRepository.getProductsByParameter(productCategory, productGemstones, 
														   productType, productMaterial, 
														   productMinPrice, productMaxPrice, 
														   sizes, productGender);
	}
	
	public List<Product> getAvailableQuantityProducts(String productCategory, List<Integer> productGemstones,
			List<Integer> productType, List<Integer> productMaterial, Integer productMinPrice, Integer productMaxPrice,
			List<Integer> size, List<Integer> productGender) {
		return filterProductRepository.getAvailableQuantityProducts(productCategory, productGemstones, 
																	productType, productMaterial, productMinPrice, 
																	productMaxPrice, size, productGender);
	}

	public List<JewelrySize> getSizesByCategoryId(String productCategory) {
			return sizeRepository.getAvailableSizesByCategory(productCategory);		
	}

	public List<Gemstone> getGemstonesByCategoryId(String productCategory) {
		return gemstoneRepository.availableGemstoneByParameter(productCategory);
	}

	public List<Gender> getGendersByCategoryId(String productCategory) {
		return genderRepository.availableGenderByParameter(productCategory);
	}

	public List<Type> getTypesByCategoryId(String productCategory) {
		return typeRepository.availableTypeByParameter(productCategory);
	}

	public List<Material> getMaterialByCategoryId(String productCategory) {
		return materialRepository.availableMaterialByParameter(productCategory);
	}

}
