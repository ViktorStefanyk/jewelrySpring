package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Customer;
import com.web.sj.repository.ICustomerRepository;

@Repository
public class CustomerRepository implements ICustomerRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private SqlParameterSource getParamSource(Customer customer) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (customer != null) {
			paramSource.addValue("userId", customer.getUserId());
			paramSource.addValue("userFirstName", customer.getUserFirstName());
			paramSource.addValue("userLastName", customer.getUserLastName());
			paramSource.addValue("userPhoneNumber", customer.getUserPhoneNumber());
			paramSource.addValue("userEmail", customer.getUserEmail());
			paramSource.addValue("userPassword", customer.getUserPassword());
			paramSource.addValue("userConfirmPassword", customer.getUserPassword());
			paramSource.addValue("roleId", customer.getRoleId());
			paramSource.addValue("enabled", customer.getActive());
		}
		return paramSource;
	}
		
	private static final class CustomerMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setUserEmail(rs.getString("userEmail"));
			customer.setUserPassword(rs.getString("userPassword"));
			customer.setRoleName(rs.getString("roleName"));
			return customer;
		}
	}
	
	private static final class ValidateMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setUserEmail(rs.getString("userEmail"));
			return customer;
		}
	}
	
	private static final class GetCustomerMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setUserId(rs.getInt("userId"));
			customer.setUserFirstName(rs.getString("userFirstName"));
			customer.setUserLastName(rs.getString("userLastName"));
			customer.setUserPhoneNumber(rs.getString("userPhoneNumber"));
			customer.setUserEmail(rs.getString("userEmail"));
			return customer;
		}
	}

	public void registationCustomer(Customer customer) {
		String sql = "INSERT INTO users( `userFirstName`, `userLastName`, `userPhoneNumber`, `userEmail`, `userPassword`, `userConfirmPassword`, `roleId`, `enabled`) VALUES( :userFirstName, :userLastName, :userPhoneNumber, :userEmail, :userPassword,:userConfirmPassword,'2', '1')";
		System.out.println("Called registationCustomer method");
		namedParameter.update(sql, getParamSource(customer));
	}
	
	public Customer validateNewCustomer(String userEmail) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("userEmail", userEmail);
		String sql = "SELECT userEmail FROM users WHERE userEmail = :userEmail";
		List<Customer> list = namedParameter.query(sql, paramSource, new ValidateMapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	public Customer findCustomerByEmail(String userUserName) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("userUserName", userUserName);
		System.out.println("Called findCustomerByEmail method");
		String sql = "SELECT u.userEmail, u.userPassword, ur.roleName "
				+ "	  FROM users u INNER JOIN users_role ur ON u.roleId = ur.roleId "
				+ "	  WHERE u.userEmail = :userUserName OR u.userPhoneNumber = :userUserName AND u.enabled = '1'";
		return (Customer) namedParameter.queryForObject(sql, paramSource, new CustomerMapper());
	}

	public List<Customer> getCustomerFirstAndLastName(String email) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		String sql = "SELECT userId, userFirstName, userLastName, userPhoneNumber, userEmail FROM users WHERE userEmail = :email";
		List<Customer> list = namedParameter.query(sql, paramSource, new GetCustomerMapper());
		return list;
	}

	public List<Customer> getAllCustomer() {
		String sql = "SELECT userEmail FROM users ";
		List<Customer> list = namedParameter.query(sql, new ValidateMapper());
		return list;
	}

	public Customer getCustomerInfo(String email) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		
		String sql = "SELECT userId, userFirstName, userLastName, userPhoneNumber, userEmail FROM users WHERE userEmail = :email";
		Customer customer = namedParameter.queryForObject(sql, paramSource, new GetCustomerMapper());
		
		return customer;
	}

	public Customer getCustomerInfoByUserId(Integer userId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("userId", userId);
		String sql = "SELECT userId, userFirstName, userLastName, userPhoneNumber, userEmail FROM users WHERE userId = :userId";
		Customer customer = namedParameter.queryForObject(sql, paramSource, new GetCustomerMapper());
		return customer;
	}
	

}
