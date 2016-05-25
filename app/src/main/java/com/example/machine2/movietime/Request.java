package com.example.machine2.movietime;

import java.util.Map;

/**
 * Created by machine3 on 5/25/16.
 */
public class Request {

    String url;
    Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    void setUrl(String url) {

        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
