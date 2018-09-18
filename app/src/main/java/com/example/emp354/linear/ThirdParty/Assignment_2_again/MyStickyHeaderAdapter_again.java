package com.example.emp354.linear.ThirdParty.Assignment_2_again;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.POJO.Images;
import com.example.emp354.linear.ThirdParty.POJO.ObjectPojoThirdParty;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

public class MyStickyHeaderAdapter_again extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    List<String> mTitleList;
    List<String> mUrlList;


    //constructor of sticky header adapter
    public MyStickyHeaderAdapter_again(Context context, List<String> titleList,List<String> urlList)
    {
        mcontext=context;
        mTitleList=titleList;
        mUrlList=urlList;

    }


    //inflating layout for imageview
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_thirdparty_2,parent,false);
        return new ImageViewHolder(view){};

    }


    //binding the imageview layout with the image
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*ImageView imageView=(ImageView)holder.itemView;*/
        ImageViewHolder imageViewHolder=(ImageViewHolder)holder;
        ImageLoader imageLoader=ImageLoader.getInstance();

        imageLoader.displayImage(mUrlList.get(position),imageViewHolder.imageView_2);

    }

    //returns a unique id for grouping header with images
    @Override
    public long getHeaderId(int position) {
        int length=mTitleList.get(position).length();
        return mTitleList.get(position).charAt(length-2);


    }

    //inflating layout for header
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.header_third_party,parent,false);
        return new TextViewHolder(view);

    }

    //binding header data with header
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*TextView textView=(TextView)holder.itemView;*/
        TextViewHolder textViewHolder = (TextViewHolder) holder;


            textViewHolder.textView_2.setText(mTitleList.get(position));
        }


        //getting the count of recyclerview item
    @Override
    public int getItemCount() {
        return mUrlList.size();
    }



    //viewholder for recyclerview item
    public class ImageViewHolder extends RecyclerView.ViewHolder
    { ImageView imageView_2;
    public ImageViewHolder(View itemView) {
            super(itemView);
            imageView_2=itemView.findViewById(R.id.imageView_third_party_2);
        }
    }


    //viewholder for header
    public class TextViewHolder extends RecyclerView.ViewHolder
    {TextView textView_2;
    public TextViewHolder(View itemView) {
            super(itemView);
            textView_2=itemView.findViewById(R.id.textView_third_party);
        }
    }
}
