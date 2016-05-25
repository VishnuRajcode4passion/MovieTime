package com.example.machine2.movietime.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by machine3 on 5/20/16.
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    public void dialogShow() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait.");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
    public void dialogDismiss() {

        progressDialog.dismiss();
    }
}


