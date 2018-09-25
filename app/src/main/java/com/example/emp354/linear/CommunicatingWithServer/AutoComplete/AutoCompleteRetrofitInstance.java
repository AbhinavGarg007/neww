package com.example.emp354.linear.CommunicatingWithServer.AutoComplete;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoCompleteRetrofitInstance {

    private static Retrofit retrofitAuto,retrofitDetail;
    private static final String BASE_URL="https://maps.googleapis.com/maps/api/place/queryautocomplete/";
    private static final String BASE_URL_DETAIL="https://maps.googleapis.com/maps/api/place/details/";
   /* json?key=AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g&input=*/


   //to get singleton instance for load all places
    public static Retrofit getRetrofitInstance()
    {
        if(retrofitAuto==null)
        {
            retrofitAuto=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitAuto;
    }


    //to get singleton instance for load details of the place
    public static Retrofit getRetrofitDetailInstance()
    {
        if(retrofitDetail==null)
        {
            retrofitDetail=new Retrofit.Builder()
                    .baseUrl(BASE_URL_DETAIL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitDetail;
    }
}
