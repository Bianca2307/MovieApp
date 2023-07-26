package com.example.movieapp.ui.on_boarding;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.ui.actors.ActorsActivity;
import com.example.movieapp.ui.genres.Genre;
import com.example.movieapp.ui.genres.GenreAdapter;
import com.example.movieapp.ui.genres.GenresActivity;
import com.example.movieapp.ui.movies.MoviesActivity;
import com.example.movieapp.ui.splash_screen.SplashScreenActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private GenreAdapter genreAdapter;
    Button genresButton;
    Button actorsButton;
    Button btnNextPage;
    public static void open(Context context) {
        context.startActivity(new Intent(context, OnBoardingActivity.class));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_on_boarding);



        genresButton = findViewById(R.id.btnGenres);
        actorsButton = findViewById(R.id.btnActors);
        btnNextPage = findViewById(R.id.btnNextPage);

        // Check if there are saved genres in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);

            if (selectedGenresJson!=null) {
                Log.d("OnBoardingActivity", "Genres are not empty");
                genresButton.setVisibility(View.GONE);

            } else {
                genresButton.setVisibility(View.VISIBLE); // Show the button if there are no saved genres
            }
        //}

        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);
            if(selectedActorsJson!=null){
                Log.d("OnBoardingActivity", "Actors are not empty");
                actorsButton.setVisibility(View.GONE);
            }else {
                actorsButton.setVisibility(View.VISIBLE); // Show the button if there are no saved genres
            }


        genresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, GenresActivity.class));
            }

        });
        actorsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, ActorsActivity.class));
            }
        });
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, MoviesActivity.class));
            }
        });

        if(selectedActorsJson != null && selectedGenresJson !=null){
            btnNextPage.setVisibility(View.VISIBLE);
        }
    }


        @Override
        protected void onResume () {
            super.onResume();
            Log.d("OnBoardingActivity", "onResume() called");


        }
}
