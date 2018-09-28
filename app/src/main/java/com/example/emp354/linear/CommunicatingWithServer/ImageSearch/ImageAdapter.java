package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.ImageModel;
import com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO.Items;
import com.example.emp354.linear.Listener.ItemClickListener;
import com.example.emp354.linear.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
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
        String url=itemsList.get(position).getImage().getThumbnailLink();

        //checking whether image is alredy present in internal storage
        File dir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "search_images");
        String fileName = url.substring(url.length() - 10);
        File file = new File(dir, fileName + ".jpg");
        if (file.exists() && file.isFile())
        {
            String path=file.getAbsolutePath();
            if(path!=null)
            { //returning image from internal storage
                Bitmap b=BitmapFactory.decodeFile(path);
                holder1.ivImage.setImageBitmap(b);
                Log.d("ImageSearch","Image is returned from internal storage");
            }

            //setting visibility of download button gone
            holder1.imageButton.setVisibility(View.GONE);
        }
        //if not present then download that image
        else {

            ImageLoader imageLoader = ImageLoader.getInstance();
            Log.d("ImageSearch","Image is fetched from cloud storage.");
            imageLoader.displayImage(url, holder1.ivImage);
        }



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImage;
        FrameLayout frameLayout;
        ImageButton imageButton;


        public ImageHolder(View itemView) {
            super(itemView);
            ivImage=itemView.findViewById(R.id.iv_result_image);
            frameLayout=itemView.findViewById(R.id.layout_image_search);
            imageButton=itemView.findViewById(R.id.ib_download);
            frameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           mItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
