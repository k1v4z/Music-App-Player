package com.example.musicapp.service;

public class DataService {
    public static String baseUrl = "https://musicplayerdb.000webhostapp.com/Server/";
    public static ApiService getApi(){
        return ApiRetrofitClient.getRetrofitClient(baseUrl).create(ApiService.class);
    }
}
