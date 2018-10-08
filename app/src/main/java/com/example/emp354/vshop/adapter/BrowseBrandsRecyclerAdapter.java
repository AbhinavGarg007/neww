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

import java.util.HashSet;

public class BrowseBrandsRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int[] mImages;
    private String[] mTitle;
    private int mHeight;
    private int mWidth;
    private ItemClickListener mItemClickListener;
    private HashSet<Integer> mIsFollowed;

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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_browse_brands,viewGroup,false);
        BrowseBrandsViewHolder browseBrandsViewHolder=new BrowseBrandsViewHolder(view);
        return browseBrandsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        BrowseBrandsViewHolder holder1=(BrowseBrandsViewHolder)viewHolder;
        if(mImages!=null) {
            holder1.ivBrand.setImageResource(mImages[i]);
        }
        if(mTitle!=null)
        {
            holder1.tvBrand.setText(mTitle[i]);
        }
        if(i%2!=0)
        {
            holder1.llBrand.setBackgroundColor(mContext.getResources().getColor(R.color.color_list));
        }
        else
        {
            holder1.llBrand.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }
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

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    public class BrowseBrandsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivBrand;
        TextView tvBrand,tvFollow;
        LinearLayout llBrand;


        public BrowseBrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBrand=itemView.findViewById(R.id.iv_browse_brands);
            llBrand=itemView.findViewById(R.id.layout_brand);
            ivBrand.getLayoutParams().height=mHeight;
            ivBrand.getLayoutParams().width=mWidth;
            tvBrand=itemView.findViewById(R.id.tv_brand_title);
            tvFollow=itemView.findViewById(R.id.tv_follow_browse_brands);
            tvFollow.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
