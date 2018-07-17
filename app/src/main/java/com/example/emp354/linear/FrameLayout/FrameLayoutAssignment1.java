package com.example.emp354.linear.FrameLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.emp354.linear.LinearLayout.LinearLayoutAssignment4;
import com.example.emp354.linear.R;

public class FrameLayoutAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayoutassignment1);

    FrameLayout f1=findViewById(R.id.f1);
    FrameLayout f2=findViewById(R.id.f2);

    f1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(FrameLayoutAssignment1.this,FrameLayoutAssignment2.class);
            startActivity(i);
        }
    });
    f2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(FrameLayoutAssignment1.this,LinearLayoutAssignment4.class);
            startActivity(i);
        }
    });




    }

}
