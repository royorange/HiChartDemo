package com.example.roy.hichartdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roy on 2019/1/22
 */
public class Axis implements Parcelable {
    private String title;
    private String color;
    private boolean opposite;
    private String units;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOpposite() {
        return opposite;
    }

    public void setOpposite(boolean opposite) {
        this.opposite = opposite;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(color);
        dest.writeInt(opposite ? 1 : 0);
        dest.writeString(units);
    }

    public static final Creator<Axis> CREATOR = new Creator<Axis>() {
        @Override
        public Axis createFromParcel(Parcel source) {
            Axis axis = new Axis();
            axis.title = source.readString();
            axis.color = source.readString();
            axis.opposite = source.readInt() == 1;
            axis.units = source.readString();
            return axis;
        }

        @Override
        public Axis[] newArray(int size) {
            return new Axis[size];
        }
    };
}
