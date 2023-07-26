package com.example.movieapp.ui.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());

        String profilePath = movie.getProfile_path();
        String imageUrl = "https://image.tmdb.org/t/p/w200" + profilePath;
        Picasso.get().load(imageUrl).into(holder.imageViewProfile);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size():0;
    }

    static class MovieViewHolder extends  RecyclerView.ViewHolder{
        TextView textViewTitle;
        ImageView imageViewProfile;

        TextView overview;
        MovieViewHolder(@NonNull View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.movie_title);
            imageViewProfile = itemView.findViewById(R.id.movie_img);
            overview = itemView.findViewById(R.id.movie_overview);
        }
    }
}
