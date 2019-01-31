package com.example.roy.hichartdemo.model;

import java.util.List;

public class CommonListResponse<T> {
    private List<T> list;
    private int count;
    private String anchor;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
}
