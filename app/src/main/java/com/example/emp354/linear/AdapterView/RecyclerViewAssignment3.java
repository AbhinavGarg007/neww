package com.example.emp354.linear.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.emp354.linear.R;

public class RecyclerViewAssignment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewassignment3);

        RecyclerView recycler_view=findViewById(R.id.recycler_view);
        GridLayoutManager layout_manager=new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
        recycler_view.setLayoutManager(layout_manager);
        CustomAdapter_recyclergrid array_adapter=new CustomAdapter_recyclergrid(this);
        recycler_view.setAdapter(array_adapter);

    }
}
