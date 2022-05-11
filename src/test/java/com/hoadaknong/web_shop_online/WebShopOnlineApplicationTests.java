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

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	void contextLoads() {
		System.out.println(authenticationService.isRightInformation("hoagigg@gmail.com","123"));
	}

	@Test
	public void addProduct(){
		OrderDetailsKey orderDetailsKey = new OrderDetailsKey();
		orderDetailsKey.setOrderId(17);
		orderDetailsKey.setProductId(709);
		orderService.deleteItem(orderDetailsKey);
	}

	@Test
	public void deleteFile(){
		uploadFileService.deleteFile("fac415beb41d4b588b0908c7f3ca9944.jpg");
	}
	@Test
	public void testMail(){
		mailService.sendMail("hoadaknong101@gmail.com",
				"Test mail",
				"Hoa dep trai",
				"Reset password");
	}
}
