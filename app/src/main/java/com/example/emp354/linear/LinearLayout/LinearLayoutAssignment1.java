package com.example.emp354.linear.LinearLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class LinearLayoutAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayoutassignment1);
        Button b5=findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LinearLayoutAssignment1.this,LinearLayoutAssignment2.class);
                startActivity(i);
            }
        });
    }
}
