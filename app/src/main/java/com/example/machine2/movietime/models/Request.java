package com.example.machine2.movietime.models;

import java.util.Map;

/**
 * Created by machine3 on 5/25/16.
 */
public class Request {

    String url;
    Map<String, String> headers;
    Map<String, String> header;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> headers) {
        this.header = headers;
    }
}
