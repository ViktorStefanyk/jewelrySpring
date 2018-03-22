package com.web.sj.repository;

import com.web.sj.domain.Order;

public interface IOrderRepository {
	
	public void saveOrderInfo(Integer orderId, Integer paymentId, String orderSettlement, String orderWarehouse, String deliveryType);
	
	public void removeQuantityOfProduct(Integer productId);
	
	public void deleteSizeOfProduct(Integer productId, String productCategory, String productSizeName);
	
	public void saveOrderOfCustomer(Order order, Integer grandTotal);

}
