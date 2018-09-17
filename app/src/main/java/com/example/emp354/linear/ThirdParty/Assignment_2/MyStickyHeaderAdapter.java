package com.example.emp354.linear.ThirdParty.Assignment_2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

public class MyStickyHeaderAdapter implements StickyRecyclerHeadersAdapter {
    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);
        return new RecyclerView.ViewHolder(view){};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView=(TextView)holder.itemView.findViewById(R.id.grid_textview);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

    }

   /* @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return 0;
    }
}
