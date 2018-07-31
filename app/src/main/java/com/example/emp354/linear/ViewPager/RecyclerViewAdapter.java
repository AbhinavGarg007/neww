package com.example.emp354.linear.ViewPager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_content,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyViewHolder holder1=(MyViewHolder) holder;
        holder1.textview_title.setText("title" + position);

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title;
        public MyViewHolder(View itemView) {
            super(itemView);

            textview_title=itemView.findViewById(R.id.textview_title);
        }
    }
}
