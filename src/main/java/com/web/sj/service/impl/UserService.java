package com.web.sj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.web.sj.domain.Customer;
import com.web.sj.repository.ICustomerRepository;
import com.web.sj.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired public ICustomerRepository customerRepository;

	@Autowired private PasswordEncoder passwordEncoder;

	public void registationUser(Customer customer) {
		customer.setUserPassword(passwordEncoder.encode(customer.getUserPassword()));
		customerRepository.registationCustomer(customer);
	}

	public Customer validateNewCustomer(String userEmail) {
		return customerRepository.validateNewCustomer(userEmail);
	}

	public List<Customer> getCustomerFirstAndLastName(String email) {
		return customerRepository.getCustomerFirstAndLastName(email);
	}

	public List<Customer> getAllCustomer() {
		return customerRepository.getAllCustomer();
	}
	
	
}
