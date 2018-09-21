package com.example.emp354.linear.RetrofitExample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter {

    private List<RetroPhotoModel> mdataList;
    private Context mcontext;


    public RetrofitAdapter(Context context,List<RetroPhotoModel> retroPhotoModelList)
    {
        mcontext=context;
        mdataList=retroPhotoModelList;
    }


    @NonNull
    @Override
    public RetrofitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.retrofit_item_layout,parent,false);
        return new RetrofitViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RetrofitViewHolder holder1=(RetrofitViewHolder) holder;

        holder1.tv_title.setText(mdataList.get(position).getTitle());

        Picasso.Builder builder=new Picasso.Builder(mcontext);
        builder.downloader(new OkHttp3Downloader(mcontext));
        builder.build().load(mdataList.get(position).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(((RetrofitViewHolder) holder).iv_coverImage);

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    public class RetrofitViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_coverImage;
        public RetrofitViewHolder(View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.title);
            iv_coverImage=itemView.findViewById(R.id.coverImage);
        }
    }
}
