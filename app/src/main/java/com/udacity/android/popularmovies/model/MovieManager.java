package com.udacity.android.popularmovies.model;

import com.udacity.android.popularmovies.utilities.MovieDBJsonUtils;

import org.json.JSONException;

/**
 * Created by Frank on 27/7/17.
 */

public class MovieManager {

    MovieItem[] movieItems = null;

    public MovieManager(String movieJsonString) {
        try {
            this.movieItems = MovieDBJsonUtils.getMovieItemsFromJson(movieJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Public Accessors
     */

    public Boolean hasMovies() {
        return movieItems != null && movieItems.length > 0;
    }

    public int movieCount() {
        return movieItems.length;
    }

    public MovieItem getItemAtIndex(int i) {
        return movieItems[i];
    }

    public String getTitleAtIndex(int i) {
        return movieItems[i].getTitle();
    }

    public String getPosterPathAtIndex(int i) {
        return movieItems[i].getPosterPath();
    }

    public Double getPopularityAtIndex(int i) {
        return movieItems[i].getPopularity();
    }

    public MovieItem[] getAllMovieItems() {
        return this.movieItems;
    }

}
