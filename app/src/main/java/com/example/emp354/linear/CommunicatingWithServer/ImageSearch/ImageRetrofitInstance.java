package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageRetrofitInstance {
    private static final String BASE_URL="https://www.googleapis.com/customsearch/";

    private static Retrofit retrofitImage;

    public  static Retrofit getRetrofitImageInstance()

    {
        Log.d("ImageSearch","getting retrofit instance");
        if(retrofitImage==null)
        {
            retrofitImage=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitImage;
    }
}
