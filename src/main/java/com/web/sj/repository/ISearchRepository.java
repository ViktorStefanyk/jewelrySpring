package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Product;

public interface ISearchRepository {
	
	public List<Product> fullSearch(String searchField);

}
