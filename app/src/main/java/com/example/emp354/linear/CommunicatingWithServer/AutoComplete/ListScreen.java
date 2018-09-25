package com.example.emp354.linear.CommunicatingWithServer.AutoComplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.PlaceModel;
import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.Predictions;
import com.example.emp354.linear.Listener.ItemClickListener;
import com.example.emp354.linear.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListScreen extends AppCompatActivity implements ItemClickListener {

   /* String BASE_URL="https://maps.googleapis.com/maps/api/place/queryautocomplete/json?key=AIzaSyCu9-ERueubyJ5g2o0T5P5Pa6jesPoL56g&input=";*/

   //declaring variables
   AutoCompleteAdapter autoCompleteAdapter;
    RecyclerView recyclerView;
    List<Predictions> predictionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);



        //gettin value from intent
        Intent listIntent=getIntent();
        String input=listIntent.getStringExtra("input");



        //getting response through retrofit
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

    // method to set adapter with recycler view
    private void generateDataList(PlaceModel placeModel)
    {
        if(placeModel!=null) {

            recyclerView = findViewById(R.id.recyclerview_listscreen);
            autoCompleteAdapter = new AutoCompleteAdapter(this, placeModel,this);

            //initialising predictionList
            predictionsList=placeModel.getPredictions();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(autoCompleteAdapter);
        }
        else
        {
            Log.d("placemodel","object is null.");
        }
    }


    //implementing listener method
    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.place_layout:
                if(predictionsList!=null) {
                    String place_id = predictionsList.get(position).getPlace_id();
                    if(place_id!=null)
                    {
                        //moving to next screen with place_id
                        Intent detailIntent=new Intent(ListScreen.this,DetailScreen.class);
                        detailIntent.putExtra("place_id",place_id);
                        startActivity(detailIntent);
                    }
                }
        }
    }
}
