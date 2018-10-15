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

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ItemClickListener mItemClickListener;

    public NotificationRecyclerAdapter(Context context,ItemClickListener itemClickListener)
    {
        mContext=context;
        mItemClickListener=itemClickListener;
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
           /* holder.ivShipping.setImageResource(R.drawable.ic_track_shiping2);*/
            ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + R.drawable.ic_track_shiping2,holder.ivShipping);
        }
        else
        {
            /*holder.ivShipping.setImageResource(R.drawable.ic_track_shiping);*/
            ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + R.drawable.ic_track_shiping,holder.ivShipping);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivShipping;
        TextView tvDescription;
        LinearLayout layoutItemNotification;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivShipping=itemView.findViewById(R.id.iv_shipping);
            tvDescription=itemView.findViewById(R.id.tv_description_shipping);
            layoutItemNotification=itemView.findViewById(R.id.layout_item_notification);

            layoutItemNotification.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
