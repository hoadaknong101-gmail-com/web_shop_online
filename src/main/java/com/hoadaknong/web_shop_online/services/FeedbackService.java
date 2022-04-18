package com.hoadaknong.web_shop_online.services;


import com.hoadaknong.web_shop_online.entities.Feedback;

public interface FeedbackService {
    void saveFeedback(Feedback feedback);
    void deleteFeedbackById(Integer id);
}
