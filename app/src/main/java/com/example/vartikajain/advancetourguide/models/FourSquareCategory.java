package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 21-06-2018.
 */

public class FourSquareCategory {
    @SerializedName("name")
    private String name;

    public FourSquareCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
