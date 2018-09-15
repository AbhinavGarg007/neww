package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Photos_1 implements Parcelable {
    private int width;
    private int height;
    private String photo_reference;
    private List<String> html_attributions;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.photo_reference);
        dest.writeStringList(this.html_attributions);
    }

    public Photos_1() {
    }

    protected Photos_1(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.photo_reference = in.readString();
        this.html_attributions = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Photos_1> CREATOR = new Parcelable.Creator<Photos_1>() {
        @Override
        public Photos_1 createFromParcel(Parcel source) {
            return new Photos_1(source);
        }

        @Override
        public Photos_1[] newArray(int size) {
            return new Photos_1[size];
        }
    };
}
