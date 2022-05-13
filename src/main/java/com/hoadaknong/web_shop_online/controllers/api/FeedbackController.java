package com.hoadaknong.web_shop_online.controllers.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Feedback;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import com.hoadaknong.web_shop_online.services.ProductService;

@RestController
public class FeedbackController {
	@Autowired
	CustomerService customerService;

	@Autowired
	FeedbackService feedbackService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/comment/{productId}/{content}")
	public String comment(@PathVariable Integer productId,
						  @PathVariable String content,
						  HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("userId");
		Feedback feedback = new Feedback();
		if (customerId != null) {
			Customer customer = customerService.getCustomerById(customerId);
			Product product = productService.findProductById(productId);
			System.out.println("Customer id : " + customer.toString());
			System.out.println("Product id : " + product.toString());
			feedback.setProductId(product);
			feedback.setContent(content);
			feedback.setRate(5);
			feedback.setModifiedDate(new Date());
			feedback.setCustomerId(customer);
			feedbackService.saveFeedback(feedback);

			return "<div class=\"comment-item\">\r\n" + "						<div class=\"comment-image\">\r\n"
					+ "							<img src=\"/img/person_icon.png\">\r\n"
					+ "						</div>\r\n" + "						<div class=\"comment-content\">\r\n"
					+ "							<div class=\"name-customer\">\r\n"
					+ "								<h4>" + customer.getFirstName() + " " + customer.getLastName()
					+ "</h4>\r\n" + "							</div>\r\n"
					+ "							<div class=\"comment-content-text\">\r\n"
					+ "								<p>" + feedback.getContent() + "</p>\r\n"
					+ "							</div>\r\n"
					+ "							<div class=\"comment-date\">\r\n"
					+ "								<span>" + feedback.getModifiedDate() + "</span>\r\n"
					+ "							</div>\r\n" + "						</div>\r\n"
					+ "					</div>";
		}
		return "0";
	}

}
