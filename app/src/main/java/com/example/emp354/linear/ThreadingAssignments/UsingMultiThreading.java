package com.example.emp354.linear.ThreadingAssignments;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.emp354.linear.JSONUtils;
import com.example.emp354.linear.R;
import com.example.emp354.linear.ThreadingAssignments.POJO.Geometry;
import com.example.emp354.linear.ThreadingAssignments.POJO.Location;
import com.example.emp354.linear.ThreadingAssignments.POJO.MyObject;
import com.example.emp354.linear.ThreadingAssignments.POJO.OpeningHours;
import com.example.emp354.linear.ThreadingAssignments.POJO.Photos;
import com.example.emp354.linear.ThreadingAssignments.POJO.Results;
import com.example.emp354.linear.ThreadingAssignments.POJO.ViewPort;
import com.google.gson.JsonArray;

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
                    /*JSONObject jsonObject=new JSONObject(loadJSONFromAsset());
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
                    }*/


                    //getting from the given json file
                    String jsonData=loadJSONFromAsset();

                    MyObject myObject=new MyObject();

                    JSONObject jsonObject= JSONUtils.getJSONObject(jsonData);

                    //for html_attributions

                    JSONArray html_attributions_array=JSONUtils.getJSONArray(jsonObject,"html_attributions");

                    List<String> html_attributions_list=new ArrayList<String>();

                    for(int i=0;i<JSONUtils.getLengthOfJSONArray(html_attributions_array);i++)
                    {
                        html_attributions_list.add(JSONUtils.getStringObject(html_attributions_array,i));
                    }

                    myObject.setHtml_attributions(html_attributions_list);

                    //for next_page
                    myObject.setNext_page_token(JSONUtils.getStringfromJSON(jsonObject,"next_page_token"));

                    //for status

                    myObject.setStatus(JSONUtils.getStringfromJSON(jsonObject,"status"));

                    //for results

                    JSONArray json_result_array=JSONUtils.getJSONArray(jsonObject,"results");

                    List<Results> resultsList=new ArrayList<Results>();

                    for(int i=0;i<JSONUtils.getLengthOfJSONArray(json_result_array);i++)
                    {
                      JSONObject jsonResultObject=JSONUtils.getJSONObject(json_result_array,i);

                      Results results=new Results();

                      JSONObject jsonGeometryObject=JSONUtils.getJSONObject(jsonResultObject,"geometry");

                      Geometry geometry=new Geometry();

                      JSONObject jsonLocationObject=JSONUtils.getJSONObject(jsonGeometryObject,"location");

                        Location location=new Location();

                        location.setLat(JSONUtils.getDoublefromJSON(jsonLocationObject,"lat"));

                        location.setLng(JSONUtils.getDoublefromJSON(jsonLocationObject,"lng"));

                        geometry.setLocation(location);


                        JSONObject jsonViewportObject=JSONUtils.getJSONObject(jsonGeometryObject,"viewport");

                        ViewPort viewPort=new ViewPort();

                        JSONObject jsonNorthEastObject=JSONUtils.getJSONObject(jsonViewportObject,"northeast");

                        Location northeast=new Location();

                        northeast.setLat(JSONUtils.getDoublefromJSON(jsonNorthEastObject,"lat"));

                        northeast.setLng(JSONUtils.getDoublefromJSON(jsonNorthEastObject,"lng"));

                        viewPort.setNortheast(northeast);



                        JSONObject jsonSouthWestObject=JSONUtils.getJSONObject(jsonViewportObject,"southwest");

                        Location southwest=new Location();

                        southwest.setLat(JSONUtils.getDoublefromJSON(jsonSouthWestObject,"lat"));

                        southwest.setLng(JSONUtils.getDoublefromJSON(jsonSouthWestObject,"lng"));

                        viewPort.setSouthwest(southwest);

                        geometry.setViewport(viewPort);

                        results.setGeometry(geometry);


                        results.setIcon(JSONUtils.getStringfromJSON(jsonResultObject,"icon"));

                        results.setId(JSONUtils.getStringfromJSON(jsonResultObject,"id"));

                        results.setName(JSONUtils.getStringfromJSON(jsonResultObject,"name"));


                        JSONObject jsonOpeningHoursObject=JSONUtils.getJSONObject(jsonResultObject,"opening_hours");

                        OpeningHours openingHours=new OpeningHours();

                        openingHours.setOpen_now(JSONUtils.getBooleanfromJSON(jsonOpeningHoursObject,"open_now"));

                        /*JSONObject jsonWeekdayTextObject=JSONUtils.getJSONObject(jsonOpeningHoursObject,"weekday_text");*/

                        JSONArray jsonWeekdayTextArray=JSONUtils.getJSONArray(jsonOpeningHoursObject,"weekday_text") ;

                        List<String > weekday_list=new ArrayList<String>();

                        for(int k=0;k<jsonWeekdayTextArray.length();k++)
                        {
                            weekday_list.add(JSONUtils.getStringObject(jsonWeekdayTextArray,k));
                        }

                        openingHours.setWeekday_text(weekday_list);

                        results.setOpening_hours(openingHours);

                       /* openingHours.setWeekday_text(JSONUtils.getStringfromJSON(jsonOpeningHoursObject,"weekday_text"));*/



                       JSONArray jsonPhotoArray=JSONUtils.getJSONArray(jsonResultObject,"photos");

                       List<Photos> photosList=new ArrayList<>();

                       for(int l=0;l<jsonPhotoArray.length();l++)
                       {
                           JSONObject jsonPhotoObject=JSONUtils.getJSONObject(jsonPhotoArray,l);

                           Photos photos=new Photos();

                           photos.setHeight(JSONUtils.getIntegerfromJSON(jsonPhotoObject,"height"));

                           photos.setHeight(JSONUtils.getIntegerfromJSON(jsonPhotoObject,"width"));

                           photos.setPhoto_reference(JSONUtils.getStringfromJSON(jsonPhotoObject,"photo_reference"));

                           JSONArray jsonHtmlAttributionsArray=JSONUtils.getJSONArray(jsonPhotoObject,"html_attributions");

                           List<String> htmlAttributionsList=new ArrayList<>();

                           for(int m=0;m<jsonHtmlAttributionsArray.length();m++);
                           {
                               html_attributions_list.add(JSONUtils.getStringObject(jsonHtmlAttributionsArray,m));
                           }




                       }














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
