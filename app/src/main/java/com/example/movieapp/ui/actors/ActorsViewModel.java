package com.example.movieapp.ui.actors;

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

public class ActorsViewModel extends ViewModel {

    private MutableLiveData<List<Actor>> actorsLiveData;

    public LiveData<List<Actor>> getActors(){
        if(actorsLiveData == null){
            actorsLiveData = new MutableLiveData<>();
            loadActors();
        }
        return actorsLiveData;
    }

    private void loadActors(){
        //RETROFIT

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<ActorsResponse> call = movieApi.getActors();

        call.enqueue(new Callback<ActorsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ActorsResponse> call, @NonNull Response<ActorsResponse> response) {
                if(response.isSuccessful()){

                    ActorsResponse actorsResponse = response.body();

                    if(actorsResponse != null){
                        List<Actor> actors = actorsResponse.getActors();
                        actorsLiveData.setValue(actors);


                }

            }}
                @Override
                public void onFailure(@NonNull Call<ActorsResponse> call, @NonNull Throwable t) {
                    // Tratează cazul în care solicitarea eșuează
                }
            }
        );


    }
    }