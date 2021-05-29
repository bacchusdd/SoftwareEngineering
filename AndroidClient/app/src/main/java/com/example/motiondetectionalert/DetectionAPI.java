package com.example.motiondetectionalert;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DetectionAPI {
    @GET("/movie.json")
    Call<String> getMovieList();
}
