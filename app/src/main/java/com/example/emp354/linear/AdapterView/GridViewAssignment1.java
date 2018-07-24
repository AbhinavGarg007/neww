package com.example.emp354.linear.AdapterView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.emp354.linear.R;
import com.example.emp354.linear.SnackbarAssignment;

public class GridViewAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewassignment);

        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GridViewAssignment1.this, ListViewAssignment3.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(GridViewAssignment1.this,GridViewAssignment2.class);
                startActivity(j);
            }
        });


        String[] data_1 = getResources().getStringArray(R.array.data);
        ArrayAdapter<String> cheeseadapter=new ArrayAdapter<String>(this,R.layout.item_grid,R.id.grid_textview,data_1);
        GridView grid_view_1=findViewById(R.id.grid_view);

        grid_view_1.setAdapter(cheeseadapter);
    }
}
