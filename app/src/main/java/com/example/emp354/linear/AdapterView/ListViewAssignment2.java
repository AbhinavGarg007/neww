package com.example.emp354.linear.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class ListViewAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewassignment);

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
