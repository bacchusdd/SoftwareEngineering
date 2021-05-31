package com.example.motiondetectionalert;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PromptAPI {
    @GET("/")
    Call<String> getStartDetection();

    @GET("/")
    Call<String> getStopDetection();

    @POST("/")
    Call<PromptResult> postStartDetection();

    @POST("/startdetection/")
    Call<PromptResult> postStopDetection();

}