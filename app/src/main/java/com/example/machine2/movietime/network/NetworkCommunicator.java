package com.example.machine2.movietime.network;

import android.util.Log;

import com.example.machine2.movietime.BaseManager;
import com.example.machine2.movietime.Request;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by machine3 on 5/20/16.
 */
public class NetworkCommunicator {

    //Variables declarations
    AsyncHttpClient client;
    RequestParams params;
    String url;

    //method created for sending requestUrl to server
    public void sendRequest(final NetworkListener networkListener, Request request) {

        url = request.getUrl();
        params = new RequestParams(request.getHeaders());
        client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                networkListener.onSuccess(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.d("NetworkCommunicator ", "NETWORK ERROR " + error);
            }

            @Override
            public void onCancel() {
                super.onCancel();
                client.cancelAllRequests(true);
            }
        });
    }
}
