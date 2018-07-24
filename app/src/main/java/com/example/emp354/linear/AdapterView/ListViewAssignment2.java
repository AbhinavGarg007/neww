package com.example.emp354.linear.AdapterView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emp354.linear.R;
import com.example.emp354.linear.SnackbarAssignment;

public class ListViewAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewassignment);


        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ListViewAssignment2.this, ListViewAssignment1.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(ListViewAssignment2.this,ListViewAssignment3.class);
                startActivity(j);
            }
        });


        final String[] data_1=getResources().getStringArray(R.array.data);
        ArrayAdapter<String> array_adapter=new ArrayAdapter<String>(this,R.layout.item_list,R.id.list_textview,data_1);
        ListView listview_2=findViewById(R.id.list_view);
        listview_2.setAdapter(array_adapter);

        View header_1= (View)getLayoutInflater().inflate(R.layout.header,null,false);
        View footer_1= (View)getLayoutInflater().inflate(R.layout.footer,null,false);

        listview_2.addHeaderView(header_1);
        listview_2.addFooterView(footer_1);

        listview_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item_pos=position;
                String message = "You clicked on position " + position ;
                Toast.makeText(ListViewAssignment2.this,message,Toast.LENGTH_LONG).show();
            }
        });

    }
}
