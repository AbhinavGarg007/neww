package com.example.emp354.linear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.VisualVoicemailService;
import android.telephony.VisualVoicemailSms;
import android.view.View;
import android.widget.ImageView;

import com.example.emp354.linear.ActionBar.ActionbarAssignment;

public class DrawablesFigureAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawablesfigureassignment);

        View view_1 = findViewById(R.id.view_1);
        View view_2 = findViewById(R.id.view_2);

        view_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DrawablesFigureAssignment.this, ActionbarAssignment.class);
                startActivity(i);
            }
        });
        view_1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent j= new Intent(DrawablesFigureAssignment.this, ImageView.class);
                startActivity(j);
            }
        });

    }
}
