package com.example.machine2.movietime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.machine2.movietime.R;

//Login activity

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity_fragment);

    }
    public void onNewIntent(Intent intent){
        int i = intent.getIntExtra("FLAG", 0);

        if(i == 0)
            finish();

    }
}