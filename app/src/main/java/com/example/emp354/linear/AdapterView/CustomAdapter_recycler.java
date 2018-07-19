package com.example.emp354.linear.AdapterView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class CustomAdapter_recycler extends RecyclerView.Adapter {

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflate layout
        View recycler_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview1, parent, false);
        //assign layout to holder
        MyViewHolder view_holder = new MyViewHolder(recycler_view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder ;
        holder1.textView.setText("Data at pos" + position);

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            textView = (TextView) itemView.findViewById(R.id.recycler_textview);

        }
    }
}
