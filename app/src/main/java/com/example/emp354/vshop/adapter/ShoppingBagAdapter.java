package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.vshop.R;

public class ShoppingBagAdapter extends RecyclerView.Adapter {

    //declaring variables
    private Context mContext;

    //constructor for the adapter
    public ShoppingBagAdapter(Context context)
    {
        mContext=context;
    }


    //inflating layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_selected_product_edit_remove,viewGroup,false);
        ShoppingBagViewHolder shoppingBagViewHolder=new ShoppingBagViewHolder(view);
        return shoppingBagViewHolder;
    }

    //binding holder with data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ShoppingBagViewHolder holder=(ShoppingBagViewHolder)viewHolder;

    }

    //getting the number of items
    @Override
    public int getItemCount() {
        return 3;
    }

    //holder for the adapter
    public  class ShoppingBagViewHolder extends RecyclerView.ViewHolder {
        public ShoppingBagViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
