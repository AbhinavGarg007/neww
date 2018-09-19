/*
package com.example.emp354.linear.Networking;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.example.emp354.linear.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHttpTask extends AsyncTask<String,void,Integer> {

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection=null;
        Integer result=0;

        try
        {
            //forming java.net.URL object
            URL url = new URL(strings[0]);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            //optional request header
            httpURLConnection.setRequestProperty("content-Type","application/json");
            //optional request header
            httpURLConnection.setRequestProperty("Accept","application/json");
            //for get request
            httpURLConnection.setRequestMethod("GET");
            int  statusCode=httpURLConnection.getResponseCode();

            //200 represents HTTP Ok

            if(statusCode==200)
            {
                inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
                String response=convertInputStreamToString(inputStream);
                parseResult(response);
                result=1;  //success

            }
            else
            {
                result=0;  //failed to fetch
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        if(integer==1)
        {
           ArrayAdapter arrayAdapter=new ArrayAdapter(NetworkingExample.this, R.layout.);
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String line="";
        String result="";

        while ((line=bufferedReader.readLine()) !=null)
        {
            result += line;
        }
        //close stream
        if(null!=inputStream)
        {
                inputStream.close();

        }
        return result;
    }


    private void parseResult(String result)
    {
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray posts=jsonObject.optJSONArray("posts");
            String[] blogTitles=new String[posts.length()];

            for(int i=0;i<posts.length();i++)
            {
                JSONObject post=posts.optJSONObject(i);
                String title=post.optString("title");
                blogTitles[i]=title;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
*/
