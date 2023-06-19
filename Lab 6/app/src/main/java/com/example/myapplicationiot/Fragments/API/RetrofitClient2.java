package com.example.myapplicationiot.Fragments.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://api-weather-tin.onrender.com/";
    public static Retrofit postRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
