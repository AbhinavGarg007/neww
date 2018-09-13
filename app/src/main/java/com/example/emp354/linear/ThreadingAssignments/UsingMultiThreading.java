package com.example.emp354.linear.ThreadingAssignments;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.emp354.linear.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class UsingMultiThreading extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<ListItem> listItem;
    ProgressDialog dialog;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threading_assignment);

        recyclerView=findViewById(R.id.recyclerview_multithreading);
        listItem=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        handler = new Handler(getApplicationContext().getMainLooper());

        Log.d("onCreate", "Thread id: " + Thread.currentThread().getId());
        dialog= ProgressDialog.show(UsingMultiThreading.this,"Loading data","Please Wait..");

        loadRecyclerData();

        }

    private void loadRecyclerData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    JSONObject jsonObject=new JSONObject(loadJSONFromAsset());
                    JSONArray array=jsonObject.getJSONArray("results");

                    for(int i=0;i<array.length();i++)
                    {
                        Log.d("loadRecyclerData", "Thread id: " + Thread.currentThread().getId());

                        JSONObject o=array.getJSONObject(i);
                        ListItem item=new ListItem(o.getString("name"),
                                o.getString("vicinity"));
                        listItem.add(item);
                        Thread.sleep(1000);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("handler", "Thread id: " + Thread.currentThread().getId());
                                dialog.dismiss();
                                adapter=new MyAdapter(listItem,getApplicationContext());
                                recyclerView.setAdapter(adapter);

                            }
                        });


                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }





            }
        }).start();


    }


    //getting string object from json file
    private String loadJSONFromAsset()
    {
        String json=null;
        try
        {
            InputStream is=getAssets().open("json_data.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
