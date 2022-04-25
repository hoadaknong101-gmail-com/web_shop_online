package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.beans.ResponseObject;
import com.hoadaknong.web_shop_online.beans.StatsByMonth;
import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.repositories.OrderRepository;
import com.hoadaknong.web_shop_online.services.StatsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatsImplement implements StatsService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public JSONObject getTotalPriceSevenDayBefore() throws ParseException {
        JSONObject jsonObject = new JSONObject();

        List<Date> listDate = new ArrayList<>();
        Long oneDay = 86400000L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date nowA = dateFormat.parse(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth());
        for(int i = 0; i < 7;i++){
            listDate.add(new Date(nowA.getTime() - oneDay*i));
        }

        ArrayList<Double> sumL = new ArrayList<Double>();
        JSONArray dateArray = new JSONArray();
        for(Date i: listDate){
            dateArray.put(dateFormat.format(i).toString());
        }


        for(Date d : listDate){
            List<Order> listOrder = orderRepository.findAllByOrderDate(d);
            double sum =0;
            if(listOrder.size() > 0){
                for (Order o : listOrder){
                    sum += o.getTotalPrice();
                }
            }
            sumL.add(sum);
        }
        JSONArray valueArray = new JSONArray();
        for (Double i: sumL){
            valueArray.put(i);
        }

        jsonObject.put("date",dateArray);
        jsonObject.put("value",valueArray);
        return jsonObject;
    }

    @Override
    public StatsByMonth getStatsByDate() throws ParseException {

        JSONObject jsonObject = new JSONObject();

        List<Date> listDate = new ArrayList<>();
        Long oneDay = 86400000L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date nowA = dateFormat.parse(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth());
        for(int i = 0; i < 7;i++){
            listDate.add(new Date(nowA.getTime() - oneDay*i));
        }
        ArrayList<String> date = new ArrayList<>();
        for(Date a : listDate){
            date.add(dateFormat.format(a).toString());
        }
        ArrayList<Double> sumL = new ArrayList<Double>();

        for(Date d : listDate){
            List<Order> listOrder = orderRepository.findAllByOrderDate(d);
            double sum =0;
            if(listOrder.size() > 0){
                for (Order o : listOrder){
                    sum += o.getTotalPrice();
                }
            }
            sumL.add(sum);
        }
        StatsByMonth stats = new StatsByMonth(date, sumL);

        return stats;
    }
}
