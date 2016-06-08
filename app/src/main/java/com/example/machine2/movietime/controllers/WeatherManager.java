package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.models.UpdatedWeatherDetails;
import com.example.machine2.movietime.models.WeatherResponse;
import com.example.machine2.movietime.network.NetworkCommunicator;
import com.example.machine2.movietime.network.NetworkListener;
import com.example.machine2.movietime.parser.MoviesErrorParser;
import com.example.machine2.movietime.parser.WeatherDetailParser;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherManager extends BaseManager implements NetworkListener {

    WeatherDetailsListener weatherDetailsListener;
    String city_name;
    WeatherResponse.MainBean weatherResponse;
    NetworkCommunicator networkCommunicator;
    UpdatedWeatherDetails updatedWeatherDetails = new UpdatedWeatherDetails();
    public void getWeather(WeatherDetailsListener weatherDetailsListener, String city_name) {
        Requests request;

        this.weatherDetailsListener = weatherDetailsListener;
        this.city_name = city_name;
        request = new Requests();
        request.setUrl(UrlProvider.WEATHER_URL+city_name);
        request.setHeader(getHeader());

       // networkCommunicator = new NetworkCommunicator();
        networkCommunicator.sendRequest(this, request);
    }

    @Override
    public void onSuccess(byte[] responseBody) {

        WeatherDetailParser weatherDetailParser;
        weatherDetailParser = new WeatherDetailParser();
        weatherResponse =weatherDetailParser.parse(responseBody);
        updatedWeatherDetails.setTemp(weatherResponse.getTemp());

        weatherDetailsListener.setWeatherDetails(updatedWeatherDetails);
    }

    @Override
    public void onFailure(byte[] responseBody) {

        String statusMessage;
        MoviesErrorParser moviesErrorParser;
        moviesErrorParser = new MoviesErrorParser();
        statusMessage = moviesErrorParser.parse(responseBody);
    }
}
