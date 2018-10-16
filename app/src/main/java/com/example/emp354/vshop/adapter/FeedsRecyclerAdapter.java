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
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class FeedsRecyclerAdapter extends RecyclerView.Adapter {

    //declaring variables
    Context mContext;
    int[] mFeeds;
    String[] mTitle;
    String[] mPrice;
    ItemClickListener mItemClickListener;
    int mHeight;

    //constructor for the adapter
    public FeedsRecyclerAdapter(Context context,int[] feeds,String[] title,String[] price,ItemClickListener itemClickListener,int height)
    {
      mContext=context;
      mFeeds=feeds;
      mTitle=title;
      mPrice=price;
      mItemClickListener =itemClickListener;
      mHeight=height;
    }

    //inflating layout
    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_feed,viewGroup,false);
        FeedsHolder feedsHolder=new FeedsHolder(view);
        return feedsHolder;
    }

    //binding view with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        FeedsHolder feedsHolder=(FeedsHolder)viewHolder;
       /* feedsHolder.ivFeed.setImageResource(mFeeds[position]);*/
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + mFeeds[position],feedsHolder.ivFeed);
        feedsHolder.tvTitle.setText(mTitle[position]);
        feedsHolder.tvNewPrice.setText(mPrice[position]);

    }

    //getting count of the number of items
    @Override
    public int getItemCount() {
        return 10;
    }


    //holder for the adapter
    public class FeedsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //declaring variables
        ImageView ivFeed,ivActionBar;
        LinearLayout layoutItemFeeds;
        TextView tvTitle,tvNewPrice,tvOldPrice;
        View view_dim;

        public FeedsHolder(@NonNull View itemView) {
            super(itemView);

            //initialising variables
            ivFeed=itemView.findViewById(R.id.iv_feed);
            ivActionBar=itemView.findViewById(R.id.iv_action_bar);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvNewPrice=itemView.findViewById(R.id.tv_new_price);
            tvOldPrice=itemView.findViewById(R.id.tv_old_price);
            view_dim=itemView.findViewById(R.id.view_dim);


            layoutItemFeeds=itemView.findViewById(R.id.layout_action_bar);

            //setting listener
            ivFeed.setOnClickListener(this);

            //setting height of the layout
            ivFeed.getLayoutParams().height=mHeight;
            view_dim.getLayoutParams().height=mHeight;
            ivActionBar.setOnClickListener(this);
        }

        //performing click on actiondots in layout
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
            //passing view and adapter position to the itemClickListener
            mItemClickListener.onItemClick(view,getAdapterPosition());

        }
    }
}
