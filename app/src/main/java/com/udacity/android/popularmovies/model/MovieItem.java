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
    private String releaseDate  = null;
//    private String duration     = null;

    public MovieItem(String title, Double popularity, String posterPath, String releaseDate, String overview/*, String duration*/) {
        this.title          = title;
        this.popularity     = popularity;
        this.posterPath     = posterPath;
        this.overview       = overview;
        this.releaseDate    = releaseDate;
//        this.duration       = duration;
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

    public String getOverview() {
        return this.overview;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

//    public String getDuration() {
//        return this.duration;
//    }

}
