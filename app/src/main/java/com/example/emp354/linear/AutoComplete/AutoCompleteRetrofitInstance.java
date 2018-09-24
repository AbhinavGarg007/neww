package com.example.emp354.linear.AutoComplete;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoCompleteRetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL="https://maps.googleapis.com/maps/api/place/queryautocomplete/";
   /* json?key=AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g&input=*/

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
