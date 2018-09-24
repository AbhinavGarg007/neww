package com.example.emp354.linear.AutoComplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.emp354.linear.AutoComplete.POJO.PlaceModel;
import com.example.emp354.linear.AutoComplete.POJO.Predictions;
import com.example.emp354.linear.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListScreen extends AppCompatActivity {

   /* String BASE_URL="https://maps.googleapis.com/maps/api/place/queryautocomplete/json?key=AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g&input=";*/
    AutoCompleteAdapter autoCompleteAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);



        Intent listIntent=getIntent();
        String input=listIntent.getStringExtra("input");

        AutoCompleteService service= AutoCompleteRetrofitInstance.getRetrofitInstance().create(AutoCompleteService.class);
        Call<PlaceModel> call=service.getAllPlaces(AutoCompleteService.API_KEY,input);
        call.enqueue(new Callback<PlaceModel>() {
            @Override
            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                if(response!=null) {
                    generateDataList(response.body());
                }
                else{
                    Log.d("response","is null");
                }
            }

            @Override
            public void onFailure(Call<PlaceModel> call, Throwable t) {
                Toast.makeText(ListScreen.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataList(PlaceModel placeModel)
    {
        if(placeModel!=null) {
            Log.d("g", "generateDataList: ");
            Log.d("desc1", "huh  " + placeModel.getPredictions().get(0).getStructured_formatting().getMain_text_matched_substrings().get(0).getOffset());
            /* Log.d("desc", "huh  "+placeModel.getPredictions().get(0).getDescription());*/

            Log.d("g1", "generateDataList:1 ");
            recyclerView = findViewById(R.id.recyclerview_listscreen);
            autoCompleteAdapter = new AutoCompleteAdapter(this, placeModel);


            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(autoCompleteAdapter);
        }
        else
        {
            Log.d("placemodel","object is null.");
        }
    }
}
