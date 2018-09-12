package com.example.emp354.linear.Multithreading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class Multithreading_6 extends AppCompatActivity implements View.OnClickListener{
    private boolean mStopLoop;
    private Handler handler;
    private TextView textView;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading_6);


            Log.d("begin", "Thread id: " + Thread.currentThread().getId());

            textView = findViewById(R.id.textView22);
            Button start = findViewById(R.id.btn_start);
            Button stop = findViewById(R.id.btn_stop);

            start.setOnClickListener(this);
            stop.setOnClickListener(this);

            handler = new Handler(getApplicationContext().getMainLooper());
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    mStopLoop=true;
                /*while (mStopLoop) {
                    Log.d("loop2", "Thread id: " + Thread.currentThread().getId());
                }*/
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (mStopLoop){
                                try{
                                    Thread.sleep(1000);

                                    Log.d("loop2", "Thread id: " + Thread.currentThread().getId());
                                    count++;
                                } catch (InterruptedException e) {
                                    Log.d("error", "error "+e.getMessage());
                                }

                        /*handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("loop2", "Thread id: " + Thread.currentThread().getId());
                                textView.setText(" "+count);
                            }
                        });*/

                                /*Another way of doing it without handler*/

                                textView.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        textView.setText(" "+count);
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                case R.id.btn_stop:
                    mStopLoop=false;
                    Log.d("stop", "Thread id: "+Thread.currentThread().getId());
                    break;
            }

        }
    }

