package com.udacity.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.udacity.android.popularmovies.api.ApiConstant;
import com.udacity.android.popularmovies.model.MovieAdapter;
import com.udacity.android.popularmovies.model.MovieItem;
import com.udacity.android.popularmovies.model.MovieManager;
import com.udacity.android.popularmovies.utilities.MovieDBJsonUtils;
import com.udacity.android.popularmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MovieManager movieManager = null;
    private MovieAdapter movieAdapter = null;

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

        new MovieDBQueryTask(this).execute(movieSearchUrl);
    }

    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {
        private final WeakReference<Activity> weakActivity;
//        private Context mContext;

        public MovieDBQueryTask(Activity mainActivity) {
//            mContext = context;
            this.weakActivity = new WeakReference<>(mainActivity);
        }

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
                    MovieItem[] movieList = movieManager.getAllMovieItems();
                    Activity mainActivity = weakActivity.get();
                    movieAdapter = new MovieAdapter(mainActivity, Arrays.asList(movieList));

                    // Get a reference to the GridView, and attach this adapter to it
                    GridView gridView = (GridView) findViewById(R.id.movie_grid);
                    gridView.setAdapter(movieAdapter);


                    // Get images for movies


                    // Populate the GridView
                }

            }


        }
    }
}
