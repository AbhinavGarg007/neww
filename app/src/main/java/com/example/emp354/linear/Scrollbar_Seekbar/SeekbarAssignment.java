package com.example.emp354.linear.Scrollbar_Seekbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emp354.linear.DrawablesFigureAssignment;
import com.example.emp354.linear.R;
import com.example.emp354.linear.TextView.TextviewAssignment1;

public class SeekbarAssignment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbarassignment);
        Spinner spinner1=findViewById(R.id.spinner1);
        SeekBar seekbar=findViewById(R.id.seekbar);



        Button btn_1=findViewById(R.id.btn_1);
        Button btn_2=findViewById(R.id.btn_2);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SeekbarAssignment.this,ScrollbarAssignment.class);
                startActivity(i);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(SeekbarAssignment.this,TextviewAssignment1.class);
                startActivity(j);
            }
        });


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.fruits,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView seektextview=findViewById(R.id.seektextview);
                seektextview.setText("SeekbarAssignment progresss is - " +  i);
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
        spintextview.setText("Spinner selected item_list is - " + text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
