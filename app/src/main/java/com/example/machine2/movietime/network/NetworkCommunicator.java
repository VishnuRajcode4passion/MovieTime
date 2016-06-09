package com.example.machine2.movietime.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.machine2.movietime.models.Requests;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by machine3 on 5/20/16.
 */
public class NetworkCommunicator {

    //   Variables declarations
//    AsyncHttpClient client;
//    RequestParams params;
//    String url;
//
//    //method created for sending requestUrl to server
//    public void sendRequest(final NetworkListener networkListener, Requests request) {
//
//        url = request.getUrl();
//        params = new RequestParams(request.getHeaders());
//        client = new AsyncHttpClient();
//        client.get(url, params, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                Log.d("NetworkCommunicator", "Headers " + headers);
//                networkListener.onSuccess(responseBody);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//                Log.d("NetworkCommunicator ", "NETWORK ERROR " + statusCode +error +responseBody);
//                networkListener.onFailure(responseBody);
//            }
//        });
//    }

    RequestQueue requestQueue;
    Context context;
    JsonObjectRequest jsonObjectRequest;
    String url;
    String headers;
    byte[] responseBody;
    Map<String, String> header;

    public NetworkCommunicator(Context context) {

        this.context = context;
    }

    public void sendRequest(final NetworkListener networkListener, Requests request) {

        requestQueue = Volley.newRequestQueue(context);
        url = request.getUrl();
        header = request.getHeaders();
        headers = header.toString();
        headers = headers.replaceAll("[{}]", "");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + headers, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        responseBody = response.toString().getBytes();
                        networkListener.onSuccess(context,responseBody);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Volley", "Error" + error);
                        responseBody = error.toString().getBytes();
                        networkListener.onFailure(responseBody);
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
