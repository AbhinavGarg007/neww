package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.emp354.linear.ThreadingAssignments.POJO.Results;

import java.util.ArrayList;
import java.util.List;

public class MyObject_1 implements Parcelable {

    private List<String> html_attributions;
    private String next_page_token;
    private List<Results> results;
    private String status;

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.html_attributions);
        dest.writeString(this.next_page_token);
        dest.writeList(this.results);
        dest.writeString(this.status);
    }

    public MyObject_1() {
    }

    protected MyObject_1(Parcel in) {
        this.html_attributions = in.createStringArrayList();
        this.next_page_token = in.readString();
        this.results = new ArrayList<Results>();
        in.readList(this.results, Results.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<MyObject_1> CREATOR = new Parcelable.Creator<MyObject_1>() {
        @Override
        public MyObject_1 createFromParcel(Parcel source) {
            return new MyObject_1(source);
        }

        @Override
        public MyObject_1[] newArray(int size) {
            return new MyObject_1[size];
        }
    };
}
