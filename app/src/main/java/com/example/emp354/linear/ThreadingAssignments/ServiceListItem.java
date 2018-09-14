package com.example.emp354.linear.ThreadingAssignments;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceListItem implements Parcelable {



    private String name;
    private String vicinity;

    public ServiceListItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.vicinity);
    }

    protected ServiceListItem(Parcel in) {
        this.name = in.readString();
        this.vicinity = in.readString();
    }

    public static final Parcelable.Creator<ServiceListItem> CREATOR = new Parcelable.Creator<ServiceListItem>() {
        @Override
        public ServiceListItem createFromParcel(Parcel source) {
            return new ServiceListItem(source);
        }

        @Override
        public ServiceListItem[] newArray(int size) {
            return new ServiceListItem[size];
        }
    };
}
