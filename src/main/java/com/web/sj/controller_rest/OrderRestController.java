package com.web.sj.controller_rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.sj.domain.DeliveryLocation;
import com.web.sj.domain.Order;
import com.web.sj.domain.Payment;
import com.web.sj.service.ICartService;
import com.web.sj.service.IMailService;

@Controller
@RequestMapping(value="/rest/order")
public class OrderRestController {
	
	@Autowired private ICartService cartService;
	@Autowired public IMailService mailService;
	
	public String getCurrentUsername() {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      return name;
	}
	
	@RequestMapping(value="/settlement", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DeliveryLocation>> getSettlement(@RequestParam(value="settlement", required=false) String settlement) {
		List<DeliveryLocation> cities = cartService.findSettlement(settlement);
		return new ResponseEntity<List<DeliveryLocation>>(cities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/all/settlements", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DeliveryLocation>> getAllSettlements() {
		List<DeliveryLocation> cities = cartService.getAllSettlements();
		return new ResponseEntity<List<DeliveryLocation>>(cities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/payments", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Payment>> getAllPaymentsType() {
		List<Payment> payments = cartService.getAllPaymentType();
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}
	
	@RequestMapping(value="/order", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addOrderConfirmed(@RequestBody @Valid Order order, 
													BindingResult result, 
													Model model,
													HttpServletRequest request) throws IOException {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		} else {
			if(order.isOtherRecipient() == true) {
				if(order.recipientFirstName != null && order.recipientLastName != null && order.recipientPatronymic != null && order.payment != null && order.orderSettlement != null) {
					String email = getCurrentUsername();
					order.userId = cartService.getCustomerInfo(email).getUserId();
					cartService.saveOrder(order);
					try {
						mailService.sendEmail(order);
					} catch (MailException ex) {
						return new ResponseEntity<>(HttpStatus.CONFLICT);
					}
					return new ResponseEntity<Object>(order, HttpStatus.OK);
				} else {
					System.out.println("Пользователя не нашли и зашли сюда 1");
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				String email = getCurrentUsername();
				order.userId = cartService.getCustomerInfo(email).getUserId();
				if(order.userId != null && order.payment != null && order.orderSettlement != null) {
					cartService.saveOrder(order);
					try {
						mailService.sendEmail(order);
					} catch (MailException ex) {
						ex.getStackTrace();
						return new ResponseEntity<>(HttpStatus.CONFLICT);
					}
					return new ResponseEntity<Object>(order, HttpStatus.OK);
				} else {
					System.out.println("Пользователя не нашли и зашли сюда 2");
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
		}	
	}

}
