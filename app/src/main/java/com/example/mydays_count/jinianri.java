package com.example.mydays_count;

import java.util.Date;

/**
 * Created by 杜哲凯 on 2016/9/17.
 */
public class jinianri {
    private long date;
    private String des;

    public jinianri(long date, String des) {
        this.date = date;
        this.des = des;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {

        return des;
    }



    public long getDate() {
        return date;
    }
}
