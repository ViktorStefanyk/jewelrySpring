package com.web.sj.controller_rest;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.sj.domain.Cart;
import com.web.sj.domain.CartItem;
import com.web.sj.domain.Customer;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Product;
import com.web.sj.service.ICartService;
import com.web.sj.service.IProductService;

@Controller
@RequestMapping(value = "/rest/cart")
public class CartRestController {
	
	@Autowired private ICartService cartService;
	@Autowired public IProductService productService;
	
	public String getCurrentUsername() {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      return name;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return cartService.create(cart);
	}
	
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		Cart cart = cartService.read(cartId);
		return cart;
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}
	
	@RequestMapping(value = "/add/{productId}",	method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable Integer productId,
						@RequestBody Float selectedSizeName,
						HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		Product product = productService.getDetailsOfProductByProductId(productId);
		if (!selectedSizeName.equals("0")) {
			JewelrySize size = new JewelrySize();
			size.setSizeName(selectedSizeName);
			product.setProductSize(size);
		}		
		cart.addCartItem(new CartItem(product));
		cartService.update(sessionId, cart);
	}
	
	
	@RequestMapping(value = "/minus/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void minusItem(@PathVariable Integer productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		Product product = productService.getDetailsOfProductByProductId(productId);
		
		CartItem cartItem = new CartItem(product);
		cart.minusCartItem(cartItem);
		
		cartService.update(sessionId, cart);
	}
	
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable Integer productId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		Product product = productService.getDetailsOfProductByProductId(productId);
		
		CartItem cartItem = new CartItem(product);
		cart.removeCartItem(cartItem);
		cartService.update(sessionId, cart);
	}
	
	@RequestMapping(value="/getcurrentuser", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCurrentUser() {
		String email = getCurrentUsername();
		if (!email.equals("anonymousUser")) {
			Customer customer = cartService.getCustomerInfo(email);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Illegal	request, please verify your payload")
	public void handleClientErrors(Exception ex) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
	public void handleServerErrors(Exception ex) {
	}

}
