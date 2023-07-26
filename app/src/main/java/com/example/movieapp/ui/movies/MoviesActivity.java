package com.example.movieapp.ui.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.ui.actors.Actor;
import com.example.movieapp.ui.actors.ActorsResponse;
import com.example.movieapp.ui.genres.Genre;
import com.example.movieapp.ui.genres.GenreResponse;
import com.example.movieapp.utils.MovieApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class MoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private MoviesViewModel moviesViewModel;

    private EditText searchEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movies);

        searchEditText = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);


        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.loadMovies(getActors(), getGenres());
        moviesViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies != null){
                    moviesAdapter.setMovies(movies);
                }
            }
        });

        // Set an OnEditorActionListener to capture search actions (e.g., when the user presses the "Search" button on the keyboard)
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    performSearch(searchEditText.getText().toString());

                    return true;
                }


                return false;

            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  performSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        getActors();
        getGenres();
    }

    private void performSearch(String query){
        getMoviesByQuery(query);
    }

    private void getMoviesByQuery(String query){
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        moviesViewModel.loadMoviesByQuery(query);
    }

    private List<Actor> getSavedActors(){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);

        List<Actor> savedSelectedActors = new ArrayList<>();
        if(!selectedActorsJson.isEmpty()){
            savedSelectedActors = new Gson().fromJson(selectedActorsJson, new TypeToken<List<Actor>>() {}.getType());
        }
        return savedSelectedActors;
    }

    private String getActors(){
        List<Actor> actors = getSavedActors();
        List<String> list  = new ArrayList<>();

        actors.forEach( actor -> {
           String actorId = String.valueOf(actor.getId());
                     list.add(actorId);
                }
        );
        return String.join(",", list);
    }

    private List<Genre> getSavedGenres(){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);

        List<Genre> savedSelectedGenres = new ArrayList<>();
        if(!selectedGenresJson.isEmpty()){
            savedSelectedGenres = new Gson().fromJson(selectedGenresJson, new TypeToken<List<Genre>>() {}.getType());
        }
        return savedSelectedGenres;
    }

    private String getGenres(){
        List<Genre> genres = getSavedGenres();
        List<String> list  = new ArrayList<>();

        genres.forEach( genre -> {
                    String genreId = String.valueOf(genre.getId());
                    list.add(genreId);
                }
        );

        return String.join(",", list);
    }

}
