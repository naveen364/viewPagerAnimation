package com.example.viewpagertest;

import android.content.Context;

public class model {

    String title;
    Integer main_back;
    Integer main_front;
    Context c;

    public model(String title, Integer main_back, Integer main_front) {
        this.title = title;
        this.main_back = main_front;
        this.main_front = main_back;
    }

    public String getTitle() {
        return title;
    }

    public void setall(String title, Integer main_back, Integer main_front){
        this.title = title;
        this.main_back = main_back;
        this.main_front = main_front;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMain_back() {
        return main_back;
    }

    public void setMain_back(Integer main_back) {
        this.main_back = main_back;
    }

    public Integer getMain_front() {
        return main_front;
    }

    public void setMain_front(Integer main_front) {
        this.main_front = main_front;
    }
}
