package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OpeningHours_1 implements Parcelable {
    private boolean open_now;
    private List<String> weekday_text;

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public List<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.open_now ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.weekday_text);
    }

    public OpeningHours_1() {
    }

    protected OpeningHours_1(Parcel in) {
        this.open_now = in.readByte() != 0;
        this.weekday_text = in.createStringArrayList();
    }

    public static final Parcelable.Creator<OpeningHours_1> CREATOR = new Parcelable.Creator<OpeningHours_1>() {
        @Override
        public OpeningHours_1 createFromParcel(Parcel source) {
            return new OpeningHours_1(source);
        }

        @Override
        public OpeningHours_1[] newArray(int size) {
            return new OpeningHours_1[size];
        }
    };
}
