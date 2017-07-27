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

    public Boolean hasMovies() {
        return movieItems != null && movieItems.length > 0;
    }

}
