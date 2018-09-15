package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

public class Location_1 implements Parcelable {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    public Location_1() {
    }

    protected Location_1(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<Location_1> CREATOR = new Parcelable.Creator<Location_1>() {
        @Override
        public Location_1 createFromParcel(Parcel source) {
            return new Location_1(source);
        }

        @Override
        public Location_1[] newArray(int size) {
            return new Location_1[size];
        }
    };
}
