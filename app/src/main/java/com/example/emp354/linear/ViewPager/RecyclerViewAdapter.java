package com.example.emp354.linear.ViewPager;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private String mtitle;
    private String mtext;
    private int mimage;
    private String[] mdate;


    public RecyclerViewAdapter(String title,String text,int image,String[] date)
    {
        mtext=text;
        mimage=image;
        mtitle=title;
        mdate = date;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_content,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyViewHolder holder1=(MyViewHolder) holder;
        holder1.textview_title.setText(mtitle);
        holder1.textview_date.setText(mdate[position]);
        holder1.textview_like.setText("145 Likes");
        holder1.textview_share.setText("Share");
        holder1.textview_bookmark.setText(mtext);

        holder1.imageview_like.setBackgroundResource(R.drawable.ic_thumb_up_black_24dp);
        holder1.imageview_share.setBackgroundResource(R.drawable.ic_share_black_24dp);
        holder1.imageview_bookmark.setBackgroundResource(mimage);



    }

    @Override
    public int getItemCount() {
        return 5;
    }

   /* @Override
    public int getItemViewType(int position) {


    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title,textview_date,textview_like,textview_share,textview_bookmark;
        ImageView imageview_like,imageview_share,imageview_bookmark;
        public MyViewHolder(View itemView) {
            super(itemView);

            textview_title=itemView.findViewById(R.id.textview_title);
            textview_date=itemView.findViewById(R.id.textview_date);
            textview_like=itemView.findViewById(R.id.textview_like);
            textview_share=itemView.findViewById(R.id.textview_share);
            textview_bookmark=itemView.findViewById(R.id.textview_bookmark);

            imageview_like=itemView.findViewById(R.id.imageview_like);
            imageview_share=itemView.findViewById(R.id.imageview_share);
            imageview_bookmark=itemView.findViewById(R.id.imageview_bookmark);

        }
    }
}
