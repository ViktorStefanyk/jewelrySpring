package com.web.sj.controller_rest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.Customer;
import com.web.sj.service.IUserService;

@Controller
@RequestMapping(value="/rest/customer")
public class SignuRestController {
	
	@Autowired public IUserService userService;
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveNewCustomer(@Valid Customer customer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		} else {
			String email = customer.getUserEmail();
			if (userService.validateNewCustomer(email) != null) {
				return new ResponseEntity<>(Collections.singletonList("Электронный адрес уже существует!"), HttpStatus.CONFLICT);
			} else {
				userService.registationUser(customer);
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
		}
	}
	

}
