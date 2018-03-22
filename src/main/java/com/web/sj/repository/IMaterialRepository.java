package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Material;

public interface IMaterialRepository {
	
	public List<Material> getMaterial();
	
	public List<Material> availableMaterialByParameter(String productCategory);

}
