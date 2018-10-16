package com.example.emp354.vshop.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.listener.ItemClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashSet;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class BrowseBrandsRecyclerAdapter extends RecyclerView.Adapter {

    //declaring variables
    private Context mContext;
    private int[] mImages;
    private String[] mTitle;
    private int mHeight;
    private int mWidth;
    private ItemClickListener mItemClickListener;
    private HashSet<Integer> mIsFollowed;

    //constructor of adapter
    public BrowseBrandsRecyclerAdapter(Context context, int[] images, String[] title, int height, int width, HashSet<Integer> isFollowed,ItemClickListener itemClickListener)
    {

        mContext=context;
        mImages=images;
        mTitle=title;
        mHeight=height;
        mWidth=width;
        mItemClickListener=itemClickListener;
        mIsFollowed=isFollowed;
    }

    //inflating view
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_browse_brands,viewGroup,false);
        BrowseBrandsViewHolder browseBrandsViewHolder=new BrowseBrandsViewHolder(view);
        return browseBrandsViewHolder;
    }


    //binding view with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        BrowseBrandsViewHolder holder1=(BrowseBrandsViewHolder)viewHolder;
        if(mImages!=null) {
           /* holder1.ivBrand.setImageResource(mImages[i]);*/
            ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + mImages[i],holder1.ivBrand);
        }
        if(mTitle!=null)
        {
            //setting the value in textview
            holder1.tvBrand.setText(mTitle[i]);
        }

        //checking if position is even or not and setting the background coor according to that.
        if(i%2!=0)
        {
            holder1.llBrand.setBackgroundColor(mContext.getResources().getColor(R.color.color_list));
        }
        else
        {
            holder1.llBrand.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }

        //checking whether user is already following or not and setting the layout according to that
        if(mIsFollowed.contains(i))
        {
           holder1.tvFollow.setText(mContext.getResources().getString(R.string.following));
           holder1.tvFollow.setTextColor(mContext.getResources().getColor(android.R.color.white));
           holder1.tvFollow.setBackground(mContext.getResources().getDrawable(R.drawable.ic_bg_follow));
        }
        else
        {
            holder1.tvFollow.setText(mContext.getResources().getString(R.string.follow_me));
            holder1.tvFollow.setTextColor(mContext.getResources().getColor(R.color.color_black));
            holder1.tvFollow.setBackground(mContext.getResources().getDrawable(R.drawable.ic_bg_following));

    }


    }

    //getting the count of items
    @Override
    public int getItemCount() {
        return mImages.length;
    }

    public class BrowseBrandsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //declaring variable
        ImageView ivBrand;
        TextView tvBrand,tvFollow;
        LinearLayout llBrand;


        //Holder
        public BrowseBrandsViewHolder(@NonNull View itemView) {
            super(itemView);

            //initialising variable
            ivBrand=itemView.findViewById(R.id.iv_browse_brands);
            llBrand=itemView.findViewById(R.id.layout_brand);
            ivBrand.getLayoutParams().height=mHeight;
            ivBrand.getLayoutParams().width=mWidth;
            tvBrand=itemView.findViewById(R.id.tv_brand_title);
            tvFollow=itemView.findViewById(R.id.tv_follow_browse_brands);

            //setting click listener
            tvFollow.setOnClickListener(this);

        }

        //performing click operation on view
        @Override
        public void onClick(View view) {

            //passing view and adapter position to the custom click listener
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
