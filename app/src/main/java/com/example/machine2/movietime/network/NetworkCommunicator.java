package com.example.machine2.movietime.network;

import android.util.Log;

import com.example.machine2.movietime.models.Requests;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by machine3 on 5/20/16.
 */
//Network class for creating connections

public class NetworkCommunicator {

    //Variables declarations
    AsyncHttpClient client;
    RequestParams params;
    String url;


    // method created for sending requestUrl to server

    public void sendRequest(final NetworkListener networkListener, Requests request) {

        url = request.getUrl();
        params = new RequestParams(request.getHeaders());
        client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("NetworkCommunicator", "Headers " + headers);
                networkListener.onSuccess(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.d("NetworkCommunicator ", "NETWORK ERROR " + statusCode +error +responseBody);
                networkListener.onFailure(responseBody);
            }
        });
    }

//    public void sendRequest(final NetworkListener networkListener,  final Requests request) {
//
//        RequestQueue requestQueue = Volley.newRequestQueue((Context) networkListener);
//        String url = request.getUrl();
//
//        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
//                new Response.Listener<Byte[]>() {
//                    @Override
//                    public void onResponse(Byte[] bytes) {
//
//                   //     byte[] toPrimitives bytes[];
//                        byte[] byt = new byte[](toPrimitives bytes);
//                        networkListener.onSuccess(byt);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Log.e("Volley", "Error");
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//
//                Map<String, String> params = request.getHeaders();
//                return params;
//            }
//        };
//        requestQueue.add(jor);
//    }


}
