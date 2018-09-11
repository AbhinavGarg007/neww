package com.example.emp354.linear.Multithreading;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class Multithreading_2 extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading_2);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView);
    }

    public void startProgress(View view)
    {
     Runnable runnable=new Runnable() {
         @Override
         public void run() {
             for(int i=0;i<10;i++)
             {
                 final int value=i;
                 fakeWork();
                 progressBar.post(new Runnable() {
                     @Override
                     public void run() {
                         textView.setText("Updating");
                         progressBar.setProgress(value);
                         Log.d("value",String.valueOf(value));
                     }
                 });
             }
         }
     } ;
     new Thread(runnable).start();
    }


    private void fakeWork()
    {
        try{
            SystemClock.sleep(1000);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
