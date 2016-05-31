package com.example.machine2.movietime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by machine2 on 30/05/16.
 */
public class WeatherDisplayFragment extends Fragment  {

    TextView weather;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.displayfragment, container, false);
        weather = (TextView)view.findViewById(R.id.textView5);
        return view;
    }

    public void display(double temp)
    {
        weather.setText(String.valueOf(temp));
    }



}