package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Product;

public interface IProductRepository {
	
	public List<Product> getAllProducts();
	
	public void insertNewProduct(Product product);
	
	public void deleteProduct(Integer productId);
	
	public void updateProduct(Product product);
	
	public Product getDetailsOfProductByProductId(Integer productId);
	
	public Product getProductByProductIdForUpdate(Integer productId);

}
