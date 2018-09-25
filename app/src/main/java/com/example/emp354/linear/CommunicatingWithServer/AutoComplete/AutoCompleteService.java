package com.example.emp354.linear.CommunicatingWithServer.AutoComplete;

import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.PlaceModel;
import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.Predictions;
import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO_2.DetailModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface AutoCompleteService {

  /*String API_KEY="AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g";*/
  /*String API_KEY="AIzaSyBYBDyW8THT6XvYuWoDl13rBctzFj7BuUk";*/
    String API_KEY="AIzaSyAPiG2fwDSl9qn5EhkY48EMFU67nKzOciI";

    //method to get all places information
    @GET("json")
    Call<PlaceModel> getAllPlaces(@Query("key") String key,@Query("input") String input);

    //method to get places detail
    @GET("json")
    Call<DetailModel> getAllDetails(@Query("key") String key, @Query("placeid") String input);
}

