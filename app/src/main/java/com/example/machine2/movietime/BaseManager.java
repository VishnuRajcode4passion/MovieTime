package com.example.machine2.movietime;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by machine3 on 5/20/16.
 */
public class BaseManager {

    Map<String, String> mHeaders;

    //add baseManager headers
    public Map<String, String> getHeaders() {

        mHeaders = new HashMap<>();
        mHeaders.put("api_key", "efc0d91dd29ee74d0c55029e31266793");
        return mHeaders;
    }
}
