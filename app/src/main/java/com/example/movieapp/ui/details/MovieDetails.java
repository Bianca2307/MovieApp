package com.example.movieapp.ui.details;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {

    @SerializedName("title")
   private  String title;
    @SerializedName("overview")
   private  String overview;
    @SerializedName("poster_path")
   private String poster_path;

    public MovieDetails(String title, String overview, String poster_path) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
    }
    @SerializedName("videos")
    private VideoResponse videos;

    public VideoResponse  getVideos() {
        return videos;
    }
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
