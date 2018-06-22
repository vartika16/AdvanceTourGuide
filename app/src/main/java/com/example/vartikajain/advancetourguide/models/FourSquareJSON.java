package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class FourSquareJSON {
    @SerializedName("response")
    private FourSquareResponse response;


    public FourSquareJSON(FourSquareResponse response) {
        this.response = response;
    }

    public FourSquareResponse getResponse() {
        return response;
    }


}
