package com.example.roy.hichartdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2019/1/20
 */
public class DataGraphChart {
    private List<DataChartCurve> curves;
    private String title;
    private List<Axis> y_axises;
    private String stacking;

    public List<DataChartCurve> getCurves() {
        return curves;
    }

    public void setCurves(List<DataChartCurve> curves) {
        this.curves = curves;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Axis> getYAxises() {
        return y_axises;
    }

    public void setYAxises(List<Axis> y_axises) {
        this.y_axises = y_axises;
    }

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }

}
