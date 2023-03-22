package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.projectnews.adapter.MovieAdapter;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        DBHelper dbHelper = new DBHelper(this);
        RecyclerView rv = findViewById(R.id.rv);
        List<Movie> movieArrayList = dbHelper.getMovieFromCart();
        MovieAdapter newsRVAdapter = new MovieAdapter(this, (ArrayList<Movie>) movieArrayList);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.setAdapter(newsRVAdapter);

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }
}