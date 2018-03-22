package com.web.sj.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.web.sj.domain.Order;
import com.web.sj.service.IMailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@TestExecutionListeners({
	  DependencyInjectionTestExecutionListener.class,
	  DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class})
public class MainServiceTest {
	
	@Autowired public IMailService mailService;
	@Autowired public NamedParameterJdbcTemplate namedParameter;
	
	
	@Test
	public void SendingEmail() throws Exception {
		Order order = new Order();
		//order.userEmail = "viktor.stephanyk@gmail.com";
		mailService.sendEmail(order);
	}

}
