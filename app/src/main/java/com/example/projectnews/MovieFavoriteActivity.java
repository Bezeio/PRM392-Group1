package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectnews.adapter.MovieFavorAdapter;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.example.projectnews.model.Movie;

import java.util.ArrayList;

public class MovieFavoriteActivity extends AppCompatActivity {
    ArrayList<Movie> movieArrayList;
    INewDao newDao;
    DBHelper dbHelper;
    MovieFavorAdapter movieFavorAdapter;
    RecyclerView newsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favourite);

        dbHelper = new DBHelper(this);
        newDao = new NewDao(this);
        movieArrayList = new ArrayList<>();
        newsRV = findViewById(R.id.idRVNewsSearchResult);

        movieFavorAdapter = new MovieFavorAdapter(this, movieArrayList);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(movieFavorAdapter);
        getNewFavor();
        movieFavorAdapter.setData(movieArrayList);
    }

    public void onBackMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void onDeleteNewFavor(View view) {
        TextView x = view.findViewById(R.id.idTVNewFvMainHeading);
        String y = x.getText().toString();
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
    }


    void getNewFavor(){
        String username = dbHelper.getSession("username");
        ArrayList<Integer> listNewId = new ArrayList<>();
        listNewId = newDao.getListMovieFavor(username);
        for( int value : listNewId ) {
            Movie movieObj = newDao.getMovieById(value);
            if(movieObj !=null) movieArrayList.add(movieObj);
        }
    }
}