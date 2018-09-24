package com.example.emp354.linear.AutoComplete;

import com.example.emp354.linear.AutoComplete.POJO.PlaceModel;
import com.example.emp354.linear.AutoComplete.POJO.Predictions;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface AutoCompleteService {

 /* */  String API_KEY="AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g";
   /* String API_KEY="AIzaSyAPiG2fwDSl9qn5EhkY48EMFU67nKzOciI";*/

    @GET("json")
    Call<PlaceModel> getAllPlaces(@Query("key") String key,@Query("input") String input);
}

