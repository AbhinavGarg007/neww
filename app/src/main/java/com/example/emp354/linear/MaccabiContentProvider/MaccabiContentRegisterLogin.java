package com.example.emp354.linear.MaccabiContentProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiHomeActivity;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiRegisterLogin;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MailIdScreenMaccabi;
import com.example.emp354.linear.MaccabiContentSharedPreference;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

public class MaccabiContentRegisterLogin extends AppCompatActivity {

    Button btnSignup,btnLogin;
    LinearLayout layoutLogin,layoutSignup;
    EditText etMailId,etFirstName,etLastName,etPhoneNo,etSignupPassword,etLoginPassword;
    String mailId;
    android.support.v7.widget.Toolbar toolbar;
    MaccabiContentSharedPreference maccabiContentSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_content_register_login);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_maccabi_back);
        maccabiContentSharedPreference=MaccabiContentSharedPreference.getInstance(this);

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
        etMailId.setText(mailId);

        if(isValidMailId)
        {
            layoutSignup.setVisibility(View.GONE);
        }
        else
        {
            layoutLogin.setVisibility(View.GONE);
        }


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values=new ContentValues();


                String firstName=etFirstName.getText().toString();
                String lastName=etLastName.getText().toString();
                String phoneNo=etPhoneNo.getText().toString();
                String signUpPassword=etSignupPassword.getText().toString();


                values.put(MaccabiContentUserModel.COLUMN_MAIL_ID,mailId);
                values.put(MaccabiContentUserModel.COLUMN_FIRST_NAME,firstName);
                values.put(MaccabiContentUserModel.COLUMN_LAST_NAME,lastName);
                values.put(MaccabiContentUserModel.COLUMN_PHONE_NO,phoneNo);
                values.put(MaccabiContentUserModel.COLUMN_PASSWORD,signUpPassword);

                Uri uri=getContentResolver().insert(MaccabiContentProvider.CONTENT_URI,values);
                String id=uri.getLastPathSegment();
                Toast.makeText(getBaseContext(), uri.toString(),Toast.LENGTH_SHORT).show();
                maccabiContentSharedPreference.saveId(Integer.valueOf(id));


               Toast.makeText(MaccabiContentRegisterLogin.this, "Now you are a registered user.", Toast.LENGTH_SHORT).show();

                nextActivity();


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pswd="";
                int id=0;
                String loginPassword=etLoginPassword.getText().toString();
                /*long id=maccabiContentSharedPreference.fetchId();*/
                String[] projection={MaccabiContentUserModel.COLUMN_PASSWORD};
                Cursor cursor=getContentResolver().query(MaccabiContentProvider.CONTENT_URI,
                        projection,
                        MaccabiContentUserModel.COLUMN_MAIL_ID+"=?",
                        new String[]{mailId},
                        null);

                if(cursor.moveToFirst()) {
                   pswd = cursor.getString(cursor.getColumnIndex(MaccabiContentUserModel.COLUMN_PASSWORD));
                }
                cursor.close();
                if(loginPassword.equals(pswd))
                {
                    String[] projection1={MaccabiContentUserModel.COLUMN_ID};
                    Cursor cursor1=getContentResolver().query(MaccabiContentProvider.CONTENT_URI,
                            projection1,
                            MaccabiContentUserModel.COLUMN_MAIL_ID+"=?",
                            new String[]{mailId},
                            null);
                    if(cursor1.moveToFirst()  && cursor1 !=null)
                    {
                        id= cursor1.getInt(cursor1.getColumnIndex(MaccabiContentUserModel.COLUMN_ID));
                        Toast.makeText(MaccabiContentRegisterLogin.this, "Login", Toast.LENGTH_SHORT).show();
                        cursor1.close();
                    }
                    else
                    {
                        id=-1;
                        Toast.makeText(MaccabiContentRegisterLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }


                    maccabiContentSharedPreference.saveId(id);
                    nextActivity();
                }
                else
                {
                    Toast.makeText(MaccabiContentRegisterLogin.this, "You have entered wrong password.", Toast.LENGTH_SHORT).show();

                }
                /*long result= db.isPasswordCorrect(mailId,loginPassword);
                if(result==0)
                {
                    Toast.makeText(MaccabiContentRegisterLogin.this, "Please enter correct password.", Toast.LENGTH_SHORT).show();


                }
                else if(result==-1)
                {
                    Toast.makeText(MaccabiContentRegisterLogin.this, "Error in login", Toast.LENGTH_SHORT).show();
                }
                else
                { maccabiContentSharedPreference.saveId(result);
                    Toast.makeText(MaccabiContentRegisterLogin.this, "Login", Toast.LENGTH_SHORT).show();
                    nextActivity();
                }
*/
            }
        });

    }
    private void nextActivity()
    {
        Intent i=new Intent(MaccabiContentRegisterLogin.this,MaccabiContentHomeActivity.class);
        i.putExtra("mailId",mailId);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent j=new Intent(MaccabiContentRegisterLogin.this,MaccabiContentMailId.class);
                startActivity(j);
                finish();
        }
        return true;
    }
}
