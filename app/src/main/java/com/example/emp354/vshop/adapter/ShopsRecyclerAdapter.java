package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emp354.vshop.R;

public class ShopsRecyclerAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private int[] mImages;

    public ShopsRecyclerAdapter(Context context, int[] images)
    {
        mContext=context;
        mImages=images;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_shop_image,viewGroup,false);
        ShopsRecyclerAdapter.ShopsHolder imagesHolder=new ShopsRecyclerAdapter.ShopsHolder(view);
        return imagesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ShopsHolder holder1=(ShopsHolder) viewHolder;
        holder1.ivProduct.setImageResource(mImages[i]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ShopsHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        public ShopsHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct=itemView.findViewById(R.id.iv_product);
        }
    }
}
