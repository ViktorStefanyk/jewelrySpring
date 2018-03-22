package com.web.sj.domain;

import java.io.Serializable;

public class CartItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Product product;
	private int quantity;
	private Integer totalPrice;
	
	public CartItem() {
		this.quantity = 1;
	}
	
	public CartItem(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.totalPrice = product.getProductPrice();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.updateTotalPrice();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() {
		totalPrice = this.product.getProductPrice() * this.quantity;
	}
	
}
