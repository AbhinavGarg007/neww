package com.example.emp354.linear.ThreadingAssignments.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.example.emp354.linear.JSONUtils;
import com.example.emp354.linear.ThreadingAssignments.POJO.Geometry;
import com.example.emp354.linear.ThreadingAssignments.POJO.Location;
import com.example.emp354.linear.ThreadingAssignments.POJO.OpeningHours;
import com.example.emp354.linear.ThreadingAssignments.POJO.Photos;
import com.example.emp354.linear.ThreadingAssignments.POJO.Results;
import com.example.emp354.linear.ThreadingAssignments.POJO.ViewPort;
import com.example.emp354.linear.ThreadingAssignments.POJO_Service.MyObject_1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyIntentService extends IntentService{

    public MyIntentService()
    {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        MyObject_1 object=loadRecyclerData();

        Intent intent1=new Intent("intent_service_data");
        intent1.putExtra("data",object);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent1);



    }

    public MyObject_1 loadRecyclerData()
    {

        //getting from the given json file

        String jsonData=loadJSONFromAsset();

        final MyObject_1 myObject=new MyObject_1();

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

        for(int j=0;j<JSONUtils.getLengthOfJSONArray(json_result_array);j++)
        {
            JSONObject jsonResultObject=JSONUtils.getJSONObject(json_result_array,j);

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

            for(int k=0;k<JSONUtils.getLengthOfJSONArray(jsonWeekdayTextArray);k++)
            {
                weekday_list.add(JSONUtils.getStringObject(jsonWeekdayTextArray,k));
            }

            openingHours.setWeekday_text(weekday_list);

            results.setOpening_hours(openingHours);

            /* openingHours.setWeekday_text(JSONUtils.getStringfromJSON(jsonOpeningHoursObject,"weekday_text"));*/



            JSONArray jsonPhotoArray=JSONUtils.getJSONArray(jsonResultObject,"photos");

            List<Photos> photosList=new ArrayList<>();

            for(int l=0;l<JSONUtils.getLengthOfJSONArray(jsonPhotoArray);l++)
            {
                JSONObject jsonPhotoObject=JSONUtils.getJSONObject(jsonPhotoArray,l);

                Photos photos=new Photos();

                photos.setHeight(JSONUtils.getIntegerfromJSON(jsonPhotoObject,"height"));

                photos.setHeight(JSONUtils.getIntegerfromJSON(jsonPhotoObject,"width"));

                photos.setPhoto_reference(JSONUtils.getStringfromJSON(jsonPhotoObject,"photo_reference"));

                JSONArray jsonHtmlAttributionsArray=JSONUtils.getJSONArray(jsonPhotoObject,"html_attributions");

                List<String> photoHtmlAttributionsList=new ArrayList<String>();

                for(int m=0;m<JSONUtils.getLengthOfJSONArray(jsonHtmlAttributionsArray);m++)
                {
                    photoHtmlAttributionsList.add(JSONUtils.getStringObject(jsonHtmlAttributionsArray,m));

                }
                photos.setHtml_attributions(photoHtmlAttributionsList);
                photosList.add(photos);
            }
            results.setPhotos(photosList);


            results.setPlace_id(JSONUtils.getStringfromJSON(jsonResultObject,"place_id"));

            results.setPrice_level(JSONUtils.getIntegerfromJSON(jsonResultObject,"price_level"));

            results.setRating(JSONUtils.getDoublefromJSON(jsonResultObject,"rating"));

            results.setReference(JSONUtils.getStringfromJSON(jsonResultObject,"reference"));

            results.setScope(JSONUtils.getStringfromJSON(jsonResultObject,"scope"));



            JSONArray jsonTypesArray=JSONUtils.getJSONArray(jsonResultObject,"types");

            List<String> typesList=new ArrayList<>();

            for (int n=0;n<JSONUtils.getLengthOfJSONArray(jsonTypesArray);n++)
            {
                typesList.add(JSONUtils.getStringObject(jsonTypesArray,n));
            }

            results.setTypes(typesList);

            results.setVicinity(JSONUtils.getStringfromJSON(jsonResultObject,"vicinity"));

            resultsList.add(results);

        }
        myObject.setResults(resultsList);

        return myObject;
    }


    //to load json from file
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
