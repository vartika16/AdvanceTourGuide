package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 22-06-2018.
 */

public class FourSquareEvent {
    @SerializedName("name")
    private String name;

    public FourSquareEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
