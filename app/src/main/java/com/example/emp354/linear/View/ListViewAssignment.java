package com.example.emp354.linear.View;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emp354.linear.R;

public class ListViewAssignment extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        String[] cheeses=getResources().getStringArray(R.array.cheese);

        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.listview,R.layout.item,cheeses));
        }


}

