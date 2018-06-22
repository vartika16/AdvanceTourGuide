package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class FourSquareResponse {

    @SerializedName("venues")
    private ArrayList<FourSquareVenue> venues;
    @SerializedName("groups")
    private ArrayList<FourSquareGroup> groups;

    @SerializedName("venue")
    private FourSquareVenue venue;

    public FourSquareResponse(ArrayList<FourSquareVenue> venues, ArrayList<FourSquareGroup> groups, FourSquareVenue venue) {
        this.venues = venues;
        this.groups = groups;
        this.venue = venue;
    }

    public ArrayList<FourSquareVenue> getVenues() {
        return venues;
    }

    public ArrayList<FourSquareGroup> getGroups() {
        return groups;
    }

    public FourSquareVenue getVenue() {
        return venue;
    }
}
