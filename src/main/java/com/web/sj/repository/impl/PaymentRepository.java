package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Payment;
import com.web.sj.repository.IPaymentRepository;

@Repository
public class PaymentRepository implements IPaymentRepository {

	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	private static final class PaymentMapper implements RowMapper<Payment> {
		public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Payment payment = new Payment();
			payment.setPaymentId(rs.getInt("paymentId"));
			payment.setPaymentName(rs.getString("paymentName"));
			payment.setPaymentType(rs.getString("paymentType"));
			return payment;
		}
	}
	
	public List<Payment> getAllPaymentType() {
		String sql = "SELECT paymentId, paymentName, paymentType FROM `order_payment` ";
		List<Payment> list = namedParameter.query(sql, new PaymentMapper());
		return list;
	}

}
