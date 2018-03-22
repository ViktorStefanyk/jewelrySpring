package com.web.sj.domain;

import java.util.HashMap;
import java.util.Map;


public class Cart {
	
	private String cartId;
	public Map<String, CartItem> cartItems;
	private Integer grandTotal;
	private Integer quantityListCart;
	
	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
		quantityListCart = 0;
	}
	
	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Integer getGrandTotal() {
		return grandTotal;
	}

	public Integer getQuantityListCart() {
		return quantityListCart;
	}

	public void addCartItem(CartItem item) {
		Integer productId = item.getProduct().getProductId();
		
		if (cartItems.containsKey(productId.toString())) {
			CartItem existingCartItem = cartItems.get(productId.toString());
			existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
			cartItems.put(productId.toString(), existingCartItem);
		} else {
			cartItems.put(productId.toString(), item);
		}
		
		updateGrandTotal();
		quantityListCart();
	}
	
	public void minusCartItem(CartItem item) {
		Integer productId = item.getProduct().getProductId();
		
		if(cartItems.containsKey(productId.toString())) {
			CartItem existingCartItem = cartItems.get(productId.toString());
			existingCartItem.setQuantity(existingCartItem.getQuantity() - item.getQuantity());
			cartItems.put(productId.toString(), existingCartItem);
		}
		updateGrandTotal();
		quantityListCart();
	}

	public void removeCartItem(CartItem item) {
		Integer productId = item.getProduct().getProductId();
		cartItems.remove(productId.toString());
		updateGrandTotal();
		quantityListCart();
	}

	public void updateGrandTotal() {
		grandTotal = 0;
		for (CartItem item : cartItems.values()) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
	}
	
	public void quantityListCart() {
		quantityListCart = 0;
		if (cartItems.size() == quantityListCart) {
			quantityListCart = 0;
		} else {
			quantityListCart = cartItems.size();
		}
	}

}
