package com.example.roy.hichartdemo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2019/1/20
 */
public class DataChartCurve {
    private List<DataGraphSeriesData> data_rows;
    private List<List<DataGraphSeriesData>> periodData;
    private String title;
    private String units;
    private int y_axis;
    private String display_type;
    private String color;

    public List<DataGraphSeriesData> getDataRows() {
        return data_rows;
    }

    public void setDataRows(List<DataGraphSeriesData> data_rows) {
        this.data_rows = data_rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getYAxis() {
        return y_axis;
    }

    public void setYAxis(int y_axis) {
        this.y_axis = y_axis;
    }

    public String getDisplayType() {
        return display_type;
    }

    public void setDisplayType(String display_type) {
        this.display_type = display_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<List<DataGraphSeriesData>> getPeriodData() {
        return periodData;
    }

    public void setPeriodData(List<List<DataGraphSeriesData>> periodData) {
        this.periodData = periodData;
    }

}
