package com.example.emp354.linear.MaccabiContentProvider;

import android.content.ContentValues;
import android.content.Intent;
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
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

public class MaccabiContentRegisterLogin extends AppCompatActivity {

    Button btnSignup,btnLogin;
    LinearLayout layoutLogin,layoutSignup;
    EditText etMailId,etFirstName,etLastName,etPhoneNo,etSignupPassword,etLoginPassword;
    String mailId;
    android.support.v7.widget.Toolbar toolbar;
    MySharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_content_register_login);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_maccabi_back);
        mySharedPreferences=MySharedPreferences.getInstance(this);

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


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values=new ContentValues();


                String firstName=etFirstName.getText().toString();
                String lastName=etLastName.getText().toString();
                int phoneNo=Integer.valueOf(etPhoneNo.getText().toString());
                String signUpPassword=etSignupPassword.getText().toString();


                values.put(MaccabiContentProvider.COLUMN_MAIL_ID,mailId);
                values.put(MaccabiContentProvider.COLUMN_FIRST_NAME,firstName);
                values.put(MaccabiContentProvider.COLUMN_LAST_NAME,lastName);
                values.put(MaccabiContentProvider.COLUMN_PHONE_NO,phoneNo);
                values.put(MaccabiContentProvider.COLUMN_PASSWORD,signUpPassword);

                Uri uri=getContentResolver().insert(MaccabiContentProvider.CONTENT_URI,values);
                long id=uri.getLastPathSegment();
                Toast.makeText(getBaseContext(), uri.toString(),Toast.LENGTH_SHORT).show();
                mySharedPreferences.saveId(id);


               Toast.makeText(MaccabiContentRegisterLogin.this, "Now you are a registered user.", Toast.LENGTH_SHORT).show();

                nextActivity();


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
