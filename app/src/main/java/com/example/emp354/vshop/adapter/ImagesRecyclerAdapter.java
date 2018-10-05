package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int[] mImages;

    public ImagesRecyclerAdapter(Context context, int[] images)
    {
        mContext=context;
        mImages=images;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_product_image,viewGroup,false);
        ImagesHolder imagesHolder=new ImagesHolder(view);
        return imagesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ImagesHolder holder1=(ImagesHolder) viewHolder;
        holder1.ivProduct.setImageResource(mImages[i]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ImagesHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        public ImagesHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct=itemView.findViewById(R.id.iv_product);
        }
    }
}
