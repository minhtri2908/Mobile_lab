package com.example.myapplicationiot.Fragments.API;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/dhtdata")
    Call<DHTmodel> getDHT();

    @GET("/api/bhdata")
    Call<BHmodel> getBH();

    @POST("")
    Call<LinearRegressionmodel> postHumidity();
}
