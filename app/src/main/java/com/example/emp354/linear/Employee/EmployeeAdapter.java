package com.example.emp354.linear.Employee;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

import java.net.PortUnreachableException;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    private Context context;
    private List<EmployeeModelClass> employeeModelClassList;

    public EmployeeAdapter(Context context,List<EmployeeModelClass> employeeModelClassList)
    {
        this.context=context;
        this.employeeModelClassList=employeeModelClassList;
    }



    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {

        EmployeeModelClass employeeModelClass=employeeModelClassList.get(position);
        holder.emp_id.setText(Html.fromHtml("&#8226;"));
        holder.emp_name.setText(employeeModelClass.getName());
        holder.emp_age.setText(String.valueOf(employeeModelClass.getAge()));
        holder.emp_profile.setText(employeeModelClass.getProfile());
        holder.emp_salary.setText(String.valueOf(employeeModelClass.getSalary()));
        holder.emp_address.setText(employeeModelClass.getAddress());
    }

    @Override
    public int getItemCount() {
        return employeeModelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView emp_id;
        public TextView emp_name;
        public TextView emp_age;
        public TextView emp_profile;
        public TextView emp_salary;
        public TextView emp_address;



        public MyViewHolder(View itemView) {
            super(itemView);
            emp_id=itemView.findViewById(R.id.emp_id);
            emp_name=itemView.findViewById(R.id.emp_name);
            emp_age=itemView.findViewById(R.id.emp_age);
            emp_profile=itemView.findViewById(R.id.emp_profile);
            emp_salary=itemView.findViewById(R.id.emp_salary);
            emp_address=itemView.findViewById(R.id.emp_address);
        }
    }
}
