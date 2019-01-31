package com.example.roy.hichartdemo.model;

/**
 * Created by Roy on 2019/1/22
 */
public class DataGraphSeriesData {
    /**
     * 时间轴（横轴）数据
     */
    private float t;
    /**
     * 纵轴数据
     */
    private float v;

    public float getTime() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public float getValue() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

}
