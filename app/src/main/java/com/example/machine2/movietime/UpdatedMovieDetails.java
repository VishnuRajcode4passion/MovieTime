package com.example.machine2.movietime;

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
    Request request= new Request();


    UrlProvider urlProvider;
    int runtime;
    double vote_average;
    public String gettitle(){

      return original_title;
    }
    void settitle(String original_title)
    {
        this.original_title = original_title;
    }

    void setImage(String poster_path)
    {
        paramMap = request.getHeaders();
        params = new RequestParams(paramMap);
        this.poster_path = poster_path;
        posters_path = urlProvider.IMAGE_URL+poster_path+params;
    }
    public String getImage()
    {
       return posters_path;
    }
    void setDuration(int runtime)
    {
       this.runtime = runtime;
    }
    public Integer getDuration()
    {
        return  runtime;
    }
    void setRatings(double vote_average)
    {
      this.vote_average = vote_average;
    }
    public double getRatings()
    {
     return vote_average;
    }
    void setReleseDate(String release_date)
    {
        this.release_date = release_date;
    }
    public String getReleaseDate()
    {
      return release_date;
    }
    void setDescription(String overview)
    {
       this.overview = overview;
    }
    public String getDescription()
    {
     return overview;
    }

}
