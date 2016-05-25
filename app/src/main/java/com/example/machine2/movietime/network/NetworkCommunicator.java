package com.example.machine2.movietime.network;

import android.content.Context;
import android.widget.Toast;

import com.example.machine2.movietime.MoviePosterParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by machine3 on 5/20/16.
 */
public class NetworkCommunicator {

    //Variables and class declartions
    AsyncHttpClient client;
    Context context;
    MoviePosterParser moviePosterParser;

    //method created for sending requestUrl to server
    public void sendRequest(final NetworkListener networkListener, String Url, RequestParams params) {

        client = new AsyncHttpClient();
        client.get(Url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                networkListener.onSuccess(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(context, "NETWORK ERROR " + error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                   super.onCancel();
                   client.cancelRequests(context, true);

            }
        });
    }
    public void weatherRequest(final NetworkListener networkListener,CharSequence city, RequestParams params) {
        String Url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=45df4fca7d202600be0e657e2d0a9dcd";
        client = new AsyncHttpClient();
        client.get(Url,null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                networkListener.onSuccess(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(context, "NETWORK ERROR " + error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                client.cancelRequests(context, true);

            }
        });
    }


}
