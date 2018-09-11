package com.example.emp354.linear.AsyncTaskPractice;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amitshekhar.model.Response;
import com.example.emp354.linear.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AsyncTaskActivity_2 extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_2);
        textView=findViewById(R.id.textView);
    }

    public class DownloadWebPageTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            okhttp3.Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return "Download failed";
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }


    }
    public void onClick(View view)
    {
        DownloadWebPageTask task=new DownloadWebPageTask();
        task.execute(new String[] {"http://www.vogella.com/index/html"});
    }

}
