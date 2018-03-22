package com.web.sj.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.sj.domain.Customer;
import com.web.sj.repository.ICustomerRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	public ICustomerRepository customerRepository;

	public UserDetails loadUserByUsername(String userUserName) throws UsernameNotFoundException {

		Customer customer = customerRepository.findCustomerByEmail(userUserName);

		GrantedAuthority authority = new SimpleGrantedAuthority(customer.getRoleName());
		UserDetails userDetails = (UserDetails) new User(customer.getUserEmail(), customer.getUserPassword(),
				Arrays.asList(authority));
		return userDetails;
	}

}
