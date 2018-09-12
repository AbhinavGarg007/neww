package com.example.emp354.linear.Multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class Multithreading_5 extends AppCompatActivity implements View.OnClickListener{

    private boolean mStopLoop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading_5);

            Log.d("begin", "Thread id: "+Thread.currentThread().getId());

            Button start=findViewById(R.id.btn_start);
            Button stop=findViewById(R.id.btn_stop);
            start.setOnClickListener(this);
            stop.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    mStopLoop=true;
               /* while (mStopLoop) {
                    Log.d("loop2", "Thread id: " + Thread.currentThread().getId());
                }*/
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (mStopLoop){
                                Log.d("start", "Thread id: "+Thread.currentThread().getId());
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
