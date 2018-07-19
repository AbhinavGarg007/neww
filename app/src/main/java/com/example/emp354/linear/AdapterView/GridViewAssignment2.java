package com.example.emp354.linear.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.example.emp354.linear.R;

public class GridViewAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewassignment);

        GridView gridview_2=findViewById(R.id.grid_view);
        CustomAdpater_grid custom_adapter = new CustomAdpater_grid();
        gridview_2.setAdapter(custom_adapter);
    }
}
