package com.example.projectnews.dao;

import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.Movie;

import java.util.ArrayList;

public interface INewDao {
    public Movie getMovieById(int Id);
    public ArrayList<Movie> getAllMovies();
    public ArrayList<Movie> getMoviesByCategory(String category);
    public ArrayList<Movie> getMoviesBySearch(String text);
    public ArrayList<CategoryRvModal> getAllMoviesCategory();
    public ArrayList<Integer> getListMovieFavor(String username);

    //NEW FAVOR ENTITY

    //NEW FAVOR ENTITY
    ArrayList<Integer> getListNewFavor(String username);

    public boolean AddMovieFavor(int newId, String username);
    public void RemoveMovieFavor(int newId, String username);
    public boolean checkNewExistFavor(int newId, String username);
}
