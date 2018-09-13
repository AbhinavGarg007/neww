package com.example.emp354.linear.ThreadingAssignments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

//creating main activity
public class UsingAsyncTask extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<ListItem> listItem;
    AsyncTaskLoadJson asyncTaskLoadJson=new AsyncTaskLoadJson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_assignment);

        recyclerView=findViewById(R.id.recycler_view);
        listItem=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        asyncTaskLoadJson.execute();

    }


    //creating class extending asynctask
    private class AsyncTaskLoadJson extends AsyncTask<String,String,String>
    {ProgressDialog dialog;


    //doInBackground method
        @Override
        protected String doInBackground(String... strings) {
            Log.d("doInBackground","doInBackground");
            loadRecyclerData();
            return null;
        }

        ///onPreExecute
        @Override
        protected void onPreExecute() {
            Log.d("onPreExecute","onPreExecute");
            dialog=ProgressDialog.show(UsingAsyncTask.this,"Loading data","Please Wait..");
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("onPostExecute","onPostExecute");
            dialog.dismiss();
            adapter=new MyAdapter(listItem,getApplicationContext());
            /* progressDialog.dismiss();*/
            recyclerView.setAdapter(adapter);

        }
    }


    //parsing data present in json file and adding it to list.
    private void loadRecyclerData()
    {
       /* ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
*/

        try{
            JSONObject jsonObject=new JSONObject(loadJSONFromAsset());
            JSONArray array=jsonObject.getJSONArray("results");

            for(int i=0;i<array.length();i++)
            {
                JSONObject o=array.getJSONObject(i);
                ListItem item=new ListItem(o.getString("name"),
                        o.getString("vicinity"));
                listItem.add(item);
                Thread.sleep(500);
            }


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
