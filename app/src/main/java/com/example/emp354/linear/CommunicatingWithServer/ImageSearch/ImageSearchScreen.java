package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.ImageModel;
import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.Items;
import com.example.emp354.linear.Listener.ItemClickListener;
import com.example.emp354.linear.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageSearchScreen extends AppCompatActivity implements ItemClickListener {

    //declaring variables
    ImageView ivSearch;
    TextView tvEmpty;
    EditText etSearch;
    BroadcastReceiver broadcastReceiver;
    RecyclerView recyclerViewSearchImage;
    ImageAdapter imageAdapter;
    List<Items> itemsList;
    ProgressDialog dialog,service_dialog;


   /* String API_KEY="AIzaSyDuCYDZW-M7fpbHsAxSo2EvEj4kfjQ-Jqg";*/
    String API_KEY="AIzaSyCGq2oBtD6jmHjqMMijiPHRsh_LBQgUZUs";
    String cx="008733068000644023346:eqlwl3brxma";
    boolean enableImageSearch=true;
    boolean disableWebSearch=true;
    String searchType="image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search_screen);

        ivSearch=findViewById(R.id.iv_search);
        tvEmpty=findViewById(R.id.tv_empty);
        etSearch=findViewById(R.id.et_search);
        recyclerViewSearchImage=findViewById(R.id.recycler_view_search_image);


        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message=intent.getStringExtra("message");
                int pos=Integer.valueOf(intent.getStringExtra("pos"));
                service_dialog.dismiss();
                Toast.makeText(ImageSearchScreen.this,message,Toast.LENGTH_SHORT).show();

               //notify the adapter
                /*imageAdapter.notifyDataSetChanged();*/

                //notify the adapter for particular position
                imageAdapter.notifyItemChanged(pos);
            }
        };


        IntentFilter intentFilter=new IntentFilter("image_data");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);


        //adding listener on edittext search bar
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(!etSearch.getText().toString().equals(""))
                {
                    if(actionId== EditorInfo.IME_ACTION_DONE)
                    {
                        dialog=ProgressDialog.show(ImageSearchScreen.this,"Loading","Please wait..");
                        FetchData(etSearch.getText().toString());
                        return true;
                    }
                }

                return false;
            }
        });


      ivSearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(!etSearch.getText().toString().equals(""))
              {
                  FetchData(etSearch.getText().toString());
              }

          }
      });

    }


    private void FetchData(String string)
    {
        ImageSearchService service=ImageRetrofitInstance.getRetrofitImageInstance().create(ImageSearchService.class);
        Call<ImageModel> call=service.getAllImages(API_KEY, cx, enableImageSearch, disableWebSearch, searchType, string);
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if(response!=null)
                    generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {

                Toast.makeText(ImageSearchScreen.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(ImageModel imageModel) {

        tvEmpty.setVisibility(View.GONE);
        recyclerViewSearchImage.setVisibility(View.VISIBLE);
        itemsList=imageModel.getItems();
        imageAdapter=new ImageAdapter(this,imageModel,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
        recyclerViewSearchImage.setLayoutManager(gridLayoutManager);
        recyclerViewSearchImage.setAdapter(imageAdapter);
        dialog.dismiss();

        //recyclerViewSearchImage.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.layout_image_search:
                if(itemsList!=null) {
                    Log.d("ImageSearch", itemsList.get(position).getImage().getThumbnailLink());
                    String url = itemsList.get(position).getImage().getThumbnailLink();

                    //to check whether file is present in internal storage or not.
                    File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "search_images");
                    String fileName = url.substring(url.length() - 10);
                    File file = new File(dir, fileName + ".jpg");
                    Log.d("ImageSearch",String.valueOf(file));
                    if (file.exists() && file.isFile())
                    {
                        String path=file.getAbsolutePath();
                        Intent intentFullView=new Intent(ImageSearchScreen.this,FullViewActivity.class);
                        intentFullView.putExtra("path",path);
                        startActivity(intentFullView);
                    }

                    else
                    {

                        Intent intent=new Intent(this,DownloadIntentService.class);
                        intent.putExtra("url",url);
                        intent.putExtra("pos",String.valueOf(position));
                        startService(intent);
                        service_dialog=ProgressDialog.show(ImageSearchScreen.this,"In progress","Please wait..");
                    }

                }
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
