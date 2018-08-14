package com.example.emp354.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        String s1 = i.getStringExtra(MainActivity.NAME);
        String s2 = i.getStringExtra(MainActivity.EMAIL);
        String s3 = i.getStringExtra(MainActivity.PHONE);

        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        //tv2.setText("Name: " + s1 + "Email: " + s2 + "Phone: " + s3);
        tv3.setText(String.format(" %s\n %s\n %s\n", s1, s2, s3));

    }

}
