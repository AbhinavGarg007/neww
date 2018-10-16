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

import org.w3c.dom.Text;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;
import static com.example.emp354.vshop.constants.Constant.TRACK_ORDER_IMAGES;
import static com.example.emp354.vshop.constants.Constant.TRACK_ORDER_TITLE;

public class TrackOrderRecyclerAdapter extends RecyclerView.Adapter {

    //declaring variables
    private Context mContext;
    private int mHeight;
    private ItemClickListener mItemClickListener;

    //constructor for the adapter
    public TrackOrderRecyclerAdapter(Context context,int height,ItemClickListener itemClickListener)
    {
        mContext=context;
        mHeight=height;
        mItemClickListener=itemClickListener;
    }

    //inflating layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_track_order_item,viewGroup,false);
        TrackOrderViewHolder trackOrderViewHolder=new TrackOrderViewHolder(view);
        return trackOrderViewHolder;
    }

    //binding layout with the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TrackOrderViewHolder holder=(TrackOrderViewHolder)viewHolder;
        holder.tvTitle.setText(TRACK_ORDER_TITLE[i]);
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + TRACK_ORDER_IMAGES[i],holder.ivImage);

    }

    //getting the number of data items
    @Override
    public int getItemCount() {
        return 4;
    }

    //holder for the adapter
    public class TrackOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layoutTrackOrderItem;

        ImageView ivImage;
        TextView tvTitle;
        public TrackOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage=itemView.findViewById(R.id.iv_track_order);
            tvTitle=itemView.findViewById(R.id.tv_title_track_order_product);

            layoutTrackOrderItem=itemView.findViewById(R.id.ll_track_order_item);
            layoutTrackOrderItem.setOnClickListener(this);
           /* layoutTrackOrderItem.getLayoutParams().height=mHeight;*/

        }

        //passing view and adapter position in listener after getting clicked
        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
