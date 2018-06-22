package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 21-06-2018.
 */

public class FourSquareContact {
    @SerializedName("twitter")
    private String twitter;
    @SerializedName("instagram")
    private String instagram;
    @SerializedName("facebookUsername")
    private String facebookUsername;

    public FourSquareContact(String twitter, String instagram, String facebookUsername) {
        this.twitter = twitter;
        this.instagram = instagram;
        this.facebookUsername = facebookUsername;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }
}
