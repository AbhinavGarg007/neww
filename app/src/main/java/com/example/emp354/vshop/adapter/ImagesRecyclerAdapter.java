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
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter {

    //declaring variables
    private Context mContext;
    private int[] mImages;

    //constructor for adapter
    public ImagesRecyclerAdapter(Context context, int[] images)
    {
        mContext=context;
        mImages=images;
    }

    //inflating layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_product_image,viewGroup,false);
        ImagesHolder imagesHolder=new ImagesHolder(view);
        return imagesHolder;
    }

    //binding view with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ImagesHolder holder1=(ImagesHolder) viewHolder;
        /*holder1.ivProduct.setImageResource(mImages[i]);*/
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + mImages[i],holder1.ivProduct);
    }

    //returning the number of items
    @Override
    public int getItemCount() {
        return 10;
    }

    public class ImagesHolder extends RecyclerView.ViewHolder {
        //declaring variables
        ImageView ivProduct;
        public ImagesHolder(@NonNull View itemView) {
            super(itemView);

            //initialising variables
            ivProduct=itemView.findViewById(R.id.iv_product);
        }
    }
}
