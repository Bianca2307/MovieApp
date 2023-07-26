package com.example.movieapp.utils;

import com.example.movieapp.ui.actors.Actor;
import com.example.movieapp.ui.actors.ActorsResponse;
import com.example.movieapp.ui.genres.GenreResponse;
import com.example.movieapp.ui.movies.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("person/popular?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&page=1")
    Call<ActorsResponse> getActors();


    @GET("genre/movie/list?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US")
    Call<GenreResponse> getGenres();



    @GET("search/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US")
    Call<MovieResponse> getMoviesByQuery(@Query("query") String query);

    @GET("discover/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US")
    Call<MovieResponse> getMoviesByPrefs(@Query("with_cast") String cast, @Query("with_genres") String genres);

}
