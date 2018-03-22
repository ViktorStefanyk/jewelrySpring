package com.web.sj.service;

import java.util.List;

import com.web.sj.domain.Customer;

public interface IUserService {
	
	public void registationUser(Customer customer);
	
	public Customer validateNewCustomer(String userEmail);
	
	public List<Customer> getCustomerFirstAndLastName(String email);
	
	public List<Customer> getAllCustomer();
	
}
