package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.entities.Feedback;
import com.hoadaknong.web_shop_online.repositories.FeedbackRepository;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackImplement implements FeedbackService {

    @Autowired
    private FeedbackRepository rp;

    @Override
    public void saveFeedback(Feedback feedback) {
        rp.save(feedback);
    }

    @Override
    public void deleteFeedbackById(Integer id) {
        rp.deleteById(id);
    }
}
