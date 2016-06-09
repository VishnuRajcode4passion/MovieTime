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

    //Variables declarations
//    AsyncHttpClient client;
//    RequestParams params;
//    String url;
//    Context context;
//
//    //method created for sending requestUrl to server
//    public void sendRequest(final NetworkListener networkListener, Requests request) {
//
//        url = request.getUrl();
//        params = new RequestParams(request.getHeader());
//        System.out.println("urls"+url+params);
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
    String result;
    Integer id;
    Map<String, String> headers;

    public NetworkCommunicator(Context context) {

        this.context = context;
    }

    public void sendRequest(final NetworkListener networkListener, final Requests request) {

        requestQueue = Volley.newRequestQueue(context);
        url = request.getUrl();
        id = request.getId();

        if(id ==1) {

            headers = request.getHeaders();
        }
        else if(id == 2)
        {
            headers = request.getHeader();
        }
        else
        {
            headers = null;
        }

        result = headers.toString();
        result = result.replaceAll("[{}]", "");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + result, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Volley", "url+result " +(url + result));
                        Log.d("Volley", "Sucess");
                        networkListener.onSuccess(response.toString().getBytes());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Volley", "Error" + error);
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
