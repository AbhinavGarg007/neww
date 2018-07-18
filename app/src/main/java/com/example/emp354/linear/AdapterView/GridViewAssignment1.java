package com.example.emp354.linear.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.emp354.linear.R;

public class GridViewAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewassignment);

        String[] data_1 = getResources().getStringArray(R.array.data);
        ArrayAdapter<String> cheeseadapter=new ArrayAdapter<String>(this,R.layout.item_grid,R.id.grid_textview,data_1);
        GridView grid_view_1=findViewById(R.id.grid_view);

        grid_view_1.setAdapter(cheeseadapter);
    }
}
