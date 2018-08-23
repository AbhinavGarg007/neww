package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.emp354.linear.Employee.DataBaseHelper;
import com.example.emp354.linear.R;

import java.util.ArrayList;
import java.util.List;

public class MaccabiRegisterLogin extends AppCompatActivity {
    Button btnSignup,btnLogin;
    LinearLayout layoutLogin,layoutSignup;
    EditText etMailId,etFirstName,etLastName,etPhoneNo,etSignupPassword,etLoginPassword;
    MaccabiDataBaseHelper db;
    String mailId;
    boolean isValidMailId;
    private List<MaccabiUserModel> maccabiUserModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_register_login);


        btnLogin=findViewById(R.id.btn_login);
        btnSignup=findViewById(R.id.btn_signup);
        layoutLogin=findViewById(R.id.layout_login);
        layoutSignup=findViewById(R.id.layout_signup);
        etMailId=findViewById(R.id.et_mail_id);
        etFirstName=findViewById(R.id.et_first_name);
        etLastName=findViewById(R.id.et_last_name);
        etPhoneNo=findViewById(R.id.et_phone_no);
        etLoginPassword=findViewById(R.id.et_login_password);
        etSignupPassword=findViewById(R.id.et_signup_password);

        Intent j=getIntent();
        boolean isValidMailId=j.getExtras().getBoolean("isValidMailId");
        mailId=j.getStringExtra("mailId");

        if(isValidMailId)
        {
            layoutSignup.setVisibility(View.GONE);
        }
        else
        {
            layoutLogin.setVisibility(View.GONE);
        }

        db=new MaccabiDataBaseHelper(this);
        maccabiUserModelList.addAll(db.getAllUser());

        etMailId.setText(mailId);






        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String firstName=etFirstName.getText().toString();
                String lastName=etLastName.getText().toString();
                int phoneNo=Integer.valueOf(etPhoneNo.getText().toString());
                String signUpPassword=etSignupPassword.getText().toString();
                createUser(mailId,firstName,lastName,phoneNo,signUpPassword);

                Toast.makeText(MaccabiRegisterLogin.this, "Now you are a registered user.", Toast.LENGTH_SHORT).show();

                nextActivity();


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginPassword=etLoginPassword.getText().toString();
                boolean result= db.isPasswordCorrect(mailId,loginPassword);
                if(result)
                {
                    nextActivity();
                }
                else
                    Toast.makeText(MaccabiRegisterLogin.this, "Please enter correct password.", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void createUser(String emailId,String firstName,String lastName,int phoneNo,String password)
    {
        long id = db.insertUser(emailId,firstName,lastName,phoneNo,password);
       /* MaccabiUserModel m=db.getUser(id);
        if(m !=null)
        {
            maccabiUserModelList.add(0,m);

        }*/
    }

    private void nextActivity()
    {
        Intent i=new Intent(MaccabiRegisterLogin.this,MaccabiHomeActivity.class);
        i.putExtra("mailId",mailId);
        startActivity(i);
        finish();
    }
}
