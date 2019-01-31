package com.example.roy.hichartdemo.model;

import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2018/12/27
 */
public class DataGraphViewModel {
    private String id;
    private String title;
    private String description;
    private int star;
    private String lastUpdate;
    private String frequency;
    private DataGraphChart chart;
    private boolean isDisplayed;
    private HIOptions hiChartOptions;
    private List<ArrayList<HISeries>> periodSeries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public DataGraphChart getChart() {
        return chart;
    }

    public void setChart(DataGraphChart chart) {
        this.chart = chart;
    }

    public HIOptions getHiChartOptions() {
        return hiChartOptions;
    }

    public void setHiChartOptions(HIOptions hiChartOptions) {
        this.hiChartOptions = hiChartOptions;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<ArrayList<HISeries>> getPeriodSeries() {
        return periodSeries;
    }

    public void setPeriodSeries(List<ArrayList<HISeries>> periodSeries) {
        this.periodSeries = periodSeries;
    }

}
