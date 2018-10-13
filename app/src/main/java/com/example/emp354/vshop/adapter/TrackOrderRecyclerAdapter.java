package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.listener.ItemClickListener;

public class TrackOrderRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mHeight;
    private ItemClickListener mItemClickListener;
    public TrackOrderRecyclerAdapter(Context context,int height,ItemClickListener itemClickListener)
    {
        mContext=context;
        mHeight=height;
        mItemClickListener=itemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_track_order_item,viewGroup,false);
        TrackOrderViewHolder trackOrderViewHolder=new TrackOrderViewHolder(view);
        return trackOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TrackOrderViewHolder holder=(TrackOrderViewHolder)viewHolder;

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class TrackOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layoutTrackOrderItem;
        public TrackOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutTrackOrderItem=itemView.findViewById(R.id.ll_track_order_item);
            layoutTrackOrderItem.setOnClickListener(this);
           /* layoutTrackOrderItem.getLayoutParams().height=mHeight;*/
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
