package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.emp354.linear.ThreadingAssignments.POJO.Location;
import com.example.emp354.linear.ThreadingAssignments.POJO.ViewPort;

public class Geometry_1 implements Parcelable {
    private Location_1 location;
    private ViewPort_1 viewport;

    public Location_1 getLocation() {
        return location;
    }

    public void setLocation(Location_1 location) {
        this.location = location;
    }

    public ViewPort_1 getViewport() {
        return viewport;
    }

    public void setViewport(ViewPort_1 viewport) {
        this.viewport = viewport;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location, flags);
        dest.writeParcelable(this.viewport, flags);
    }

    public Geometry_1() {
    }

    protected Geometry_1(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.viewport = in.readParcelable(ViewPort.class.getClassLoader());
    }

    public static final Parcelable.Creator<Geometry_1> CREATOR = new Parcelable.Creator<Geometry_1>() {
        @Override
        public Geometry_1 createFromParcel(Parcel source) {
            return new Geometry_1(source);
        }

        @Override
        public Geometry_1[] newArray(int size) {
            return new Geometry_1[size];
        }
    };
}
