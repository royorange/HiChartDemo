package com.example.roy.hichartdemo.model;

/**
 * Created by Roy on 2019/1/20
 */
public class DataGraph {
    private String id;
    private String title;
    private String description;
    private int star;
    private int frequency;
    private String last_timestamp;
    private DataGraphChart  generated_chart;

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

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getLastTimestamp() {
        return last_timestamp;
    }

    public void setLast_timestamp(String last_timestamp) {
        this.last_timestamp = last_timestamp;
    }

    public DataGraphChart getGeneratedChart() {
        return generated_chart;
    }

    public void setGenerated_chart(DataGraphChart generated_chart) {
        this.generated_chart = generated_chart;
    }
}
