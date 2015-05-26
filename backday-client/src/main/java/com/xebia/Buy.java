package com.xebia;

import java.util.Date;

public class Buy {
    private String id;
    private Integer value;
    private Date date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
