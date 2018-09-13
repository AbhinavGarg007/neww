package com.example.emp354.linear.ThreadingAssignments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    List<ListItem> listItems;
    Context context;

    public MyAdapter(List<ListItem> listItems,Context context)
    {
        this.listItems=listItems;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemlist_threading,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        ListItem listItem=listItems.get(position);

        holder.tv_name.setText(listItem.getName());
        holder.tv_vicinity.setText(listItem.getAddress());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_vicinity;


        public ViewHolder(View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.tv_name);
            tv_vicinity=itemView.findViewById(R.id.tv_vicinity);


        }
    }
}
