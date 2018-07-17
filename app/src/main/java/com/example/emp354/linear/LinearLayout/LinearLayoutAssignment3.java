package com.example.emp354.linear.LinearLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class LinearLayoutAssignment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayoutassignment3);
        Button b1=findViewById(R.id.b1);
        Button b3=findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LinearLayoutAssignment3.this,LinearLayoutAssignment2.class);
                startActivity(i);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(LinearLayoutAssignment3.this,LinearLayoutAssignment4.class);
                startActivity(i);
            }
        });
    }
}
