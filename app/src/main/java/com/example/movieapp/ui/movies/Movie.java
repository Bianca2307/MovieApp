package com.example.movieapp.ui.movies;

public class Movie {

   private String title;
    private String poster_path;
    private int id;

    private String overview;

    public Movie(String title, String poster_path, String overview, int id) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public int getId() {return id;}

    public String getOverview() {
        return overview;
    }
    public String getProfile_path() {
        return poster_path;
    }
}
