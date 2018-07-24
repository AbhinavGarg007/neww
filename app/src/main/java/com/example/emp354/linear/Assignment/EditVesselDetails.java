package com.example.emp354.linear.Assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.emp354.linear.R;

public class EditVesselDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editvesseldetails);

        ImageView back=findViewById(R.id.back);
        Button next=findViewById(R.id.save_button);


        Spinner spinner=findViewById(R.id.spinner);
        Toolbar mTopToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.vessel_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditVesselDetails.this,Flight_assignment.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(EditVesselDetails.this,Flight_assignment.class);
            }
        });

    }
}
