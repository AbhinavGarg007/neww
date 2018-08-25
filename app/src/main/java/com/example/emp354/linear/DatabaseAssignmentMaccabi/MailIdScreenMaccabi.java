package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emp354.linear.R;

public class MailIdScreenMaccabi extends AppCompatActivity {

    EditText et_mail;
    Button btn_next;
    String mail_text;
    boolean isValidMailId;
    private MaccabiDataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_id_screen_maccabi);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_maccabi_back);

        et_mail=findViewById(R.id.et_mail);
        btn_next=findViewById(R.id.btn_next);
        db=new MaccabiDataBaseHelper(this);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail_text=et_mail.getText().toString();
                boolean result=db.isMailIdExists(mail_text);

                if(result)
                {
                    isValidMailId=result;
                }  else {
                    isValidMailId = false;
                    }

                    nextActivity(isValidMailId);


            }
        });
    }
    private void nextActivity(boolean isValidMailId)
    {
        Intent i = new Intent(MailIdScreenMaccabi.this, MaccabiRegisterLogin.class);
        i.putExtra("mailId",mail_text);
        i.putExtra("isValidMailId", isValidMailId);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;

    }
}
