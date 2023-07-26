package com.example.movieapp.ui.actors;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorsResponse {
    @SerializedName("results")
    private List<Actor> actors;

    public List<Actor> getActors() {
        return actors;
    }
}
