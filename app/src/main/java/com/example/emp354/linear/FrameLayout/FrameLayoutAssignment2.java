package com.example.emp354.linear.FrameLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.emp354.linear.R;
import com.example.emp354.linear.RelativeLayout.RelativeLayoutAssignment1;

public class FrameLayoutAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayoutassignment2);

        FrameLayout f1=findViewById(R.id.f1);
        FrameLayout f2=findViewById(R.id.f2);

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FrameLayoutAssignment2.this,RelativeLayoutAssignment1.class);
                startActivity(i);
            }
        });
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FrameLayoutAssignment2.this,FrameLayoutAssignment1.class);
                startActivity(i);
            }
        });
    }
}
