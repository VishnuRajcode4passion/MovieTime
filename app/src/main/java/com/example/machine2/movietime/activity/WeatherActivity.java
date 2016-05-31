package com.example.machine2.movietime.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.UpdatedWeatherDetails;
import com.example.machine2.movietime.WeatherDisplayFragment;
import com.example.machine2.movietime.WeatherManager;
import com.example.machine2.movietime.WeatherSearchFragment;
import com.example.machine2.movietime.network.WeatherListener;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherActivity extends BaseActivity implements WeatherListener {
    WeatherSearchFragment searchFragment;
    WeatherDisplayFragment displayFragment;

    double temp;
    Button search;
    EditText searchCity;
    String cityName;
    WeatherManager weatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfragment);
        FragmentManager manager = getSupportFragmentManager();
        searchFragment = (WeatherSearchFragment)manager.findFragmentById(R.id.fragment);
        displayFragment = (WeatherDisplayFragment)manager.findFragmentById(R.id.fragment2);
        search = (Button) findViewById(R.id.searchs);
        searchCity = (EditText)findViewById(R.id.search);
        cityName = searchCity.getText().toString();
        System.out.println("city"+cityName);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != cityName) {
                    weatherManager = new WeatherManager();
                    weatherManager.getWeather(cityName);


                }
            }
        });
    }




    @Override
    public void onSuccess(UpdatedWeatherDetails updatedWeatherDetails) {
        temp = updatedWeatherDetails.getWeather();
        displayFragment.display(temp);


    }
}