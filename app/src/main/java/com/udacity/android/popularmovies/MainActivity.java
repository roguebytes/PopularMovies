package com.udacity.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.udacity.android.popularmovies.api.ApiConstant;
import com.udacity.android.popularmovies.model.MovieManager;
import com.udacity.android.popularmovies.utilities.MovieDBJsonUtils;
import com.udacity.android.popularmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private MovieManager movieManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeMovieDBSearchQuery();
    }

    /**
     * This method retrieves a list of the current popular movies from the Movie DB
     */
    private void makeMovieDBSearchQuery() {
        String movieQuery = ApiConstant.POPULAR_MOVIES_URL;
        URL movieSearchUrl = NetworkUtils.buildUrl(movieQuery);

        new MovieDBQueryTask().execute(movieSearchUrl);
    }

    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String movieSearchResults = null;
            try {
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(searchURL);
                Log.v("HTML Response", movieSearchResults);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieSearchResults;
        }

        @Override
        protected void onPostExecute(String movieSearchResults) {
            super.onPostExecute(movieSearchResults);
            if (movieSearchResults != null && !movieSearchResults.equals("")) {
                // Turn JSON string into a usable data structure
                movieManager = new MovieManager(movieSearchResults);

                if (movieManager.hasMovies()) {
                    Log.v("message", "MovieManager has movies!");
                }


//                try {
//                    String[] movieArray = MovieDBJsonUtils.getSimpleMovieStringsFromJson(MainActivity.this, movieSearchResults);
//                    Log.v("JSON", "Got movieArray");
//                } catch(JSONException e) {
//                    e.printStackTrace();
//                }

                // Get images for movies
                // Populate the GridView
            }


        }
    }
}
