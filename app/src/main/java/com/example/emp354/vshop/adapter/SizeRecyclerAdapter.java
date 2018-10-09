package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.listener.ItemClickListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SizeRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mSize;
    private ItemClickListener mItemClickListener;
    private List<Integer> mIsSizeSelected;
    private HashMap<String,String> mIsSizeAvailable;

    public SizeRecyclerAdapter(Context context,List<String> size,ItemClickListener itemClickListener,List<Integer> isSizeSelected,HashMap<String,String> isSizeAvailable)
    {
        mSize=size;
        mContext=context;
        mItemClickListener=itemClickListener;
        mIsSizeSelected=isSizeSelected;
        mIsSizeAvailable=isSizeAvailable;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_size,viewGroup,false);
        SizeViewHolder sizeViewHolder=new SizeViewHolder(view);
        return sizeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SizeViewHolder holder=(SizeViewHolder) viewHolder;

        holder.tvSize.setText(mSize.get(i));
        if(!mIsSizeAvailable.get(mSize.get(i)).equals("available"))
        {
           holder.tvSize.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if(mIsSizeSelected.contains(i)) {
            holder.tvSize.setBackground(mContext.getDrawable(R.drawable.size_selected_drawable));
            holder.tvSize.setTextColor(mContext.getResources().getColor(android.R.color.white));
        }
        else
        {
            holder.tvSize.setBackground(mContext.getDrawable(R.drawable.size_unselected_drawable));
            holder.tvSize.setTextColor(mContext.getResources().getColor(R.color.color_black));
        }

    }

    @Override
    public int getItemCount() {
        return mSize.size();
    }

    public class SizeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSize;
        public SizeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSize=itemView.findViewById(R.id.tv_size);
            tvSize.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
