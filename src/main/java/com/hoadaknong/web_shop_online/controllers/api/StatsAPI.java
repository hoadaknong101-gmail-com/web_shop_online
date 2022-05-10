package com.hoadaknong.web_shop_online.controllers.api;

import com.hoadaknong.web_shop_online.beans.ResponseObject;
import com.hoadaknong.web_shop_online.beans.StatisticOrder;
import com.hoadaknong.web_shop_online.beans.StatsByMonth;
import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.services.OrderService;
import com.hoadaknong.web_shop_online.services.StatsService;
import org.aspectj.weaver.ast.Or;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class StatsAPI {

    @Autowired
    OrderService orderService;

    @Autowired
    StatsService statsService;


    @RequestMapping(value = "/api/web_shop/admin/revenue", method = RequestMethod.GET)
    public StatsByMonth revenueJSON() throws ParseException {
        StatsByMonth a = statsService.getStatsByDate();
        return a;
    }

    @RequestMapping(value ="/api/web_shop/admin/number_of_status_order", method = RequestMethod.GET)
    public StatisticOrder analyticByOrderStatus(){
        StatisticOrder statisticOrder = statsService.getNumberOrderStatus();
        return statisticOrder;
    }

}
