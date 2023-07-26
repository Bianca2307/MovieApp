package com.example.movieapp.ui.details;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.ui.genres.Genre;
import com.example.movieapp.ui.genres.GenreResponse;
import com.example.movieapp.utils.MovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsViewModel extends ViewModel {


    private MutableLiveData<MovieDetails> movieDetailsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Video>> videosLiveData = new MutableLiveData<>();


    public LiveData<MovieDetails> getMoviesDetailsLiveData(){
        return movieDetailsLiveData;
    }

    public LiveData<List<Video>> getVideosLiveData() {
        return videosLiveData;
    }


    public void loadDetails(int movieId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MovieDetails> call = movieApi.getMovieDetails(movieId,"videos");

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                if (response.isSuccessful()) {

                    MovieDetails movieDetailsResponse = response.body();

                    if(movieDetailsResponse != null){


                        VideoResponse videosResponse = movieDetailsResponse.getVideos();
                        if (videosResponse != null) {
                            if (videosResponse != null) {
                                List<Video> videos = videosResponse.getResults();
                                videosLiveData.postValue(videos);
                            }
                        }

                        movieDetailsResponse = new MovieDetails(
                                movieDetailsResponse.getTitle(),
                                movieDetailsResponse.getOverview(),
                                movieDetailsResponse.getPoster_path()
                        );
                        movieDetailsLiveData.postValue(movieDetailsResponse);
                    }
                } else {
                    //Handle API error
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
