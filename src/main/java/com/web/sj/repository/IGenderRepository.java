package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Gender;

public interface IGenderRepository {
	
	public List<Gender> getGenders();
	
	public List<Gender> availableGenderByParameter(String productCategory);

}
