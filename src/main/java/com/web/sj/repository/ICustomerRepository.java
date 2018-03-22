package com.web.sj.repository;

import java.util.List;

import com.web.sj.domain.Customer;

public interface ICustomerRepository {
	
	public void registationCustomer(Customer customer);
	
	public Customer validateNewCustomer(String userEmail);
	
	public Customer findCustomerByEmail(String userUserName);
		
	public List<Customer> getCustomerFirstAndLastName(String email);
	
	public List<Customer> getAllCustomer();
	
	public Customer getCustomerInfo(String email);
	
	public Customer getCustomerInfoByUserId(Integer userId);
	
}
