package com.udacity.android.popularmovies.utilities;

import com.udacity.android.popularmovies.common.ApiConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Frank on 20/7/17.
 */

public class NetworkUtils {
    /**
     * Appends the SECRET_API_KEY to the search query to build the
     *  URL used to query the Movie DB.
     *
     * @param searchQuery The search query.
     * @return The URL with the secret API key appended
     */
    public static URL buildUrl(String searchQuery) {
        String authSearchQueryString = searchQuery + ApiConstants.API_KEY;

        URL url = null;
        try {
            url = new URL(authSearchQueryString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
