package com.example.machine2.movietime.parser;

import com.example.machine2.movietime.models.WeatherResponse;
import com.google.gson.Gson;

/**
 * Created by machine2 on 06/06/16.
 */
//parser class for Weather details
public class WeatherDetailParser {
    Gson gson;
    WeatherResponse weatherResponse;
    String responseString;

    //method declaration for getting the weather responses

    public WeatherResponse parse(byte[] responseBody) {
        responseString = new String(responseBody);
        gson = new Gson();
        weatherResponse = gson.fromJson(responseString, WeatherResponse.class);
        System.out.println("WEATHER RESPONSE "+weatherResponse);
        return  weatherResponse;
    }
}
