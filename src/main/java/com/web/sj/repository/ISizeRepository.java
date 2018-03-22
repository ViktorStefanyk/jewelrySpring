package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.JewelrySize;

public interface ISizeRepository {
	
	public List<JewelrySize> getAvailableSizesByCategory(String productCategory);
	
	public List<JewelrySize> getAllSizesByProductIdAndCategory(Integer productId, String productCategory);
	
	public void deleteSizesByProductId(Integer productId, String productCategory);
	
	public List<JewelrySize> getAllSizesByCategory(String productCategory);
	
	public void inserSizeByCategory(Integer productId, Integer productCategory, Integer sizeId);

}
