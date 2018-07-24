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

public class Flight_assignment extends AppCompatActivity {
private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_assignment);

        ImageView back=findViewById(R.id.back);
        Button next=findViewById(R.id.search_flight_btn);

        Spinner spinner=findViewById(R.id.spinner);
        mTopToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.flight_class,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Flight_assignment.this,Child_view.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Flight_assignment.this,EditVesselDetails.class);
                startActivity(j);
            }
        });
    }
}
