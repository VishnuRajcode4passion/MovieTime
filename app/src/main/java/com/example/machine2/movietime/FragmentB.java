package com.example.machine2.movietime;

/**
 * Created by machine2 on 25/05/16.
 */

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class FragmentB extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentsb, container, false);
        textView = (TextView) view.findViewById(R.id.textView1);
        return view;
    }

    public void change(WeatherResponse weatherResponse) {
        String wind = weatherResponse.getWind().toString();
        textView.setText(wind);
    }


}