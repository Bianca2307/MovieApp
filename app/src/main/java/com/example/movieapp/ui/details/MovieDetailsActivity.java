package com.example.movieapp.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieDetailsViewModel movieDetailsViewModel;
   private ImageView imageViewPoster;
   private TextView textViewTitle;
   private TextView textViewOverview;

   private WebView webViewVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_details);

       //imageViewPoster = findViewById(R.id.imageViewPoster);
       textViewTitle = findViewById(R.id.textViewTitle);
       textViewOverview = findViewById(R.id.textViewOverview);
       webViewVideo = findViewById(R.id.webViewVideo);

        // Enable JavaScript in WebView
        webViewVideo.getSettings().setJavaScriptEnabled(true);

        // Set WebViewClient to handle URL loading
        webViewVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://www.youtube.com/")) {
                    // Allow YouTube URLs to load within the WebView
                    return false;
                } else {
                    // Load other URLs in the default way
                    view.loadUrl(url);
                    return true;
                }
            }
        });


        // Get the movie ID from the Intent
        int movieId = getIntent().getIntExtra("movie_id", -1);

        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        movieDetailsViewModel.getMoviesDetailsLiveData().observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {

                if(movieDetails != null){
                    displayMovieDetails(movieDetails);
                }
            }
        });

        movieDetailsViewModel.getVideosLiveData().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                if (videos != null && !videos.isEmpty()) {
                    // Process the videos and display them on the screen
                    Video video = videos.get(0); // For example, get the first video
                    String videoUrl = "https://www.youtube.com/watch?v=" + video.getKey();
                    webViewVideo.loadUrl(videoUrl);

                }
            }
        });

        //Load movie details
        movieDetailsViewModel.loadDetails(movieId);


    }

    private void displayMovieDetails(MovieDetails movieDetails) {

//        String profilePath = movieDetails.getPoster_path();
//        String imageUrl = "https://image.tmdb.org/t/p/w200" + profilePath;
//        Picasso.get().load(imageUrl).into(imageViewPoster);


        textViewTitle.setText(movieDetails.getTitle());
        textViewOverview.setText(movieDetails.getOverview());


    }

}
