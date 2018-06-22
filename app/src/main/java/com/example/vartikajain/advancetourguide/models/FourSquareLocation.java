package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class FourSquareLocation {

    @SerializedName("address")
    private String address;
    @SerializedName("crossStreet")
    private String crossStreet;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lng")
    private Double lng;

    public FourSquareLocation(String address, String crossStreet, String city, String state, String country, Double lat, Double lng) {
        this.address = address;
        this.crossStreet=crossStreet;
        this.city = city;
        this.state = state;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
