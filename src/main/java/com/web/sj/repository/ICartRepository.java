package com.web.sj.repository;


import java.sql.SQLException;

import com.web.sj.domain.Cart;

public interface ICartRepository {
	
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
	
	public void saveConfirmedOrderItems(Integer orderId, String orderProductName, Integer orderProductPrice, 
										Integer orderProductId, Float orderProductSize, Integer orderQuantityProduct, 
										Integer orderProductTotalPrice) throws SQLException;
	
	

}
