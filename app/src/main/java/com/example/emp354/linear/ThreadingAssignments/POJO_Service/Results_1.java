package com.example.emp354.linear.ThreadingAssignments.POJO_Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.emp354.linear.ThreadingAssignments.POJO.Geometry;
import com.example.emp354.linear.ThreadingAssignments.POJO.OpeningHours;
import com.example.emp354.linear.ThreadingAssignments.POJO.Photos;

import java.util.ArrayList;
import java.util.List;

public class Results_1 implements Parcelable {

    private Geometry_1 geometry;
    private String icon;
    private String id;
    private String name;
    private OpeningHours_1 opening_hours;
    private List<Photos> photos;
    private String place_id;
    private int price_level;
    private double rating;
    private String reference;
    private String scope;
    private List<String> types;
    private String vicinity;

    public Geometry_1 getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry_1 geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours_1 getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours_1 opening_hours) {
        this.opening_hours = opening_hours;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
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
        dest.writeParcelable(this.geometry, flags);
        dest.writeString(this.icon);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.opening_hours, flags);
        dest.writeList(this.photos);
        dest.writeString(this.place_id);
        dest.writeInt(this.price_level);
        dest.writeDouble(this.rating);
        dest.writeString(this.reference);
        dest.writeString(this.scope);
        dest.writeStringList(this.types);
        dest.writeString(this.vicinity);
    }

    public Results_1() {
    }

    protected Results_1(Parcel in) {
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
        this.icon = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.opening_hours = in.readParcelable(OpeningHours.class.getClassLoader());
        this.photos = new ArrayList<Photos>();
        in.readList(this.photos, Photos.class.getClassLoader());
        this.place_id = in.readString();
        this.price_level = in.readInt();
        this.rating = in.readDouble();
        this.reference = in.readString();
        this.scope = in.readString();
        this.types = in.createStringArrayList();
        this.vicinity = in.readString();
    }

    public static final Parcelable.Creator<Results_1> CREATOR = new Parcelable.Creator<Results_1>() {
        @Override
        public Results_1 createFromParcel(Parcel source) {
            return new Results_1(source);
        }

        @Override
        public Results_1[] newArray(int size) {
            return new Results_1[size];
        }
    };
}
