package com.example.machine2.movietime.activity;

/**
 * Created by machine2 on 25/05/16.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.machine2.movietime.FragmentA;
import com.example.machine2.movietime.FragmentB;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.WeatherManager;

public class ActivityAB extends FragmentActivity {
    WeatherManager weatherManager;

    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);

        FragmentManager manager = getSupportFragmentManager();
        fragmentA = (FragmentA) manager.findFragmentById(R.id.fragment3);
        fragmentB = (FragmentB) manager.findFragmentById(R.id.fragment4);

        fragmentA.setTextChangeListener(new FragmentA.TextChangeListener() {

            @Override
            public void onTextChange(CharSequence newText) {
                weatherManager = new WeatherManager();
                weatherManager.manager(newText);
            }
        });
    }

}