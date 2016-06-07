package com.example.machine2.movietime.parser;

import com.example.machine2.movietime.models.WeatherResponse;
import com.google.gson.Gson;

/**
 * Created by machine2 on 06/06/16.
 */
//parser class for Weather details
public class WeatherDetailParser {
    Gson gson;
    WeatherResponse.MainBean weatherResponse;
    String responseString;

    //method declaration for getting the weather responses

    public WeatherResponse.MainBean parse(byte[] responseBody) {

        responseString = new String(responseBody);
        gson = new Gson();
        weatherResponse = gson.fromJson(responseString, WeatherResponse.MainBean.class);
        return  weatherResponse;
    }
}
