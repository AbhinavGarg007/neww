package com.example.emp354.linear.Employee;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.emp354.linear.R;

import java.util.ArrayList;
import java.util.List;

public class Employee extends AppCompatActivity {

    private EmployeeAdapter employeeAdapter;
    private List<EmployeeModelClass> employeeModelClassList= new ArrayList<>();
    private FrameLayout layout_frame;
    private RecyclerView recyclerView;
    private TextView emptyEmployeeView;
    private DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        layout_frame=findViewById(R.id.layout_frame);
        recyclerView=findViewById(R.id.recycler_view);
        emptyEmployeeView=findViewById(R.id.empty_employee_view);

        db=new DataBaseHelper(this);
        employeeModelClassList.addAll(db.getAllEmployees());

        employeeAdapter=new EmployeeAdapter(this,employeeModelClassList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL,16));

        recyclerView.setAdapter(employeeAdapter);
        toggleEmptyEmployee();


        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmployeeDialog(false,null,-1);
            }
        });

        /**On long press on recyclerview item open alert dialog
         * with options
         * Edit and Delete
         */

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);

            }
        }));

    }

    /**Insert new node in db
     * and
     * refresh list
     */

    private void createEmployee(String name,int age,String profile,int salary,String address) {

        //inserting node in db and getting
        //newly inserted note id

        long id = db.insertEmployee(name,age,profile,salary,address);



        //get the newly inserted note from db
        EmployeeModelClass e = db.getEmployee(id);
        if (e != null)
        {
            //add new employee to array list at 0 position
            employeeModelClassList.add(0,e);

            //refresh the list
            employeeAdapter.notifyDataSetChanged();
            toggleEmptyEmployee();
        }
    }

    /**
     * Updating note in db
     * updating item in list
     * by its position
     */
    private void updateEmployee(String name,int age,String profile,int salary,String address,int position)
    {
        EmployeeModelClass e=employeeModelClassList.get(position);
        /*e.setId(id);*/
        e.setName(name);
        e.setAge(age);
        e.setProfile(profile);
        e.setSalary(salary);
        e.setAddress(address);

        //update employee in db
        db.updateEmployee(e);

        //refresh the list
        employeeModelClassList.set(position,e);
        employeeAdapter.notifyItemChanged(position);

        toggleEmptyEmployee();
    }

    /**
     * Delete node from sqlite
     * rempve item from list
     * by position
     */

    private void deleteEmployee(int position)
    {
        //delete employee from db
        db.deleteEmployee(employeeModelClassList.get(position));

        //remove employee from list
        employeeModelClassList.remove(position);
        employeeAdapter.notifyItemChanged(position);
        toggleEmptyEmployee();
    }

    /**
     * Open dialog with edit & delete options
     *
     */

    private void showActionsDialog(final int position)
    {
        CharSequence colors[]=new CharSequence[]{"Edit","Delete"};

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose Option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    showEmployeeDialog(true,employeeModelClassList.get(position),position);
                }else
                {
                    deleteEmployee(position);
                }
            }
        });
        builder.show();

    }

    /**
     * Show alert dialog with EditText options to enter/edit employee
     * when shouldUpdate=true,it automatically display old employee
     * and change the button text to update
     *
     */

    private void showEmployeeDialog(final boolean shouldUpdate,final EmployeeModelClass employeeModelClass ,final int position)
    {
        LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
        View view=inflater.inflate(R.layout.employee_dialog,null);

        AlertDialog.Builder userInput=new AlertDialog.Builder(Employee.this);
        userInput.setView(view);

      /*  final EditText et_id=view.findViewById(R.id.et_id);*/
        final EditText et_name=view.findViewById(R.id.et_name);
        final EditText et_age=view.findViewById(R.id.et_age);
        final EditText et_profile=view.findViewById(R.id.et_profile);
        final EditText et_salary=view.findViewById(R.id.et_salary);
        final EditText et_address=view.findViewById(R.id.et_address);
        /*final EditText et_id=view.findViewById(R.id.et_id)*/
        TextView dialog_title=view.findViewById(R.id.dialog_title);
        dialog_title.setText(!shouldUpdate ? "New Employee" : "Edit Employee");

        if(shouldUpdate && employeeModelClass != null)
        {
          /*  et_id.setText(employeeModelClass.getId());*/
            et_name.setText(employeeModelClass.getName());
            et_age.setText(employeeModelClass.getAge());
            et_profile.setText(employeeModelClass.getProfile());
            et_salary.setText(employeeModelClass.getSalary());
            et_address.setText(employeeModelClass.getAddress());
        }
        userInput.setCancelable(false)
                 .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 })
                 .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                     }
                 });

        final AlertDialog alertDialog=userInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show toast message when no text is entered
                if(TextUtils.isEmpty(et_name.getText().toString()) ||
                        TextUtils.isEmpty(et_age.getText().toString()) ||
                        TextUtils.isEmpty(et_profile.getText().toString()) ||
                        TextUtils.isEmpty(et_salary.getText().toString()) ||
                        TextUtils.isEmpty(et_address.getText().toString()))

                {
                    Toast.makeText(Employee.this,"Fill all details",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    alertDialog.dismiss();
                }

                //check if user updating employee
                if(shouldUpdate && employeeModelClass!=null)
                {
                    //update employeee by its id
                    updateEmployee(et_name.getText().toString(),Integer.valueOf(et_age.getText().toString()),et_profile.getText().toString(),
                            Integer.valueOf(et_salary.getText().toString()),et_address.getText().toString(),position);
                }
                else
                {
                    //create new employee
                    createEmployee(
                            et_name.getText().toString(),
                            Integer.valueOf(et_age.getText().toString()),
                            et_profile.getText().toString(),
                            Integer.valueOf(et_salary.getText().toString()),
                            et_address.getText().toString());
                }
            }
        });


    }
    private void toggleEmptyEmployee()
    {
        if(db.getEmployeeCount()>0)
        {
           emptyEmployeeView.setVisibility(View.GONE);
        }
        else
        {
            emptyEmployeeView.setVisibility(View.VISIBLE);
        }
    }
}
