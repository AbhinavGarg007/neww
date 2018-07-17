package com.example.emp354.linear.LinearLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.FrameLayout.FrameLayoutAssignment1;
import com.example.emp354.linear.R;

public class LinearLayoutAssignment4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayoutassignment4);

        Button b1=findViewById(R.id.b1);
        Button b3=findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LinearLayoutAssignment4.this,FrameLayoutAssignment1.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(LinearLayoutAssignment4.this,LinearLayoutAssignment3.class);
                startActivity(i);
            }
        });
    }
}
