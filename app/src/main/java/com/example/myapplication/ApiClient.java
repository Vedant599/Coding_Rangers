package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://10.0.2.2/startupinfo/scripts/";
    private static Retrofit retrofit;
    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
