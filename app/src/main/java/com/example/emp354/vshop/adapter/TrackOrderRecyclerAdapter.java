package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emp354.vshop.R;

public class TrackOrderRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mHeight;
    public TrackOrderRecyclerAdapter(Context context,int height)
    {
        mContext=context;
        mHeight=height;
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
        return 4;
    }

    public class TrackOrderViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutTrackOrderItem;
        public TrackOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutTrackOrderItem=itemView.findViewById(R.id.ll_track_order_item);
           /* layoutTrackOrderItem.getLayoutParams().height=mHeight;*/
        }
    }
}
