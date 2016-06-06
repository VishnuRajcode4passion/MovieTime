package com.example.machine2.movietime;

import com.example.machine2.movietime.adapters.MovieImageAdapter;
import com.example.machine2.movietime.models.MoviesPosterResponse;
import com.google.gson.Gson;

/**
 * Created by machine2 on 04/06/16.
 */
public class BaseParser {

    public MoviesPosterResponse moviesPosterResponse;
    public MovieImageAdapter imageAdapter;
    public String responseString;
    public Gson gson = new Gson();
}
