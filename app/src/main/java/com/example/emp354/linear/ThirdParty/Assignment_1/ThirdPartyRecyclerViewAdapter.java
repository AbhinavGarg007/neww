package com.example.emp354.linear.ThirdParty.Assignment_1;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emp354.linear.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ThirdPartyRecyclerViewAdapter extends RecyclerView.Adapter {

    private String [] mimages=new String[10];


    public ThirdPartyRecyclerViewAdapter(String[] images)
    {
        mimages=images;
    }
    @NonNull
    @Override
    public ViewHolderThirdParty onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_thirdparty,parent,false);
        ViewHolderThirdParty viewHolderThirdParty=new ViewHolderThirdParty(view);
        return viewHolderThirdParty;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderThirdParty viewHolderThirdParty=(ViewHolderThirdParty) holder;

        ImageLoader imageLoader= ImageLoader.getInstance();


            imageLoader.displayImage(mimages[position],viewHolderThirdParty.imageView);

            /*imageLoader.loadImage(imageUri, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
            viewHolderThirdParty.imageView.setImageResource(imageUri);*/



    }

    @Override
    public int getItemCount() {
        return mimages.length;
    }

    public class ViewHolderThirdParty extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolderThirdParty(View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imageView_Third_Party);

        }
    }
}
