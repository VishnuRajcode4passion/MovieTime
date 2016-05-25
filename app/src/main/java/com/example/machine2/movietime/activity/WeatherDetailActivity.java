package com.example.machine2.movietime.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.WeatherManager;
import com.example.machine2.movietime.network.Communicator;

/**
 * Created by machine2 on 23/05/16.
 */
public class WeatherDetailActivity extends FragmentActivity {
   WeatherManager weatherManager;
    WeatherA weatherA;
    WeatherB WeatherB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        FragmentManager manager = getSupportFragmentManager();
        weatherA = (WeatherA) manager.findFragmentById(R.id.fragment);
        WeatherB = (WeatherB) manager.findFragmentById(R.id.fragment2);

        weatherA.setCommunicator(new Communicator() {

            @Override
            public void sendData(Editable city) {
                weatherManager = new WeatherManager();
                weatherManager.manager(city);

            }


        });
}
    }