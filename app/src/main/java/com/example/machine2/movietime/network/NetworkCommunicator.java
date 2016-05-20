package com.example.machine2.movietime.network;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by machine3 on 5/20/16.
 */
public class NetworkCommunicator {

    //Variables and class declartions
    private static final String TAG = "NetworkCommunicator";
    AsyncHttpClient client;
    String url;
    Context context;

    public NetworkCommunicator(Context context, String url) {
        this.url = url;
        this.context = context;
    }

    //method created for the Movies
    public void sendRequest(final NetworkListener networkListener) {

        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api_key", "efc0d91dd29ee74d0c55029e31266793");

        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                networkListener.onResponse(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(context, "NETWORK ERROR " + error, Toast.LENGTH_LONG).show();
//                NetworkErrors networkErrors = new NetworkErrors();
//                networkErrors.showError(statusCode, error);
            }

            @Override
            public void onCancel() {
                //   super.onCancel();
                client.cancelRequests(context, true);

            }
        });
    }
}
