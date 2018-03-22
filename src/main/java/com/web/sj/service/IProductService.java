package com.web.sj.service;

import java.util.List;

import com.web.sj.domain.Category;
import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Image;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.Type;

public interface IProductService {
	
	public List<Product> getAllProducts();
	
	public void deleteProductByProductId(Integer productId);
	
	public void insertNewProduct(Product product);
	
	public void updateProduct(Product product);
	
	public Product getProductByProductIdForUpdate(Integer productId);
	
	public List<JewelrySize> getSizesByProductId(Integer productId);
	
	public Product getDetailsOfProductByProductId(Integer productId);
	
	public List<Image> getAllImagesByProductId(Integer productId);
	
	public List<Category> getAllCategories();
	
	public List<Gemstone> getAllGemstones();
	
	public List<Gender> getAllGenders();
	
	public List<Material> getAllMaterial();
	
	public List<Type> getAllType();
	
	public List<JewelrySize> getSizesByCategory(String productCategory);
	
	public void addMainImage(Integer productId, String id, String link, String deletehash, String imageIdentificationInDB);
	
}
