package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Gemstone;

public interface IGemstoneRepository {
	
	public List<Gemstone> getGemstones();
	
	public List<Gemstone> availableGemstoneByParameter(String productCategory);

}
