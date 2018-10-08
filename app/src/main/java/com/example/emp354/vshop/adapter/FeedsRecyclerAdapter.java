package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.listener.ItemClickListener;

public class FeedsRecyclerAdapter extends RecyclerView.Adapter {
    Context mContext;
    int[] mFeeds;
    String[] mTitle;
    String[] mPrice;
    ItemClickListener mItemClickListener;
    int mHeight;

    public FeedsRecyclerAdapter(Context context,int[] feeds,String[] title,String[] price,ItemClickListener itemClickListener,int height)
    {
      mContext=context;
      mFeeds=feeds;
      mTitle=title;
      mPrice=price;
      mItemClickListener =itemClickListener;
      mHeight=height;
    }
    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_feed,viewGroup,false);
        FeedsHolder feedsHolder=new FeedsHolder(view);
        return feedsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        FeedsHolder feedsHolder=(FeedsHolder)viewHolder;
        feedsHolder.ivFeed.setImageResource(mFeeds[position]);
        feedsHolder.tvTitle.setText(mTitle[position]);
        feedsHolder.tvNewPrice.setText(mPrice[position]);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FeedsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivFeed,ivActionBar;
        LinearLayout layoutItemFeeds;
        TextView tvTitle,tvNewPrice,tvOldPrice;
        View view_dim;

        public FeedsHolder(@NonNull View itemView) {
            super(itemView);
            ivFeed=itemView.findViewById(R.id.iv_feed);

            ivActionBar=itemView.findViewById(R.id.iv_action_bar);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvNewPrice=itemView.findViewById(R.id.tv_new_price);
            tvOldPrice=itemView.findViewById(R.id.tv_old_price);
            view_dim=itemView.findViewById(R.id.view_dim);


            layoutItemFeeds=itemView.findViewById(R.id.layout_action_bar);
            ivFeed.setOnClickListener(this);
            ivFeed.getLayoutParams().height=mHeight;
            view_dim.getLayoutParams().height=mHeight;
            ivActionBar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_action_bar:
                    if(layoutItemFeeds.getVisibility() == View.GONE && view_dim.getVisibility() == View.GONE){
                        layoutItemFeeds.setVisibility(View.VISIBLE);
                        view_dim.setVisibility(View.VISIBLE);
                    }
                    else {
                        layoutItemFeeds.setVisibility(View.GONE);
                        view_dim.setVisibility(View.GONE);
                    }

            }
            mItemClickListener.onItemClick(view,getAdapterPosition());

        }
    }
}
