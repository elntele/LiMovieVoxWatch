package com.knowtest.limovievoxwatch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMovies {
    @SerializedName("results")
    private List <Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
