package com.example.roy.hichartdemo.model;

/**
 * Created by Roy on 2019/1/17
 */
public class DataGraphCategoryModel {
    private String id;
    private String title;
    private String description;
    private String last_timestamp;
    private String cover_uri;

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

    public String getLastTimestamp() {
        return last_timestamp;
    }

    public void setLast_timestamp(String last_timestamp) {
        this.last_timestamp = last_timestamp;
    }

    public String getCoverUri() {
        return cover_uri;
    }

    public void setCover_uri(String cover_uri) {
        this.cover_uri = cover_uri;
    }
}
