package com.example.emp354.linear.Scrollbar_Seekbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.emp354.linear.R;
import com.example.emp354.linear.RelativeLayout.RelativeLayoutAssignment3;
import com.example.emp354.linear.RelativeLayout.RelativeLayoutAssignment4;

public class ScrollbarAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollbarassignment);

        Button scroll_btn_1=findViewById(R.id.scroll_btn_1);
        Button scroll_btn_2=findViewById(R.id.scroll_btn_21);

        scroll_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ScrollbarAssignment.this,RelativeLayoutAssignment4.class);
                startActivity(i);
            }
        });
        scroll_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ScrollbarAssignment.this,SeekbarAssignment.class);
                startActivity(i);
            }
        });
    }
}
