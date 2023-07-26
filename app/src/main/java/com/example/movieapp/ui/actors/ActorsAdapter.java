package com.example.movieapp.ui.actors;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.ui.genres.Genre;
import com.example.movieapp.ui.genres.GenreAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {

    private List<Actor> actors = new ArrayList<>();

    public List<Actor> selectedActors;
    private ActorsSelectionListener actorsSelectionListener;

    public void setGenreSelectionListener(ActorsAdapter.ActorsSelectionListener listener) {
        this.actorsSelectionListener = listener;
    }

    public void setActors(List<Actor> actors){
        this.actors = actors;
        this.selectedActors = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor,parent,false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = actors.get(position);
        holder.textViewName.setText(actor.getName());


        //Utilizarea bibliotecii Picasso pt incarcarea imaginii
        String profilePath = actor.getProfile_path();
        String imageUrl = "https://image.tmdb.org/t/p/w200" + profilePath;
        Picasso.get().load(imageUrl).into(holder.imageViewProfile);

//        Glide.with(imageView.getContext())
//                .load(imageUrl)
//                .into(imageView);

        //WAY TO SELECT ITEMS
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int clickedPosition = holder.getAdapterPosition();
//                selectedItem = clickedPosition;


                if(selectedActors.contains(actor)){
                    selectedActors.remove(actor);
                }else{
                    selectedActors.add(actor);
                }
                notifyDataSetChanged();



            }
        });

        // Change the background color of the selected item
        if (selectedActors.contains(actor)) {
            holder.itemView.setBackgroundColor(Color.rgb(138,43,226));
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return actors != null ? actors.size() : 0;
    }

    static class ActorViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        ImageView imageViewProfile;

        ActorViewHolder(@NonNull View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);


        }
    }
    public interface ActorsSelectionListener {
        void onActorsSelected(List<Actor> selectedActors);
    }
}
