package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emp354.vshop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class ShopsRecyclerAdapter extends RecyclerView.Adapter{

    //declaring variables
    private Context mContext;
    private int[] mImages;

    //constructor for the adapter
    public ShopsRecyclerAdapter(Context context, int[] images)
    {
        //assigning values passed in constructor to the variable
        mContext=context;
        mImages=images;
    }

    //inflating layout and assign it to holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_shop_image,viewGroup,false);
        ShopsRecyclerAdapter.ShopsHolder imagesHolder=new ShopsRecyclerAdapter.ShopsHolder(view);
        return imagesHolder;
    }

    //binding layout with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ShopsHolder holder1=(ShopsHolder) viewHolder;
       /* holder1.ivProduct.setImageResource(mImages[i]);*/

        //loading image using imageloader
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + mImages[i],holder1.ivProduct);
    }

    //getting the number of items
    @Override
    public int getItemCount() {
        return 10;
    }

    //holder for adapter
    public class ShopsHolder extends RecyclerView.ViewHolder {

        //declaring variables
        ImageView ivProduct;
        public ShopsHolder(@NonNull View itemView) {
            super(itemView);

            //initialising variables
            ivProduct=itemView.findViewById(R.id.iv_product);
        }
    }
}
