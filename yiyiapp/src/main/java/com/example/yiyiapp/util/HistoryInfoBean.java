package com.example.yiyiapp.util;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class HistoryInfoBean {

    public HistoryInfoBean(String result, String time, String src) {
        this.result = result;
        this.time = time;
        this.src = src;
    }

    public HistoryInfoBean(){}

    public String src;
    public String result;
    public String time;
}
