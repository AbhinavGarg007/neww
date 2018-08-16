package com.example.emp354.linear.AsyncTaskPractice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class AsyncTaskActivity extends AppCompatActivity {

    private EditText edittext_time;
    private TextView textview_1, textview_2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_activity);

        edittext_time = findViewById(R.id.edittext_time);
        textview_1 = findViewById(R.id.textview_1);
        textview_2 = findViewById(R.id.textview_2);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleeptime = edittext_time.getText().toString();
                runner.execute(sleeptime);
            }
        });


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String resp;
        ProgressDialog dialog;



        /*@Override
        protected String doInBackground(String... params) {
          *//*  publishProgress("Sleeping...");*//*

            try {
                int time = Integer.parseInt(params[0]) * 1000;
                Thread.sleep(time);
                resp=("Slept for " + params[0] + " seconds");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                resp=e.getMessage();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                resp=e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            textview_2.setText(s);
        }

        @Override
        protected void onPreExecute() {
            dialog=ProgressDialog.show(AsyncTaskActivity.this,"ProgressDialog","Wait for " + edittext_time.getText().toString()+ " seconds");

        }

        @Override
        protected void onProgressUpdate(String... values) {
           *//* dialog=ProgressDialog.show(AsyncTaskActivity.this,"ProgressDialog","Wait for " + Integer.valueOf(values[0])+ " seconds");*//*
            textview_2.setText(values[0]);

        }*/


        @Override
        public String doInBackground(String... params) {




            for (int i = Integer.valueOf(params[0]); i >= 0; i--) {
                try {
                    publishProgress("Sleeping for .. " + i + " seconds.");
                   /* dialog = ProgressDialog.show(AsyncTaskActivity.this, "ProgressDialog", "Wait for " + i + " seconds");*/
                    Thread.sleep(1000);
                    resp="Slept for " + params[0] + " seconds.";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    resp = e.getMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                    resp = e.getMessage();

                }
            }
            return resp;
        }

        @Override
        public void onPostExecute(String s) {
            dialog.dismiss();
            textview_2.setText(s);
        }

        @Override
        public void onPreExecute() {
            dialog = ProgressDialog.show(AsyncTaskActivity.this, "ProgressDialog", "Please Wait....");

        }

        @Override
        public void onProgressUpdate(String... values) {
            /* dialog=ProgressDialog.show(AsyncTaskActivity.this,"ProgressDialog","Done");*/
            textview_2.setText(values[0]);

        }


    }
}
