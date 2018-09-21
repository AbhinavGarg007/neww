package com.example.emp354.linear.RetrofitExample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.emp354.linear.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    RetrofitAdapter retrofitAdapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        dialog=ProgressDialog.show(this,"Loading","Please Wait..");

        //create handle for retrofitInstance interface

        GetDataService service=RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhotoModel>> call=service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhotoModel>>() {
            @Override
            public void onResponse(Call<List<RetroPhotoModel>> call, Response<List<RetroPhotoModel>> response) {
                dialog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<RetroPhotoModel>> call, Throwable t) {

                dialog.dismiss();
                Toast.makeText(RetrofitActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataList(List<RetroPhotoModel> list)
    {
        recyclerView=findViewById(R.id.retrofit_recycler);
        retrofitAdapter=new RetrofitAdapter(this,list);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(retrofitAdapter);
    }
}
