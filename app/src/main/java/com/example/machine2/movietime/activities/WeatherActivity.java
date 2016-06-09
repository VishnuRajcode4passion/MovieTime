package com.example.machine2.movietime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.controllers.WeatherManager;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherActivity extends BaseActivity  {

    double temp;
    Button search;
    EditText searchCity;
    String cityName;
    WeatherManager weatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfragment);
        search = (Button) findViewById(R.id.searchs);
        searchCity = (EditText)findViewById(R.id.search);

               search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cityName = searchCity.getText().toString();
                System.out.println("city"+cityName);
                if (cityName !=null) {

                    Intent intent = new Intent(WeatherActivity.this,WeatherDetailsActivity.class);
                    intent.putExtra("cityName",cityName);
                    startActivity(intent);
                }
            }
        });
    }

}