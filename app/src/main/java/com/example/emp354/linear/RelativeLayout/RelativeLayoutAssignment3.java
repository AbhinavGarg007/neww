package com.example.emp354.linear.RelativeLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class RelativeLayoutAssignment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayoutassignment3);
        Button btn_4=findViewById(R.id.btn_4);
        Button btn_5=findViewById(R.id.btn_5);

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RelativeLayoutAssignment3.this,RelativeLayoutAssignment2.class);
                startActivity(i);
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(RelativeLayoutAssignment3.this,RelativeLayoutAssignment4.class);
                startActivity(j);
            }
        });
    }
}
