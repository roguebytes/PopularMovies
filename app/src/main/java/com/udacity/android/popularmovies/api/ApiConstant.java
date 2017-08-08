package com.udacity.android.popularmovies.api;

/**
 * Created by Frank on 20/7/17.
 */

public class ApiConstant {
    public static final String API_KEY = "SECRET_API_KEY";
    public static final String POPULAR_MOVIES_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    public static final String TOP_MOVIES_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=";
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_DEFAULT_SIZE = "w185";

    private ApiConstant() {
        // Hiding Constructor
    }
}
