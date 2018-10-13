package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.vshop.R;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public NotificationRecyclerAdapter(Context context)
    {
        mContext=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_notification_item,viewGroup,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        NotificationViewHolder holder=(NotificationViewHolder) viewHolder;
        if(i%2!=0)
        {
            holder.ivShipping.setImageResource(R.drawable.ic_track_shiping2);
        }
        else
        {
            holder.ivShipping.setImageResource(R.drawable.ic_track_shiping);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShipping;
        TextView tvDescription;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivShipping=itemView.findViewById(R.id.iv_shipping);
            tvDescription=itemView.findViewById(R.id.tv_description_shipping);
        }
    }
}
