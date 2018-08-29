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

public class MaccabiLikeAdapter extends RecyclerView.Adapter{
    private Context mcontext;
    private ArrayList<MaccabiUserModel> mUserList;


    public MaccabiLikeAdapter(Context context, ArrayList list)
    {
        mcontext=context;
        mUserList=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maccabi_likes_fragment_layout,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (mUserList != null) {


            MaccabiUserModel maccabiUserModel = mUserList.get(position);
            if (maccabiUserModel != null) {

                final MaccabiLikeAdapter.MyViewHolder holder1 = (MaccabiLikeAdapter.MyViewHolder) holder;
                holder1.tv_mail_data.setText(maccabiUserModel.getEmailId());
                holder1.tv_first_name_data.setText(maccabiUserModel.getFirstName());
                holder1.tv_last_name_data.setText(maccabiUserModel.getLastName());
                holder1.tv_phone_no_data.setText(String.valueOf(maccabiUserModel.getPhoneNo()));
                /* likes =maccabiUserModel.getLikes();*/
                /* holder1.tv_no_of_likes.setText("Total likes: " + String.valueOf(likes));*/
            }

        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
   public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_mail_data,tv_first_name_data,tv_last_name_data,tv_phone_no_data;

       public MyViewHolder(View itemView) {
           super(itemView);
           tv_mail_data=itemView.findViewById(R.id.tv_mail_data);
           tv_first_name_data=itemView.findViewById(R.id.tv_first_name_data);
           tv_last_name_data=itemView.findViewById(R.id.tv_last_name_data);
           tv_phone_no_data=itemView.findViewById(R.id.tv_phone_no_data);


       }
   }
}
