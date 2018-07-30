package com.example.emp354.linear.FragmentAssignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emp354.linear.AdapterView.CustomAdapter_recycler;
import com.example.emp354.linear.Model.User;
import com.example.emp354.linear.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterFragment extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<User> list;
    private Context mcontext;
    private onClickItem onClickItem;

    public CustomAdapterFragment(Context context,ArrayList<User> arrayList) {
        list=arrayList;
        mcontext=context;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /* View recycler_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview1, parent, false);
        //assign layout to holder
        MyViewHolder view_holder = new MyViewHolder(recycler_view);
        return view_holder;*/
       View recycler_view;

       if(viewType==0)
       {
           recycler_view=LayoutInflater.from(mcontext).inflate(R.layout.fragment_assignment_student_layout,parent,false);
          /* MyViewHolder viewHolder=new MyViewHolder(recycler_view);*/
           return new StudentHolder(recycler_view);
       }
       else if(viewType==1)
       {
           recycler_view=LayoutInflater.from(mcontext).inflate(R.layout.fragment_assignment_employee_layout,parent,false);
           /*MyViewHolder viewHolder=new MyViewHolder(recycler_view);*/
           return new EmployeeHolder(recycler_view);
       }
       return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /* MyViewHolder holder1=(MyViewHolder) holder;*/

        User user = list.get(position);
        if (user != null) {

            if (user.getType().equals("Student")) {
                ((StudentHolder) holder).textview_sname.setText(user.getName());
                ((StudentHolder) holder).textview_mail.setText(user.getMail());
                ((StudentHolder) holder).textview_gender.setText(user.getGender());
            }
            else if (user.getType().equals("Employee")) {
                ((EmployeeHolder) holder).textview_ename.setText(user.getName());
            }
        }
    }


    @Override
    public int getItemViewType(int position) {

        String Type=list.get(position).getType();
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
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView textview_sname;
        TextView textview_mail;
        TextView textview_gender;

        public StudentHolder(View itemView) {
            super(itemView);

// get the reference of item view's
                textview_sname = itemView.findViewById(R.id.textview_sname);
                textview_mail = itemView.findViewById(R.id.textview_mail);
                textview_gender = itemView.findViewById(R.id.textview_gender);


            // To make an entry of student clickable
             FrameLayout item_details=(FrameLayout) itemView.findViewById(R.id.student_frame);
            item_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickItem!=null)
                    {
                        onClickItem.onItemClick(getAdapterPosition());
                    }
                }
            });
            // To delete an entry of student
            ImageView deletestd=(ImageView)itemView.findViewById(R.id.imageview_close);
            deletestd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickItem!=null)
                    {
                        onClickItem.onDeleteClick(getAdapterPosition());
                    }
                }
            });
        }
            }
            public class EmployeeHolder extends RecyclerView.ViewHolder {
                TextView textview_ename;

                public EmployeeHolder(View itemView) {
                    super(itemView);
                    textview_ename = itemView.findViewById(R.id.textview_ename);

                    // To make an entry of student clickable
                    FrameLayout item_details = (FrameLayout) itemView.findViewById(R.id.employee_frame);
                    item_details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onClickItem != null) {
                                onClickItem.onItemClick(getAdapterPosition());
                            }
                        }
                    });
                    // To delete an entry of employee
                    ImageView deletestd = (ImageView) itemView.findViewById(R.id.imageview_close);
                    deletestd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onClickItem != null) {
                                onClickItem.onDeleteClick(getAdapterPosition());
                            }
                        }
                    });
                }
            }
                // To delete an entry and show details on right fragment
                public interface onClickItem {
                    void onItemClick(int position);

                    void onDeleteClick(int position);
                }




}
