package com.web.sj.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Order;
import com.web.sj.repository.IOrderRepository;

@Repository
public class OrderRepository implements IOrderRepository {
	
	private NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void saveOrderOfCustomer(Order order, Integer grandTotal) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("userId", order.getUserId());
		paramSource.addValue("recipientFirstName", order.getRecipientFirstName());
		paramSource.addValue("recipientLastName", order.getRecipientLastName());
		paramSource.addValue("recipientPatronymic", order.getRecipientPatronymic());
		paramSource.addValue("grandTotal", grandTotal);
		paramSource.addValue("orderComment", order.getOrderComment());
		
		String sql = "INSERT INTO `order_customer`(`userId`, `recipientFirstName`, `recipientLastName`, `recipientPatronymic`, `grandTotal`, `orderComment`) "
				+ "   VALUES(:userId, :recipientFirstName, :recipientLastName, :recipientPatronymic, :grandTotal, :orderComment)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameter.update(sql, paramSource, keyHolder);
		Integer orderId = keyHolder.getKey().intValue();
		order.setOrderId(orderId);
	}

	public void saveOrderInfo(Integer orderId, Integer paymentId, String orderSettlement, String orderWarehouse, String deliveryType) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderId", orderId);
		paramSource.addValue("paymentId", paymentId);
		paramSource.addValue("orderSettlement", orderSettlement);
		paramSource.addValue("orderWarehouse", orderWarehouse);
		paramSource.addValue("deliveryType", deliveryType);
		
		String sql = "INSERT INTO `order_location_payment`(`orderId`, `paymentId`, `Settlement`, `Warehouse`, `DeliveryType`) "
				+ "	  VALUES (:orderId, :paymentId, :orderSettlement, :orderWarehouse, :deliveryType)";
		
		namedParameter.update(sql, paramSource);
	}

	public void removeQuantityOfProduct(Integer productId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		
		String sql = "UPDATE product SET productQuantity=productQuantity-1 WHERE productId = :productId ";
		
		namedParameter.update(sql, paramSource);
	}

	@Override
	public void deleteSizeOfProduct(Integer productId, String productCategory, String productSizeName) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		paramSource.addValue("productCategory", productCategory);
		paramSource.addValue("productSizeName", productSizeName);
		
		String sql = null;
		
		if (productCategory.equals("Кольца")) {
			sql = "DELETE FROM product_ringsize WHERE productId = :productId and ringSizeId IN (SELECT ringSizeId FROM ringsize WHERE ringSize = :productSizeName)";
		}
		
		if (productCategory.equals("Цепочки")) {
			sql = "DELETE FROM product_chainssize WHERE productId = :productId and chainsSizeId IN (SELECT chainsSizeId FROM chainssize WHERE chainsSize = :productSizeName)";
		}
		
		if (productCategory.equals("Подвески")) {
			sql = "DELETE FROM product_pendantsize WHERE productId = :productId and pendantSizeId IN (SELECT pendantSizeId FROM pendantsize WHERE pendantSize = :productSizeName)";
		}
		
		namedParameter.update(sql, paramSource);
		
		
	}

}
