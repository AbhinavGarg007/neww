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

public class ListViewAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewassignment);

        Button back=findViewById(R.id.back);
        Button next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ListViewAssignment1.this, SnackbarAssignment.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(ListViewAssignment1.this,ListViewAssignment2.class);
                startActivity(j);
            }
        });


       //you can simply declare the array here
       // String[] data={"parmesan","ricotta","fotina","mozzarella","cheddar"};
       // ArrayAdapter<String> cheeseadapter=new ArrayAdapter<String>(this,R.layout.item_list,R.id.cheese_name,data);

       //you can declare the string array in string.xml
        final String[] data_1 = getResources().getStringArray(R.array.data);
        ArrayAdapter<String> cheeseadapter=new ArrayAdapter<String>(this,R.layout.item_list,R.id.list_textview,data_1);
        ListView listview_1=findViewById(R.id.list_view);

        listview_1.setAdapter(cheeseadapter);

        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "You clicked on " + data_1[position];
                Toast.makeText(ListViewAssignment1.this,message,Toast.LENGTH_LONG).show();
            }
        });
        }


}

