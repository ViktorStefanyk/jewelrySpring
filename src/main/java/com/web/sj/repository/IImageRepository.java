package com.web.sj.repository;


import java.util.List;

import com.web.sj.domain.Image;

public interface IImageRepository {
	
	public void addMainImage(Integer productId, String id, String link, String deletehash, String imageIdentificationInDB);
	
	public List<Image> getAllImagesByProductId(Integer productId);

}
