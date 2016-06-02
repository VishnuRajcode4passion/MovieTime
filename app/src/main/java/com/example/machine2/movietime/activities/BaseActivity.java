package com.example.machine2.movietime.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by machine3 on 5/20/16.
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    public void showDialog() {

        showDialog("Processing...", "Please wait.");
    }

    public void showDialog(String title, String message) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissDialog() {

        progressDialog.dismiss();
    }
}






