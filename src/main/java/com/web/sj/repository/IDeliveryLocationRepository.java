package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.DeliveryLocation;

public interface IDeliveryLocationRepository {
	
	public List<DeliveryLocation> findSettlement(String settlement);
	
	public List<DeliveryLocation> getAllSettlements();

}
