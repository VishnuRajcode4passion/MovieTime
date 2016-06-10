package com.example.machine2.movietime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    ImageButton GoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfragment);

        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        search = (Button) findViewById(R.id.searchs);
        searchCity = (EditText)findViewById(R.id.search);
        GoHome = (ImageButton)findViewById(R.id.HomeButton);



        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cityName = searchCity.getText().toString();
                System.out.println("city"+cityName);
                if (cityName !=null) {

                    Intent intent = new Intent(WeatherActivity.this,WeatherDetailsActivity.class);
                    intent.putExtra("cityname",cityName);
                    search.startAnimation(animation);
                    startActivity(intent);
                }
                else if(cityName == null)
                {
                    Toast.makeText(getApplicationContext(),"please Enter Any City",Toast.LENGTH_SHORT).show();
                }
            }
        });

        GoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }

}