package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.ImageModel;
import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.Items;
import com.example.emp354.linear.Listener.ItemClickListener;
import com.example.emp354.linear.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ImageModel mImageModel;
    private List<Items> itemsList;
    ItemClickListener mItemClickListener;

    public ImageAdapter(Context context, ImageModel imageModel,ItemClickListener itemClickListener)
    {
     mContext=context;
     mItemClickListener=itemClickListener;
     if(imageModel!=null)
     {
         mImageModel=imageModel;
         itemsList=mImageModel.getItems();
     }

    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_search_layout,parent,false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageHolder holder1=(ImageHolder) holder;

        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(itemsList.get(position).getImage().getThumbnailLink(),holder1.ivImage);



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImage;
        FrameLayout frameLayout;


        public ImageHolder(View itemView) {
            super(itemView);
            ivImage=itemView.findViewById(R.id.iv_result_image);
            frameLayout=itemView.findViewById(R.id.layout_image_search);
            frameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           mItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
