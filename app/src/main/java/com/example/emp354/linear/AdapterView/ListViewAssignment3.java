package com.example.emp354.linear.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.emp354.linear.R;

public class ListViewAssignment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewassignment);

        ListView listview_3=findViewById(R.id.list_view);
        CustomAdapter custom_adapter=new CustomAdapter();
        listview_3.setAdapter(custom_adapter);




    }
}
