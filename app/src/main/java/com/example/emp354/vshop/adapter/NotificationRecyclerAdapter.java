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

    //declaring varibles
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private int mHeight;

    //constructor for adapter
    public NotificationRecyclerAdapter(Context context,int height,ItemClickListener itemClickListener)
    {
        mContext=context;
        mItemClickListener=itemClickListener;
        mHeight=height;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_notification_item,viewGroup,false);
        return new NotificationViewHolder(view);
    }

    //binding the data with the layout
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        NotificationViewHolder holder=(NotificationViewHolder) viewHolder;

        //checking whether the required position and inflating layout according to that

        if(i%2!=0)
        {
           /* holder.ivShipping.setImageResource(R.drawable.ic_track_shiping2);*/

            ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + R.drawable.ic_track_shiping2,holder.ivShipping);
        }
        else
        {
            if(i==0)
            {
                holder.tvNew.setVisibility(View.VISIBLE);
                holder.tvNotificationType.setText(R.string.best_seller);
                holder.tvDescription.setText("Our top picks in Sunglasses");
                ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + R.drawable.glasses,holder.ivShipping);
                holder.layoutItemNotification.setEnabled(false);
            }
            else {
                /*holder.ivShipping.setImageResource(R.drawable.ic_track_shiping);*/
                holder.tvNew.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + R.drawable.ic_track_shiping,holder.ivShipping);
            }

        }

    }

    //returning the count of number of items
    @Override
    public int getItemCount() {
        return 10;
    }

    //holder for adapter
    public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //declaring variables
        ImageView ivShipping;
        TextView tvDescription,tvNotificationType,tvNew;
        LinearLayout layoutItemNotification;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialising variables
            ivShipping=itemView.findViewById(R.id.iv_shipping);
            tvDescription=itemView.findViewById(R.id.tv_description_shipping);
            tvNotificationType=itemView.findViewById(R.id.tv_notification_type);
            tvNew=itemView.findViewById(R.id.tv_new);
            layoutItemNotification=itemView.findViewById(R.id.layout_item_notification);

           /* layoutItemNotification.getLayoutParams().height=mHeight;*/
            layoutItemNotification.setOnClickListener(this);
        }

        //passing view and adapter position to the custom listener after getting clicked
        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
