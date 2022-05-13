package com.hoadaknong.web_shop_online;

import com.hoadaknong.web_shop_online.entities.*;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import com.hoadaknong.web_shop_online.repositories.*;
import com.hoadaknong.web_shop_online.services.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class WebShopOnlineApplicationTests {
	@Autowired
	ProductRepository repo;

	@Autowired
	UploadFileService uploadFileService;

	@Autowired
	SendMailService mailService;

	@Autowired
	CustomerService cusService;

	@Autowired
	OrderService orderService;


	@Autowired
	OrderRepository orderRepository;

	@Autowired
	StatsService statsService;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	CustomerRepository rp;

	@Autowired
	ProductCategoryRepository categoryRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	FeedbackService feedbackService;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	void contextLoads() {
		Feedback feedback = new Feedback();
		feedback.setContent("đâsda");
		feedback.setCustomerId(cusService.getCustomerById(1));
		feedback.setProductId(repo.getById(709));
		feedback.setRate(5);
		feedback.setModifiedDate(new Date());
		feedbackService.saveFeedback(feedback);
		
	}

}
