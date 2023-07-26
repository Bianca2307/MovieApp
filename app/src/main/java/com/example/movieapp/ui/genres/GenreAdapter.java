package com.example.movieapp.ui.genres;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genres = new ArrayList<>();
    public List<Genre> selectedGenres;


    private GenreSelectionListener genreSelectionListener;

    public void setGenreSelectionListener(GenreSelectionListener listener) {
        this.genreSelectionListener = listener;
    }



    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        this.selectedGenres = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.bind(genre);


        //WAY TO SELECT ITEMS
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedGenres.contains(genre)){
                    selectedGenres.remove(genre);
                }else{
                    selectedGenres.add(genre);
                }

                notifyDataSetChanged();

            }
        });

        // Change the background color of the selected item
        if (selectedGenres.contains(genre)) {
            holder.itemView.setBackgroundColor(Color.rgb(138,43,226));
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }


    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder {
        private TextView genreNameTextView;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreNameTextView = itemView.findViewById(R.id.genreNameTextView);
        }

        public void bind(Genre genre) {
            genreNameTextView.setText(genre.getName());
        }
    }

    public interface GenreSelectionListener {
        void onGenresSelected(List<Genre> selectedGenres);
    }

}



