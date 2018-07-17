package com.example.emp354.linear.RelativeLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.FrameLayout.FrameLayoutAssignment2;
import com.example.emp354.linear.R;

public class RelativeLayoutAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayoutassignment1);

        Button b2=findViewById(R.id.b2);
        Button b4=findViewById(R.id.b4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RelativeLayoutAssignment1.this,FrameLayoutAssignment2.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RelativeLayoutAssignment1.this,RelativeLayoutAssignment2.class);
                startActivity(i);
            }
        });
    }
}
