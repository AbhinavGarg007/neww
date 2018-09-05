package com.example.emp354.linear.RoomExample;

import android.arch.persistence.room.Room;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFirstName,etLastName,etSalary,etAge,etPhoneNo;
    Button btn_submit,btn_find,btn_count,btn_delete;
    private AppDatabase appDatabase;
    String firstName,lastName,age,phoneNo,salary;
    RoomUserModel roomUserModel;

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        roomUserModel=new RoomUserModel();

        final String DATABASE_NAME = "user_db";

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        etFirstName=findViewById(R.id.et_first_name);
        etLastName=findViewById(R.id.et_last_name);
        etAge=findViewById(R.id.et_age);
        etPhoneNo=findViewById(R.id.et_phone_no);
        etSalary=findViewById(R.id.et_salary);
        btn_submit=findViewById(R.id.btn_submit);
        btn_count=findViewById(R.id.btn_count);
        btn_delete=findViewById(R.id.btn_delete);
        btn_find=findViewById(R.id.btn_find);




       /* addUser(db,roomUserModel);*/
       btn_submit.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_count.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_submit:
                firstName=etFirstName.getText().toString();
                lastName=etLastName.getText().toString();
                age=etAge.getText().toString();
                phoneNo=etPhoneNo.getText().toString();
                salary=etSalary.getText().toString();

                roomUserModel.setFirstName(firstName);
                roomUserModel.setLastName(lastName);
                roomUserModel.setAge(age);
                roomUserModel.setSalary(salary);
                roomUserModel.setPhoneNo(phoneNo);

                appDatabase.userDao().insertAll(roomUserModel);
                Toast.makeText(RoomActivity.this,"Data is submitted.",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_find:
                firstName=etFirstName.getText().toString();
                lastName=etLastName.getText().toString();
                roomUserModel=appDatabase.userDao().findByName(firstName,lastName);

                Toast.makeText(RoomActivity.this,roomUserModel.getFirstName()+" "+
                roomUserModel.getLastName()+" "+roomUserModel.getAge()+" "+roomUserModel.getSalary()+" "+
                roomUserModel.getPhoneNo(),Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_count:
                int count=appDatabase.userDao().countUsers();
                Toast.makeText(RoomActivity.this,"Total users are : "+count,Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_delete:
                appDatabase.userDao().delete(roomUserModel);
                Toast.makeText(RoomActivity.this,"Deleted the entries.",Toast.LENGTH_SHORT).show();
                break;


        }

    }
    /*private static RoomUserModel addUser(final AppDatabase db,RoomUserModel roomUserModel)
    {
        db.userDao().insertAll(roomUserModel);
        return roomUserModel;
    }*/
}
