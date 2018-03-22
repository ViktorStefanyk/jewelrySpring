package com.web.sj.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sj.domain.Cart;
import com.web.sj.domain.CartItem;
import com.web.sj.domain.Customer;
import com.web.sj.domain.DeliveryLocation;
import com.web.sj.domain.Order;
import com.web.sj.domain.Payment;
import com.web.sj.repository.ICartRepository;
import com.web.sj.repository.ICustomerRepository;
import com.web.sj.repository.IDeliveryLocationRepository;
import com.web.sj.repository.IOrderRepository;
import com.web.sj.repository.IPaymentRepository;
import com.web.sj.service.ICartService;

@Service
public class CartService implements ICartService {
	
	@Autowired private ICartRepository cartRepository;
	@Autowired public ICustomerRepository customerRepository;
	@Autowired public IPaymentRepository paymentRepository;
	@Autowired public IOrderRepository orderRepository;
	@Autowired public IDeliveryLocationRepository deliveryLocationRepository;

	public Cart create(Cart cart) {
		System.out.println("Список заказанных товаров: " + cart);
		return cartRepository.create(cart);
	}

	public Cart read(String cartId) {
		return cartRepository.read(cartId);
	}

	public void update(String cartId, Cart cart) {
		cartRepository.update(cartId, cart);
		
	}

	public void delete(String cartId) {
		cartRepository.delete(cartId);
	}

	public Customer getCustomerInfo(String email) {
		return customerRepository.getCustomerInfo(email);
	}

	public void saveOrder(Order order) {
		SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
		DateTool datetime = new DateTool();
		order.setOrderedDate(datetime);
		order.cart = cartRepository.read(order.getCartId());
		orderRepository.saveOrderOfCustomer(order, order.cart.getGrandTotal());
		orderRepository.saveOrderInfo(order.getOrderId(), order.getPayment().getPaymentId(), order.getOrderSettlement(), order.getOrderWarehouse(), order.getOrderDeliveryType());

		for(CartItem cartItem : order.cart.getCartItems().values()) {
			try {
				cartRepository.saveConfirmedOrderItems(order.getOrderId(), cartItem.getProduct().getProductName(), 
														cartItem.getProduct().getProductPrice(), cartItem.getProduct().getProductId(),
														cartItem.getProduct().getProductSize().getSizeName(), cartItem.getQuantity(), cartItem.getTotalPrice());
				
//				order.setProductId(cartItem.getProduct().getProductId()); 
				//Deleting product quantity
//				orderRepository.removeQuantityOfProduct(cartItem.getProduct().getProductId());
				
				//Deleting the size of product if it will exist
//				if(!cartItem.getProduct().getProductSizeName().isEmpty()) {
//					orderRepository.deleteSizeOfProduct(cartItem.getProduct().getProductId(), cartItem.getProduct().getProductCategory(), cartItem.getProduct().getProductSizeName());
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public List<Payment> getAllPaymentType() {
		return paymentRepository.getAllPaymentType();
	}

	public List<DeliveryLocation> findSettlement(String settlement) {
		return deliveryLocationRepository.findSettlement(settlement);
	}

	public List<DeliveryLocation> getAllSettlements() {
		return deliveryLocationRepository.getAllSettlements();
	}

}
