package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 20-06-2018.
 */

public class FourSquareGroup {

    @SerializedName("items")
    private ArrayList<FourSquareItem> items;

    public FourSquareGroup(ArrayList<FourSquareItem> items) {
        this.items = items;
    }

    public ArrayList<FourSquareItem> getItems() {
        return items;
    }
}
