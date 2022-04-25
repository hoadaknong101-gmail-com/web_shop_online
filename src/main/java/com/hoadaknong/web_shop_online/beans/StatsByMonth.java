package com.hoadaknong.web_shop_online.beans;

import java.util.ArrayList;

public class StatsByMonth {
    ArrayList<String> date;
    ArrayList<Double> value;

    public StatsByMonth(ArrayList<String> date, ArrayList<Double> value) {
        this.date = date;
        this.value = value;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<Double> getValue() {
        return value;
    }

    public void setValue(ArrayList<Double> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StatsByMonth{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
