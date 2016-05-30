package com.example.machine2.movietime.models;

import java.util.List;

/**
 * Created by machine2 on 27/05/16.
 */
public class MovieTrailerResponse {

    /**
     * id : 157336
     * results : [{"id":"533ec6fcc3a3685448009ccc","iso_639_1":"en","iso_3166_1":"US","key":"nyc6RJEEe0U","name":"Official Teaser Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5376ab510e0a26141c0005a8","iso_639_1":"en","iso_3166_1":"US","key":"zSWdZVtXT7E","name":"Official UK Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"550df44b9251413554004d43","iso_639_1":"en","iso_3166_1":"US","key":"KlyknsTJk0w","name":"Own it today","site":"YouTube","size":720,"type":"Teaser"},{"id":"571f32ac925141052c001bc1","iso_639_1":"en","iso_3166_1":"US","key":"0vxOhd4qlnA","name":"Official Trailer 3","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571f33f69251414df40020e3","iso_639_1":"en","iso_3166_1":"US","key":"827FNDpQWrQ","name":"Official UK Teaser Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571f323d92514128010000d5","iso_639_1":"en","iso_3166_1":"US","key":"2LqzF5WauAw","name":"Official Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571f32c4c3a3686c99001da7","iso_639_1":"en","iso_3166_1":"US","key":"Rt2LHkSwdPQ","name":"Official Trailer 2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"571f3355c3a3686c99001dc3","iso_639_1":"en","iso_3166_1":"US","key":"LY19rHKAaAg","name":"Official UK Trailer 4","site":"YouTube","size":1080,"type":"Trailer"},{"id":"53db3c790e0a26189a000d09","iso_639_1":"en","iso_3166_1":"US","key":"ePbKGoIGAXY","name":"Official UK Trailer 3","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    /**
     * id : 533ec6fcc3a3685448009ccc
     * iso_639_1 : en
     * iso_3166_1 : US
     * key : nyc6RJEEe0U
     * name : Official Teaser Trailer
     * site : YouTube
     * size : 1080
     * type : Trailer
     */

    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
