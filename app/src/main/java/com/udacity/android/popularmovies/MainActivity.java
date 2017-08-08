package com.udacity.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.udacity.android.popularmovies.common.ApiConstants;
import com.udacity.android.popularmovies.common.AppConstants;
import com.udacity.android.popularmovies.model.MovieAdapter;
import com.udacity.android.popularmovies.model.MovieItem;
import com.udacity.android.popularmovies.model.MovieManager;
import com.udacity.android.popularmovies.utilities.NetworkUtils;

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

        makeMovieDBSearchQuery(AppConstants.SORT_KEY_TOP_RATED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_sort_top_rated) {
            Context context = MainActivity.this;
            this.makeMovieDBSearchQuery(AppConstants.SORT_KEY_TOP_RATED);
            return true;
        }
        else if (itemThatWasClickedId == R.id.action_sort_popular) {
            Context context = MainActivity.this;
            this.makeMovieDBSearchQuery(AppConstants.SORT_KEY_POPULAR);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method retrieves a list of the current popular movies from the Movie DB
     */
    private void makeMovieDBSearchQuery(String sortKey) {
        String movieQuery = "";

        if (sortKey.equalsIgnoreCase(AppConstants.SORT_KEY_TOP_RATED)) {
            movieQuery = ApiConstants.TOP_MOVIES_URL;
        }
        else if (sortKey.equalsIgnoreCase(AppConstants.SORT_KEY_POPULAR)) {
            movieQuery = ApiConstants.POPULAR_MOVIES_URL;
        }

        URL movieSearchUrl = NetworkUtils.buildUrl(movieQuery);

        new MovieDBQueryTask(this).execute(movieSearchUrl);
    }

    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {
        private final WeakReference<Activity> weakActivity;

        public MovieDBQueryTask(Activity mainActivity) {
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
                }
            }
        }
    }
}
