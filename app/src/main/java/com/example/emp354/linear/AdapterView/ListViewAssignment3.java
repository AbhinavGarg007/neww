package com.example.emp354.linear.AdapterView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.emp354.linear.R;
import com.example.emp354.linear.SnackbarAssignment;

public class ListViewAssignment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewassignment);

        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ListViewAssignment3.this, ListViewAssignment2.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(ListViewAssignment3.this,GridViewAssignment1.class);
                startActivity(j);
            }
        });


        ListView listview_3=findViewById(R.id.list_view);
        CustomAdapter_list custom_adapter=new CustomAdapter_list();
        listview_3.setAdapter(custom_adapter);




    }
}
