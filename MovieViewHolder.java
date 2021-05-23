package com.example.lola;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    // User interface components
    private TextView txtName;
    private TextView txtDirector;
    private ImageView imagejj;
    // Movie to be used by OnClickListener
    private Movie movie;

    public MovieViewHolder(View itemView) {
        super(itemView);
        // Find the user interface components
        this.txtName = (TextView) itemView.findViewById(R.id.txtName);
        this.txtDirector = (TextView) itemView.findViewById(R.id.txtDirector);
        this.imagejj=(ImageView)itemView.findViewById(R.id.imgid);

        // Set OnClickListener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the context
                Context context = v.getContext();
                // Show the name of the movie in a Toast
                Toast.makeText(context, movie.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setMovie(Movie movie) {
        // Assign the movie to the instance variable
        this.movie = movie;
        // Set the content to the view
        this.txtName.setText(movie.getName());
        this.txtDirector.setText(movie.getDirector());
        this.imagejj.setImageResource(movie.getImgid());
    }
}