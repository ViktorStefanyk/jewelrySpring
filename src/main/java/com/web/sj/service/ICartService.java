package com.web.sj.service;

import java.util.List;

import com.web.sj.domain.Cart;
import com.web.sj.domain.Customer;
import com.web.sj.domain.DeliveryLocation;
import com.web.sj.domain.Order;
import com.web.sj.domain.Payment;

public interface ICartService {
	
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
	
	public Customer getCustomerInfo(String email);
	
	public void saveOrder(Order order);
	
	public List<Payment> getAllPaymentType();
	
	public List<DeliveryLocation> findSettlement(String settlement);
	
	public List<DeliveryLocation> getAllSettlements();
		
}
