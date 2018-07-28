package com.example.emp354.linear.FragmentAssignment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.AdapterView.CustomAdapter_recycler;
import com.example.emp354.linear.Model.User;
import com.example.emp354.linear.R;

public class CustomAdapterFragment extends RecyclerView.Adapter{

    User user=new User();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /* View recycler_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview1, parent, false);
        //assign layout to holder
        MyViewHolder view_holder = new MyViewHolder(recycler_view);
        return view_holder;*/
       View recycler_view;

       if(viewType==0)
       {
           recycler_view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_assignment_student_layout,parent,false);
           MyViewHolder viewHolder=new MyViewHolder(recycler_view);
           return viewHolder;
       }
       else if(viewType==1)
       {
           recycler_view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_assignment_employee_layout,parent,false);
           MyViewHolder viewHolder=new MyViewHolder(recycler_view);
           return viewHolder;
       }
       return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1=(MyViewHolder) holder;
        if(user.getType().equals("Student"))
        {
            holder1.textview_name.setText(user.getName());
            holder1.textview_mail.setText(user.getMail());
            holder1.textview_gender.setText(user.getGender());
        }
        else if(user.getType().equals("Employee"))
        {
            holder1.textview_name.setText(user.getName());
        }
        }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView textview_name;
        TextView textview_mail;
        TextView textview_gender;

        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
                textview_name = itemView.findViewById(R.id.textview_name);
                textview_mail = itemView.findViewById(R.id.textview_mail);
                textview_gender = itemView.findViewById(R.id.textview_gender);

                }
            }

    @Override
    public int getItemViewType(int position) {

        String Type=user.getType();
        if(Type.equals("Student"))
        {
          return 0;
        }
        else if(Type.equals("Employee"))
        {
       return 1;
        }
        return -1;
    }
}
