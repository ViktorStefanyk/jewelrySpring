package com.web.sj.repository.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import com.web.sj.domain.Cart;
import com.web.sj.domain.Order;
import com.web.sj.repository.ICartRepository;

@Repository
public class CartRepository implements ICartRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	@Override
	public void saveConfirmedOrderItems(Integer orderId, String orderProductName, Integer orderProductPrice,
			Integer orderProductId, Float orderProductSize, Integer orderQuantityProduct, Integer orderProductTotalPrice) throws SQLException {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderId", orderId);
		paramSource.addValue("orderProductName", orderProductName);
		paramSource.addValue("orderProductPrice", orderProductPrice);
		paramSource.addValue("orderProductId", orderProductId);
		paramSource.addValue("orderProductSize", orderProductSize);
		paramSource.addValue("orderQuantityProduct", orderQuantityProduct);
		paramSource.addValue("orderProductTotalPrice", orderProductTotalPrice);
//		Date orderDate = (new java.sql.Date(new Date().getTime()), Types.TIMESTAMP);
		paramSource.addValue("SYSDATE", new java.sql.Date(new Date().getTime()), Types.TIMESTAMP);
		
		String sql = "INSERT INTO `order_items`(`orderId`, "
				+ "								`orderProductName`, "
				+ "								`orderProductPrice`, "
				+ "								`orderProductId`,"
				+ "								`orderProductSize`, "
				+ "								`orderQuantityProduct`, "
				+ "								`orderProductTotalPrice`, "
				+ "								`orderSDConfirmedOrder`) "
				+ "	  VALUES (:orderId, "
				+ "			  :orderProductName, "
				+ "			  :orderProductPrice, "
				+ "			  :orderProductId,"
				+ "			  :orderProductSize, "
				+ "			  :orderQuantityProduct, "
				+ " 		  :orderProductTotalPrice, "
				+ "			  SYSDATE())";
		namedParameter.update(sql, paramSource);
		
	}
	
	private Map<String, Cart> listOfCarts;
	
	public CartRepository() {
		listOfCarts = new HashMap<String,Cart>();
	}
	

	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format("Can not create a cart. A cart with the give id (%) aldrady exist", cart.getCartId()));
		}
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		
		if(listOfCarts.isEmpty()) {
			listOfCarts.put(cartId, new Cart());
		}
		
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Can not update cart. The cart with the give id (%) does not does not exist",cartId));
		}
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Can not delete cart. The cart with the give id (%) does not does not exist",cartId));
		}
		
		listOfCarts.remove(cartId);
	}


	

}
