package com.example.emp354.linear.ThreadingAssignments.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

import com.example.emp354.linear.ThreadingAssignments.POJO.Results;
import com.example.emp354.linear.ThreadingAssignments.POJO_Service.MyObject_1;

import java.util.List;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.ViewHolder>{

    List<Results> resultsList;
    Context context;

    public MyServiceAdapter(MyObject_1 object, Context context)
    {
        if(object!=null)
        {
            resultsList=object.getResults();
        }

        this.context=context;
    }
    @NonNull
    @Override
    public MyServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemlist_threading,parent,false);
        MyServiceAdapter.ViewHolder viewHolder=new MyServiceAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyServiceAdapter.ViewHolder holder, int position) {

        Results results=resultsList.get(position);

        holder.tv_name.setText(results.getName());
        holder.tv_vicinity.setText(results.getVicinity());

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
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


