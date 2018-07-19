package com.example.emp354.linear.AdapterView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emp354.linear.R;

import static com.example.emp354.linear.R.drawable.circle;

public class CustomAdpater_grid extends BaseAdapter {

    final int VIEWTYPE_FIRST = 1;
    final int VIEWTYPE_SECOND = 2;

    //Resources mResources;

    //public CustomAdpater_grid(Context context) {
        //mResources=context.getResources();
    //}


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
        if (position != 0 && position % 5 == 0)
            return VIEWTYPE_SECOND;
        else return VIEWTYPE_FIRST;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEWTYPE_FIRST:
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
                TextView text = convertView.findViewById(R.id.grid_textview);
                text.setText(String.format("Data at pos %d", position));
                break;
            case VIEWTYPE_SECOND:
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawableitem_grid, parent, false);
                //TextView text1=convertView.findViewById(R.id.grid_textview);
                //text1.setBackgroundDrawable(mResources.getDrawable(R.drawable.circle_grid));


        }
        return convertView;
    }
}
