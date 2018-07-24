package com.example.emp354.linear.AdapterView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class RecyclerViewAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewassignment);


        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(RecyclerViewAssignment2.this, RecyclerViewAssignment1.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(RecyclerViewAssignment2.this,RecyclerViewAssignment3.class);
                startActivity(j);
            }
        });

        RecyclerView recycler_view=findViewById(R.id.recycler_view);

        CustomAdapter_recycler array_adapter=new CustomAdapter_recycler();
        recycler_view.setAdapter(array_adapter);

        LinearLayoutManager layout_manager=new LinearLayoutManager(getApplicationContext());
        layout_manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(layout_manager);
    }
}
