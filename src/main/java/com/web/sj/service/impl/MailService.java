package com.web.sj.service.impl;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.web.sj.domain.Order;
import com.web.sj.repository.ICartRepository;
import com.web.sj.repository.ICustomerRepository;
import com.web.sj.service.IMailService;


@Service("mailService")
public class MailService implements IMailService {
	
	@Autowired private JavaMailSender mailSender;
	@Autowired public ICustomerRepository customerRepository;
	@Autowired private VelocityEngine velocityEngine;
	@Autowired private ICartRepository cartRepository;


	public void sendEmail(Object object) {
		Order order = (Order) object;
		MimeMessagePreparator preparator = getMessagePreparator(order);
        mailSender.send(preparator);		
	}
	
	private MimeMessagePreparator getMessagePreparator(final Order order) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				order.customer = customerRepository.getCustomerInfoByUserId(order.getUserId());
				order.cart = cartRepository.read(order.getCartId());
				order.cartItems = order.cart.getCartItems().values();
								
				message.setTo(order.customer.getUserEmail());
				message.setBcc("storesalejewelry@gmail.com");
				message.setFrom("storesalejewelry@gmail.com", "Жемчужинка");
				message.setSubject("Ваша заявка № "+ order.getOrderId() +" принята");
				message.setSentDate(new Date());
				
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("order", order);
				
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/htmlTemplate.vm", "UTF-8", model);
				message.setText(text, true);
				
//				Resource brandPath = new FileSystemResource(new File(context.getRealPath("/")+"resources\\images\\brand\\brand.png"));
//				message.addInline("brand", brandPath);
				
				//Resource mapMarkerPath = new FileSystemResource(new File(context.getRealPath("/")+"resources\\images\\markers\\mapMarker.png"));
//				message.addInline("mapMarker", new FileSystemResource(new File(context.getRealPath("/")+"resources\\images\\markers\\mapMarker.png")));
				
//				for(CartItem cartItem : order.cartItems) {
//					Resource imagePath = new FileSystemResource(new File(context.getRealPath("/") + cartItem.getProduct().getImage().getLink()));
//					message.addInline(cartItem.getProduct().getProductId().toString(), imagePath);
//				}

			}
		};
		return preparator;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	

}
