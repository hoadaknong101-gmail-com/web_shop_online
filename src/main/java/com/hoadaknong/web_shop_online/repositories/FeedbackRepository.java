package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Feedback;
import com.hoadaknong.web_shop_online.entities.keys.FeedbackKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}