package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

import java.util.ArrayList;
import java.util.List;

public class MaccabiRecyclerViewAdapter extends RecyclerView.Adapter{

    private Context mcontext;
    private ArrayList<MaccabiUserModel> mlist;

    public MaccabiRecyclerViewAdapter(Context context, ArrayList list)
    {
      mcontext=context;
      mlist=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maccabi_all_members_details_layout,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(mlist != null) {
            MaccabiUserModel maccabiUserModel=mlist.get(position);
            if(maccabiUserModel!= null) {


                MyViewHolder holder1 = (MyViewHolder) holder;
                holder1.tvMail.setText(maccabiUserModel.getEmailId());
                holder1.tvFirstName.setText(maccabiUserModel.getFirstName());
                holder1.tvLastName.setText(maccabiUserModel.getLastName());
                holder1.tvPhoneNo.setText(String.valueOf(maccabiUserModel.getPhoneNo()));
                holder1.tvUserAge.setText("3");
                holder1.tv_no_of_likes.setText("Total likes: 3" );
            }

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMail,tvFirstName,tvLastName,tvPhoneNo,tv_no_of_likes,tvUserAge;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvMail=itemView.findViewById(R.id.tv_mail_data);
            tvFirstName=itemView.findViewById(R.id.tv_first_name);
            tvLastName=itemView.findViewById(R.id.tv_last_name);
            tvPhoneNo=itemView.findViewById(R.id.tv_phone_no);
            tvUserAge=itemView.findViewById(R.id.tv_user_age);
            tv_no_of_likes=itemView.findViewById(R.id.tv_no_of_likes);
        }
    }
}
