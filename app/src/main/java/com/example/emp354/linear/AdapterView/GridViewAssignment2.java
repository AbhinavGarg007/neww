package com.example.emp354.linear.AdapterView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.emp354.linear.R;

public class GridViewAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewassignment);

        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GridViewAssignment2.this, GridViewAssignment1.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(GridViewAssignment2.this,RecyclerViewAssignment1.class);
                startActivity(j);
            }
        });

        GridView gridview_2=findViewById(R.id.grid_view);
        CustomAdpater_grid custom_adapter = new CustomAdpater_grid();
        gridview_2.setAdapter(custom_adapter);
    }
}
