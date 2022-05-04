package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Feedback;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import com.hoadaknong.web_shop_online.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/web_shop/admin")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/feedbacks")
    public String feedbackAdminPage(Model model){
        List<Feedback> listFeedback = feedbackService.findAllFeedback();
        Integer amount = listFeedback.size();

        model.addAttribute("listFeedback",listFeedback);
        model.addAttribute("amount",amount);

        return "admin_page/admin_feedback";
    }
}
