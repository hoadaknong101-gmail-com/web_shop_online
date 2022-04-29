package com.hoadaknong.web_shop_online.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/web_shop/admin/statistic")
public class StatisticController {

    @RequestMapping(value={"/",""}, method = RequestMethod.GET)
    public String statisticPage(Model model){

        return "admin_statistic";
    }
}
