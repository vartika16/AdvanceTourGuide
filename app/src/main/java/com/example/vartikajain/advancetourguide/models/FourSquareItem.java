package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 20-06-2018.
 */

public class FourSquareItem {
  @SerializedName("venue")
    private FourSquareVenue venue;

    public FourSquareItem(FourSquareVenue venue) {
        this.venue = venue;
    }

    public FourSquareVenue getVenue() {
        return venue;
    }
}
