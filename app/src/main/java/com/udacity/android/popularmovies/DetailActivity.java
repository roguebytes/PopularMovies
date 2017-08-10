package com.udacity.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.common.ApiConstants;

public class DetailActivity extends AppCompatActivity {

    private String posterPath   = "";
    private String movieTitle   = "";
    private Double popularity   = 0.0;
    private String releaseDate  = "";
    private String overview     = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("posterPath")) {
            this.posterPath = intentThatStartedThisActivity.getStringExtra("posterPath");
            this.loadMoviePoster();
        }

        if (intentThatStartedThisActivity.hasExtra("title")) {
            this.movieTitle = intentThatStartedThisActivity.getStringExtra("title");
            this.setMovieTitle();
        }

        if (intentThatStartedThisActivity.hasExtra("popularity")) {
            this.popularity = intentThatStartedThisActivity.getDoubleExtra("popularity", 0.0);
            this.setMoviePopularity();
        }

        if (intentThatStartedThisActivity.hasExtra("overview")) {
            this.overview = intentThatStartedThisActivity.getStringExtra("overview");
            this.setMovieOverview();
        }

        if (intentThatStartedThisActivity.hasExtra("title")) {
            this.releaseDate = intentThatStartedThisActivity.getStringExtra("releaseDate");
            this.setMovieReleaseDate();
        }

    }

    private void loadMoviePoster() {
        ImageView iconView = (ImageView) findViewById(R.id.detail_movie_poster);

        String posterQueryPath = ApiConstants.IMAGE_BASE_URL + ApiConstants.IMAGE_DEFAULT_SIZE + this.posterPath;

        Picasso.with(DetailActivity.this).load(posterQueryPath).into(iconView);
    }

    private void setMovieTitle() {
        TextView titleTextView = (TextView) findViewById(R.id.detail_movie_title);
        titleTextView.setText(this.movieTitle);
    }

    private void setMoviePopularity() {
        TextView popularityTextView = (TextView) findViewById(R.id.detail_movie_popularity);
        String popularityString = String.format("%.1f", this.popularity) + "/10";
        popularityTextView.setText(popularityString);
    }

    private void setMovieOverview() {
        TextView overviewTextView = (TextView) findViewById(R.id.detail_movie_synopsis);
        overviewTextView.setText(this.overview);
    }

    private void setMovieReleaseDate() {
        TextView releaseDateTextView = (TextView) findViewById(R.id.detail_movie_year);
        releaseDateTextView.setText(this.releaseDate);
    }
}
