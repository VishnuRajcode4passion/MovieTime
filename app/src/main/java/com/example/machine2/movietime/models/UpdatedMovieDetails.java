package com.example.machine2.movietime.models;

import com.example.machine2.movietime.UrlProvider;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by machine2 on 26/05/16.
 */
public class UpdatedMovieDetails {

    String original_title;
    String poster_path;
    String posters_path;
    String release_date;
    String overview;
    Map<String, String> paramMap;
    RequestParams params;
    Requests request = new Requests();
    UrlProvider urlProvider;
    int runtime;
    double vote_average;

    public String getTitle() {

        return original_title;
    }

    public void settitle(String original_title) {
        this.original_title = original_title;
    }

    public void setImage(String poster_path) {
        paramMap = request.getHeaders();
        params = new RequestParams(paramMap);
        this.poster_path = poster_path;
        posters_path = urlProvider.IMAGE_URL + poster_path + params;
    }

    public String getImage() {
        return posters_path;
    }

    public void setDuration(int runtime) {
        this.runtime = runtime;
    }

    public Integer getDuration() {
        return runtime;
    }

    public void setRatings(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getRatings() {
        return vote_average;
    }

    public void setReleseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setDescription(String overview) {
        this.overview = overview;
    }

    public String getDescription() {
        return overview;
    }

}
