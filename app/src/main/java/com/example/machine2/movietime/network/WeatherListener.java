package com.example.machine2.movietime.network;

import com.example.machine2.movietime.UpdatedWeatherDetails;

/**
 * Created by machine2 on 30/05/16.
 */
public interface WeatherListener {

    void onSuccess(UpdatedWeatherDetails updatedWeatherDetails);

}
