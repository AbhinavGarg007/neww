package com.example.emp354.linear.AdapterView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class CustomAdapter extends BaseAdapter{

    final int VIEWTYPE_FIRST=1;
    final int VIEWTYPE_SECOND=2;



    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position !=0 && position%5==0)
        return VIEWTYPE_SECOND;
        else return VIEWTYPE_FIRST;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType=getItemViewType(position);
        switch(viewType)
        {
            case VIEWTYPE_FIRST:
                convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
                TextView text=convertView.findViewById(R.id.list_textview);
                text.setText(String.format("Data at pos %d", position));
                break;
            case VIEWTYPE_SECOND:
                convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.drawableitem_list,parent,false);
        }
        return convertView;
    }
}
