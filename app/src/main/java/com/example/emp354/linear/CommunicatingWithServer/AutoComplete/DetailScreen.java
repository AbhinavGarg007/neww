package com.example.emp354.linear.CommunicatingWithServer.AutoComplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.PlaceModel;
import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO_2.DetailModel;
import com.example.emp354.linear.R;
import com.example.emp354.linear.RetrofitExample.RetrofitAdapter;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailScreen extends AppCompatActivity {


    //declaring variables
    ImageView ivLogo,ivBgImage;
    TextView tvTitle,tvAdress,tvTypes,tvPhone,tvWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        //initialising variables
        ivBgImage=findViewById(R.id.iv_bg_image);

        ivLogo=findViewById(R.id.iv_logo);
        tvTitle=findViewById(R.id.tv_title);
        tvAdress=findViewById(R.id.tv_address);
        tvTypes=findViewById(R.id.tv_hashtag);
        tvPhone=findViewById(R.id.tv_phone);
        tvWebsite=findViewById(R.id.tv_website);


        //getting value from intent
        Intent intent=getIntent();
        String place_id= intent.getStringExtra("place_id");

        //geting response from method
        AutoCompleteService service=AutoCompleteRetrofitInstance.getRetrofitDetailInstance().create(AutoCompleteService.class);
        Call<DetailModel> call=service.getAllDetails(AutoCompleteService.API_KEY,place_id);
        call.enqueue(new Callback<DetailModel>() {
            @Override
            //on valid response
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                if(response!=null) {
                    generateDataList(response.body());
                }
                else{
                    Log.d("response","is null");
                }
            }

            //on failure
            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                Toast.makeText(DetailScreen.this,"Error",Toast.LENGTH_SHORT).show();

            }
        });


    }
    //method to set the data in views
    private void generateDataList(DetailModel detailModel) {
        if (detailModel != null) {
            String text="";

            String url=("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key="+AutoCompleteService.API_KEY+"&photoreference="+detailModel.getResult().getPhotos().get(0).getPhoto_reference());
           /* layoutImage.setBackgroundResource(detailModel.getResult().get(0).getIcon());*/
           if(detailModel.getResult().getPhotos()!=null) {
               Log.d("ref", detailModel.getResult().getPhotos().get(0).getPhoto_reference());
               Log.d("url", "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=" + AutoCompleteService.API_KEY + "&photoreference=" + detailModel.getResult().getPhotos().get(0).getPhoto_reference());
           }
           tvTitle.setText(detailModel.getResult().getName());

           tvWebsite.setText(detailModel.getResult().getWebsite());
           tvPhone.setText(detailModel.getResult().getInternational_phone_number());
           tvAdress.setText(detailModel.getResult().getFormatted_address());

           //to load icon
            Picasso.Builder builder=new Picasso.Builder(DetailScreen.this);
            builder.downloader(new OkHttp3Downloader(this));
            builder.build().load(detailModel.getResult().getIcon())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivLogo);
          /*  Picasso.Builder builder1=new Picasso.Builder(DetailScreen.this);
            builder1.downloader(new OkHttp3Downloader(this));
            builder1.build().load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivBgImage);
*/
          //to load background image
            ImageLoader imageLoader= ImageLoader.getInstance();
            imageLoader.displayImage(url,ivBgImage);

           for(int i=0;i<detailModel.getResult().getTypes().size();i++)
           {
               text +=("#" +detailModel.getResult().getTypes().get(i));
           }
           tvTypes.setText(text);





        }
    }


}
