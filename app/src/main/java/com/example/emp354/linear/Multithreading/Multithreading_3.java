package com.example.emp354.linear.Multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.emp354.linear.R;

public class Multithreading_3 extends AppCompatActivity {
    Button button_1,button_2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading_3);

        button_1=findViewById(R.id.button_1);
        button_2=findViewById(R.id.button_2);
        img=findViewById(R.id.imageView);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        img.post(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageResource(R.drawable.india1);
                            }
                        });
                    }
                }).start();
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        img.post(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageResource(R.drawable.india2);
                            }
                        });
                    }
                }).start();
            }
        });

    }
}
