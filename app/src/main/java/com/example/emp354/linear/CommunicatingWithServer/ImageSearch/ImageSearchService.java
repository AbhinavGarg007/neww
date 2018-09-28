package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;


import android.util.Log;

import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.ImageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageSearchService {




    String API_KEY="AIzaSyDuCYDZW-M7fpbHsAxSo2EvEj4kfjQ-Jqg";
    String cx="008733068000644023346:eqlwl3brxma";
    String enableImageSearch="true";
    String disableWebSearch="true";
    String searchType="image";

    @GET("v1")
    Call<ImageModel> getAllImages(@Query("key") String key,
                                  @Query("cx") String cx,
                                  @Query("enableImageSearch") boolean enableImageSearch,
                                  @Query("disableWebSearch") boolean disableWebSearch,
                                  @Query("searchType") String searchType,
                                  @Query("q") String q);

}
