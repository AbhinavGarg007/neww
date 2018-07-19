package com.example.emp354.linear.AdapterView;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.emp354.linear.R;

import java.util.stream.Stream;

public class RecyclerViewAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewassignment);


        RecyclerView recycler_view=findViewById(R.id.recycler_view);

        CustomAdapter_recycler array_adapter=new CustomAdapter_recycler();
        recycler_view.setAdapter(array_adapter);

        LinearLayoutManager layout_manager=new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(layout_manager);

        //DividerItemDecoration itemDecor=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        //itemDecor.setDrawable(getApplicationContext().getDrawable(R.drawable.divider));
        //recycler_view.addItemDecoration(itemDecor);




    }
}
