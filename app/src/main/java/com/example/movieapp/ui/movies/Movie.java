package com.example.movieapp.ui.movies;

public class Movie {

   private String title;
    private String poster_path;



    private String overview;

    public Movie(String title, String poster_path, String overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
    public String getProfile_path() {
        return poster_path;
    }
}
