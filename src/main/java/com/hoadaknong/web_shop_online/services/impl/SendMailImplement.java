package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailImplement implements SendMailService {

    @Autowired
    JavaMailSender sender;

    @Override
    public void sendMail(String toEmail, String subject, String body,String typeMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("qhpser@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        sender.send(message);
        System.out.println(typeMail + " was sent to " + toEmail + " successfully!");
    }
}
