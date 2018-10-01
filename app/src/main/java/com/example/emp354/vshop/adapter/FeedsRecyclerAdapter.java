package com.example.emp354.vshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;

public class FeedsRecyclerAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_feed,viewGroup,false);
        FeedsHolder feedsHolder=new FeedsHolder(view);
        return feedsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        FeedsHolder feedsHolder=(FeedsHolder)viewHolder;

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FeedsHolder extends RecyclerView.ViewHolder {
        public FeedsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
