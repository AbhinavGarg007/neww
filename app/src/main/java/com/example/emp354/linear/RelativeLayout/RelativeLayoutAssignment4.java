package com.example.emp354.linear.RelativeLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.emp354.linear.R;


import com.example.emp354.linear.Scrollbar_Seekbar.ScrollbarAssignment;

public class RelativeLayoutAssignment4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayoutassignment4);


    Button apple=findViewById(R.id.apple);
    Button grapes=findViewById(R.id.grapes);

        apple.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(RelativeLayoutAssignment4.this,RelativeLayoutAssignment3.class);
            startActivity(i);
        }
    });
        grapes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(RelativeLayoutAssignment4.this,ScrollbarAssignment.class);
            startActivity(i);
        }
    });
}}
