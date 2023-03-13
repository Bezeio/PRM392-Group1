package com.example.projectnews.model;

import java.util.ArrayList;

public class MovieModal {
    private String status;
    private String totalResult;
    private ArrayList<Movie> articles;

    public MovieModal(String status, String totalResult, ArrayList<Movie> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<Movie> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Movie> articles) {
        this.articles = articles;
    }
}
