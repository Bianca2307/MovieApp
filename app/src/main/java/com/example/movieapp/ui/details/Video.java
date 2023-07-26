package com.example.movieapp.ui.details;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    public Video(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
