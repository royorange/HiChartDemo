package com.example.roy.hichartdemo.model;

/**
 * Created by Roy on 16/11/17.
 */

public class NetResponse<T> {
    private int status;
    private T data;
    private long timestamp;
    private String message;
    private int code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getBody() {
        return data;
    }

    public void setBody(T results) {
        this.data = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timestamp = timeStamp;
    }
}
