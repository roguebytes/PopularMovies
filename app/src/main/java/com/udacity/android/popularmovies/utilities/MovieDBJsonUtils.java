package com.udacity.android.popularmovies.utilities;


import com.udacity.android.popularmovies.model.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Frank on 24/7/17.
 */

public final class MovieDBJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing movie details.
     *
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing movie data
     */
    public static MovieItem[] getMovieItemsFromJson(String movieJsonString) throws JSONException {

        final String MDB_RESULTS = "results";
        final String MDB_MESSAGE_CODE = "cod";

        final String MDB_TITLE = "title";
        final String MDB_VOTE_COUNT = "vote_count";
        final String MDB_ID = "id";
        final String MDB_VIDEO = "video";
        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_POPULARITY = "popularity";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_ORIGINAL_LANGUAGE = "original_language";
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_GENRE_IDS = "genre_ids";
        final String MDB_BACKDROP_PATH = "backdrop_path";
        final String MDB_ADULT = "adult";
        final String MDB_OVERVIEW = "overview";
        final String MDB_RELEASE_DATE = "release_date";

        // String array to hold each day's weather String
        MovieItem[] movieItems = null;

        JSONObject movieJSON = new JSONObject(movieJsonString);

        // Is there an error?
        if (movieJSON.has(MDB_MESSAGE_CODE)) {
            int errorCode = movieJSON.getInt(MDB_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    // No movie data available
                    return null;
                default:
                    // Server error likely
                    return null;
            }
        }

        JSONArray movieArray = movieJSON.getJSONArray(MDB_RESULTS);

        movieItems = new MovieItem[movieArray.length()];

        for(int i = 0; i < movieArray.length(); i++) {
            // These are the values that will be collected
            String title;
            String overview;
            String posterPath;
            String releaseDate;
            Double voteAverage;

            // Get the JSON object representing the movie
            JSONObject movieJSONObject = movieArray.getJSONObject(i);
            title           = movieJSONObject.getString(MDB_TITLE);
            posterPath      = movieJSONObject.getString(MDB_POSTER_PATH);
            releaseDate     = movieJSONObject.getString(MDB_RELEASE_DATE);
            overview        = movieJSONObject.getString(MDB_OVERVIEW);
            voteAverage     = movieJSONObject.getDouble(MDB_VOTE_AVERAGE);

            MovieItem movieObject = new MovieItem(title, posterPath, releaseDate, overview, voteAverage);
            movieItems[i] = movieObject;

        }

        return movieItems;
    }

}
