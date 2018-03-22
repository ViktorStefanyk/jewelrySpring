package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Type;

public interface ITypeRepository {
	
	public List<Type> getTypes();
	
	public List<Type> availableTypeByParameter(String productCategory);

}
