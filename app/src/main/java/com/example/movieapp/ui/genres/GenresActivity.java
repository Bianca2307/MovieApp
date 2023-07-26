package com.example.movieapp.ui.genres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.ui.on_boarding.OnBoardingActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GenresActivity extends AppCompatActivity implements GenreAdapter.GenreSelectionListener{

    private RecyclerView recyclerView;
    private GenreAdapter genreAdapter;

    private GenreViewModel genreViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_genres);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        genreAdapter = new GenreAdapter();
        recyclerView.setAdapter(genreAdapter);


      GenreAdapter.GenreSelectionListener listener = this;
       genreAdapter.setGenreSelectionListener(listener);



        Button saveButton = findViewById(R.id.saveButton);

        // Set a click listener on the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the onGenresSelected function to handle saving selected genres
                onGenresSelected(genreAdapter.selectedGenres);

                // Back to OnBoardingActivity
                Intent intent = new Intent(GenresActivity.this, OnBoardingActivity.class);
                startActivity(intent);

                // Finish the current activity (optional)
                finish();
            }
        });

        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if(genres != null){
                    genreAdapter.setGenres(genres);
                }
            }
        });



    }

    public void onGenresSelected(List<Genre> selectedGenres) {

        saveSelectedGenresToSharedPreferences(selectedGenres);
        showSelectedGenresSavedToast();
        reloadGenres();

    }

    private void saveSelectedGenresToSharedPreferences(List<Genre> selectedGenres) {
        // Save the list of selected genres using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert the selected genres to JSON string
        Gson gson = new Gson();
        String selectedGenresJson = gson.toJson(selectedGenres);

        // Save the JSON string in SharedPreferences
        editor.putString("selectedGenres", selectedGenresJson);
        editor.apply();
    }
    private void showSelectedGenresSavedToast() {
        // Show a toast to indicate that the selected genres are saved
        Toast.makeText(this, "Selected genres saved", Toast.LENGTH_SHORT).show();
    }

    private void reloadGenres() {
        // Reload the genres (you can replace this with the appropriate logic to refresh the genres)
        getGenres();
    }
    private void getGenres() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);

        if (selectedGenresJson != null) {
            Gson gson = new Gson();
            List<Genre> savedSelectedGenres = gson.fromJson(selectedGenresJson, new TypeToken<List<Genre>>() {}.getType());


            if (savedSelectedGenres != null && !savedSelectedGenres.isEmpty()) {
                for (Genre genre : savedSelectedGenres) {
                    System.out.println("Genre ID: " + genre.getId());
                    System.out.println("Genre Name: " + genre.getName());
                }
            } else {
                System.out.println("No saved genres found.");
            }
        }
    }






}
