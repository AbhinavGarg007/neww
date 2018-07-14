package com.example.emp354.linear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Seekbar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        Spinner spinner1=findViewById(R.id.spinner1);
        SeekBar seekbar=findViewById(R.id.seekbar);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.fruits,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView seektextview=findViewById(R.id.seektextview);
                seektextview.setText("Seekbar progresss is - " +  i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
        TextView spintextview=findViewById(R.id.spintextview);
        spintextview.setText("Spinner selected item is - " + text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
