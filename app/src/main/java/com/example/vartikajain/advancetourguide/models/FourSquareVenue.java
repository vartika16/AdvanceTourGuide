package com.example.vartikajain.advancetourguide.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class FourSquareVenue{
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private FourSquareLocation location;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("contact")
    private FourSquareContact contact;
    @SerializedName("description")
    private String description;
    @SerializedName("hours")
    private FourSquareHours hours;
    @SerializedName("verified")
    private Boolean verified;
    @SerializedName("categories")
    private ArrayList<FourSquareCategory> categories;


    public FourSquareVenue(String id, String name, FourSquareLocation location,Double rating,String decsription,FourSquareHours hours) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating=rating;
        this.description=decsription;
        this.hours=hours;
    }

//    public FourSquarePhoto getBestPhoto() {
//        return bestPhoto;
//    }


    public ArrayList<FourSquareCategory> getCategories() {
        return categories;
    }


    public Boolean isVerified() {
        return verified;
    }

    public FourSquareHours getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FourSquareLocation getLocation() {
        return location;
    }

    public FourSquareContact getContact() {
        return contact;
    }

//    public FourSquarePhoto getPhotos() {
//        return photos;
//    }
}
