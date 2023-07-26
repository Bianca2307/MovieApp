package com.example.movieapp.ui.movies;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.utils.MovieApi;




import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData;

    public LiveData<List<Movie>> getMovies(){

        if(moviesLiveData == null){
            moviesLiveData = new MutableLiveData<>();
//            loadMovies();
        }

        return moviesLiveData;
    }

    public void loadMovies(String actors, String genres){
        //RETROFIT

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);


        Call<MovieResponse> call = movieApi.getMoviesByPrefs(actors, genres);


        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                             if(response.isSuccessful()){

                                 MovieResponse moviesResponse = response.body();

                                 if(moviesResponse != null){
                                     List<Movie> movies = moviesResponse.getMovies();
                                     moviesLiveData.setValue(movies);


                                 }

                             }}
                         @Override
                         public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                             // Tratează cazul în care solicitarea eșuează
                         }
                     }
        );

    }

    public void loadMoviesByQuery(String query){
        //RETROFIT

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);


        Call<MovieResponse> call = movieApi.getMoviesByQuery(query);


        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                             if(response.isSuccessful()){

                                 MovieResponse moviesResponse = response.body();

                                 if(moviesResponse != null){
                                     List<Movie> movies = moviesResponse.getMovies();
                                     moviesLiveData.setValue(movies);


                                 }

                             }}
                         @Override
                         public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                             // Tratează cazul în care solicitarea eșuează
                         }
                     }
        );

    }




}
