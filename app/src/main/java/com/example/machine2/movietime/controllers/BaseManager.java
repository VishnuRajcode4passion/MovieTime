package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by machine3 on 5/20/16.
 */
public class BaseManager {

    Map<String, String> mHeaders;

    //api key for MovieDB api
    public Map<String, String> getHeaders() {

        mHeaders = new HashMap<>();

        mHeaders.put("api_key", Constants.MOVIE_API_KEY);
        return mHeaders;
    }

    //api key for OpenWeatherMap api.
    public Map<String, String> getHeader() {

        mHeaders = new HashMap<>();
        mHeaders.put("api_key", Constants.WEATHER_API_KEY);
        return mHeaders;
    }
}
