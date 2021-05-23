package com.example.lola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {
    // RecyclerView user interface component
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private RecyclerView.Adapter<MovieViewHolder> adapter;

    // LayoutManager for the RecyclerView
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Find the UI components
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Arrange the items linearly
       // this.layoutManager = new LinearLayoutManager(this);       //vertical recycle or horizontal
        this.layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        // Get the movies
        List<Movie> movies = getMovies();

        // Create the MovieAdapter
        this.adapter = new MovieAdapter(movies);

        // Set the LayoutManager
        this.recyclerView.setLayoutManager(layoutManager);

        // Set the Adapter
        this.recyclerView.setAdapter(adapter);
    }

    /**
     * Create a List of Movies.
     */
    private List<Movie> getMovies() {
        // Create a List of movies
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Star Wars", "J.J. Abrams",R.drawable.aa));
        movies.add(new Movie("The Martian", "Ridley Scott",R.drawable.aa));
        movies.add(new Movie("Crimson Peak", "Guillermo del Toro",R.drawable.aa));
        movies.add(new Movie("Pan", "Joe Wright",R.drawable.aa));
        movies.add(new Movie("Knock Knock", "Eli Roth",R.drawable.aa));
        movies.add(new Movie("Sicario", "Denis Villeneuve",R.drawable.aa));
        movies.add(new Movie("The Walk", "Robert Zemeckis",R.drawable.aa));
        movies.add(new Movie("Black Mass", "Scott Cooper",R.drawable.aa));
        movies.add(new Movie("Goosebumps", "Rob Letterman",R.drawable.aa));
        movies.add(new Movie("Dope", "Rick Famuyiwa",R.drawable.aa));

        return movies;
    }
}