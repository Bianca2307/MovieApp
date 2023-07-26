package com.example.movieapp.utils;

import com.example.movieapp.ui.actors.ActorsResponse;
import com.example.movieapp.ui.details.MovieDetails;
import com.example.movieapp.ui.genres.GenreResponse;
import com.example.movieapp.ui.movies.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("movie/{movie_id}?api_key=d773193a88ede0c03b5da21759b8dea6&append_to_response=videos")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId,@Query("append_to_response") String appendToResponse);

}
