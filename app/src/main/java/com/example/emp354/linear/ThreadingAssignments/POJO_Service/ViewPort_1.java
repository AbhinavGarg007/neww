package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.emp354.linear.ThreadingAssignments.POJO.Location;

public class ViewPort_1 implements Parcelable {
    private Location_1 northeast;
    private Location_1 southwest;

    public Location_1 getNortheast() {
        return northeast;
    }

    public void setNortheast(Location_1 northeast) {
        this.northeast = northeast;
    }

    public Location_1 getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location_1 southwest) {
        this.southwest = southwest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.northeast, flags);
        dest.writeParcelable(this.southwest, flags);
    }

    public ViewPort_1() {
    }

    protected ViewPort_1(Parcel in) {
        this.northeast = in.readParcelable(Location.class.getClassLoader());
        this.southwest = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<ViewPort_1> CREATOR = new Parcelable.Creator<ViewPort_1>() {
        @Override
        public ViewPort_1 createFromParcel(Parcel source) {
            return new ViewPort_1(source);
        }

        @Override
        public ViewPort_1[] newArray(int size) {
            return new ViewPort_1[size];
        }
    };
}
