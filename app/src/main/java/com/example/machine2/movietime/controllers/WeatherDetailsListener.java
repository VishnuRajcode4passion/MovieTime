package com.example.machine2.movietime.controllers;

import com.example.machine2.movietime.models.UpdatedWeatherDetails;

/**
 * Created by machine2 on 06/06/16.
 */
public interface WeatherDetailsListener {
    void setWeatherDetails(UpdatedWeatherDetails updatedWeatherDetails);
}