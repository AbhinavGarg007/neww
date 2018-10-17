package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;

public class DiscoverRecyclerAdapter extends RecyclerView.Adapter {

    //declaring variables
    private Context mContext;


    //constructor for adapter
    public DiscoverRecyclerAdapter(Context context)
    {
        //asigning values passed in constructor to the variable
        mContext=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_discover_item,viewGroup,false);
        DiscoverViewHolder discoverViewHolder=new DiscoverViewHolder(view);
        return discoverViewHolder;
    }

    //binding view with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        DiscoverViewHolder holder=(DiscoverViewHolder) viewHolder;
    }

    //getting count of number of items
    @Override
    public int getItemCount() {
        return 5;
    }

    //holder for the adapter
    public class DiscoverViewHolder extends RecyclerView.ViewHolder {
        public DiscoverViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
