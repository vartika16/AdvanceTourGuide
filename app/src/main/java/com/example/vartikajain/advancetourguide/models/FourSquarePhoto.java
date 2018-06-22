package com.example.vartikajain.advancetourguide.models;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 20-06-2018.
 */

public class FourSquarePhoto {
    @SerializedName("prefix")
    private URL prefix;
    @SerializedName("suffix")
    private URL suffix;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;

    public FourSquarePhoto(URL prefix, URL suffix, int height, int width) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.height = height;
        this.width = width;
    }

    public URL getPrefix() {
        return prefix;
    }

    public URL getSuffix() {
        return suffix;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
