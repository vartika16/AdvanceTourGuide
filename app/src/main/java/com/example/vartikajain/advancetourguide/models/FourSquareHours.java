package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 21-06-2018.
 */

public class FourSquareHours {
    @SerializedName("status")
    private String status;

    public FourSquareHours(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
