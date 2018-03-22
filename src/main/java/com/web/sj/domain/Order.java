package com.web.sj.domain;

import java.util.Collection;

import org.apache.velocity.tools.generic.DateTool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Order {
	
	public Payment payment;
	public Customer customer;
	public Cart cart;
	public Collection<CartItem> cartItems;
	public String cartId;
	public String orderSettlement;
	public String orderWarehouse;
	public String orderDeliveryType;
	public String orderComment;
	public Integer orderId;
	public Integer userId;
	public Integer articleId;
	public String recipientFirstName;
	public String recipientLastName;
	public String recipientPatronymic;
	public boolean otherRecipient;
	public DateTool orderedDate;

	public Order() {
		super();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getSettlement() {
		return orderSettlement;
	}

	public void setSettlement(String orderSettlement) {
		this.orderSettlement = orderSettlement;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public String getOrderSettlement() {
		return orderSettlement;
	}

	public void setOrderSettlement(String orderSettlement) {
		this.orderSettlement = orderSettlement;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getRecipientFirstName() {
		return recipientFirstName;
	}

	public void setRecipientFirstName(String recipientFirstName) {
		this.recipientFirstName = recipientFirstName;
	}

	public String getRecipientLastName() {
		return recipientLastName;
	}

	public void setRecipientLastName(String recipientLastName) {
		this.recipientLastName = recipientLastName;
	}

	public String getRecipientPatronymic() {
		return recipientPatronymic;
	}

	public void setRecipientPatronymic(String recipientPatronymic) {
		this.recipientPatronymic = recipientPatronymic;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isOtherRecipient() {
		return otherRecipient;
	}

	public void setOtherRecipient(boolean otherRecipient) {
		this.otherRecipient = otherRecipient;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Collection<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Collection<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public DateTool getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(DateTool orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getOrderWarehouse() {
		return orderWarehouse;
	}

	public void setOrderWarehouse(String orderWarehouse) {
		this.orderWarehouse = orderWarehouse;
	}

	public String getOrderDeliveryType() {
		return orderDeliveryType;
	}

	public void setOrderDeliveryType(String orderDeliveryType) {
		this.orderDeliveryType = orderDeliveryType;
	}
	
	
}
