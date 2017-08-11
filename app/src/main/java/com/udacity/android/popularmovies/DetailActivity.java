package com.udacity.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.common.ApiConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private String posterPath   = "";
    private String movieTitle   = "";
    private String releaseDate  = "";
    private String overview     = "";
    private Double voteAverage  = 0.0;

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

        if (intentThatStartedThisActivity.hasExtra("overview")) {
            this.overview = intentThatStartedThisActivity.getStringExtra("overview");
            this.setMovieOverview();
        }

        if (intentThatStartedThisActivity.hasExtra("title")) {
            this.releaseDate = intentThatStartedThisActivity.getStringExtra("releaseDate");
            this.setMovieReleaseDate();
        }

        if (intentThatStartedThisActivity.hasExtra("voteAverage")) {
            this.voteAverage = intentThatStartedThisActivity.getDoubleExtra("voteAverage", 0.0);
            this.setVoteAverage();
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

    private void setMovieOverview() {
        TextView overviewTextView = (TextView) findViewById(R.id.detail_movie_synopsis);
        overviewTextView.setText(this.overview);
    }

    private void setMovieReleaseDate() {
        TextView releaseDateTextView = (TextView) findViewById(R.id.detail_movie_year);
        // Convert String to date and then extact the year component
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(this.releaseDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            int year = cal.get(Calendar.YEAR);
            releaseDateTextView.setText(String.valueOf(year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setVoteAverage() {
        TextView popularityTextView = (TextView) findViewById(R.id.detail_movie_popularity);
        String voteAverageString = String.format("%.1f", this.voteAverage) + "/10";
        popularityTextView.setText(voteAverageString);
    }
}
