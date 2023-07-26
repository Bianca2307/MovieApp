package com.example.movieapp.ui.actors;

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
import com.example.movieapp.ui.genres.Genre;
import com.example.movieapp.ui.genres.GenresActivity;
import com.example.movieapp.ui.on_boarding.OnBoardingActivity;
import com.example.movieapp.utils.Credentials;
import com.example.movieapp.utils.MovieApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActorsActivity extends AppCompatActivity implements ActorsAdapter.ActorsSelectionListener {

    private RecyclerView recyclerView;
    private ActorsAdapter actorsAdapter;

    private ActorsViewModel actorsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actors);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        actorsAdapter = new ActorsAdapter();
        recyclerView.setAdapter(actorsAdapter);

        ActorsAdapter.ActorsSelectionListener listener = this;
        actorsAdapter.setGenreSelectionListener(listener);





        Button saveButton = findViewById(R.id.saveButton);

        // Set a click listener on the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the onGenresSelected function to handle saving selected genres
                onActorsSelected(actorsAdapter.selectedActors);

                // Back to OnBoardingActivity
                Intent intent = new Intent(ActorsActivity.this, OnBoardingActivity.class);
                startActivity(intent);

                // Finish the current activity (optional)
                finish();
            }
        });
        actorsViewModel = new ViewModelProvider(this).get(ActorsViewModel.class);
        actorsViewModel.getActors().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actors) {

                if(actors != null){
                    actorsAdapter.setActors(actors);
                }
            }
        });



    }

    public void onActorsSelected(List<Actor> selectedActors) {
        saveSelectedActorsToSharedPreferences(selectedActors);
        showSelectedActorsSavedToast();
        reloadActors();
    }

    private void saveSelectedActorsToSharedPreferences(List<Actor> selectedActors) {
        // Save the list of selected actors using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert the selected actors to JSON string
        Gson gson = new Gson();
        String selectedActorsJson = gson.toJson(selectedActors);

        // Save the JSON string in SharedPreferences
        editor.putString("selectedActors", selectedActorsJson);
        editor.apply();
    }

    private void showSelectedActorsSavedToast() {
        // Show a toast to indicate that the selected actors are saved
        Toast.makeText(this, "Selected actors saved", Toast.LENGTH_SHORT).show();
    }

    private void reloadActors() {
        // Reload the actors (you can replace this with the appropriate logic to refresh the actors)
        getActors();
    }


    private void getActors() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);

        if (selectedActorsJson != null) {
            Gson gson = new Gson();
            List<Actor> savedSelectedActors = gson.fromJson(selectedActorsJson, new TypeToken<List<Actor>>() {}.getType());


            if (savedSelectedActors != null && !savedSelectedActors.isEmpty()) {
                for (Actor actor : savedSelectedActors) {
                    System.out.println("Actor ID: " + actor.getId());
                    System.out.println("Actor Name: " + actor.getName());

                }
            } else {
                System.out.println("No saved genres found.");
            }
        }
    }


}
