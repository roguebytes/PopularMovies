package com.udacity.android.popularmovies.model;

/**
 * Created by Frank on 27/7/17.
 */

public class MovieItem {

    private String title        = null;
    private String overview     = null;
    private String posterPath   = null;
    private String backdropPath = null;
    private Double voteAverage  = 0.0;
    private Double popularity   = 0.0;
    private Boolean hasVideo    = false;

    public MovieItem(String title, Double popularity, String posterPath) {
        this.title          = title;
        this.popularity     = popularity;
        this.posterPath     = posterPath;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getPopularity() {
        return this.popularity;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

}
