package com.example.machine2.movietime.models;

/**
 * Created by machine3 on 5/26/16.
 */
public class MoviesErrorResponse {


    /**
     * status_code : 7
     * status_message : Invalid API key: You must be granted a valid key.
     */

    private int status_code;
    private String status_message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
