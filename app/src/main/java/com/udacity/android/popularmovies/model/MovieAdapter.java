package com.udacity.android.popularmovies.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.common.ApiConstants;

import java.util.List;

/**
 * Created by Frank on 6/08/2017.
 */

public class MovieAdapter extends ArrayAdapter<MovieItem> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param movieItems     A List of MovieItem objects to display in a list
     */
    public MovieAdapter(Activity context, List<MovieItem> movieItems) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, movieItems);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        MovieItem movieItem = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_item_icon);
//        iconView.setImageResource(movieItem.image);

        String posterQueryPath = ApiConstants.IMAGE_BASE_URL + ApiConstants.IMAGE_DEFAULT_SIZE + movieItem.getPosterPath();
//        URL posterUrl = NetworkUtils.buildUrl(posterQuery);

        Picasso.with(convertView.getContext()).load(posterQueryPath).into(iconView);
//        Picasso.with(convertView.getContext()).load(posterUrl).into(iconView);
//
//        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_version_name);
//        versionNameView.setText(androidFlavor.versionName);
//
//        TextView versionNumberView = (TextView) convertView.findViewById(R.id.list_item_versionnumber_textview);
//        versionNumberView.setText(androidFlavor.versionNumber);
        return convertView;
    }

}
