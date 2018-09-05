package com.example.emp354.linear.MaccabiContentProvider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiRegisterLogin;
import com.example.emp354.linear.DatabaseAssignmentMaccabi.MailIdScreenMaccabi;
import com.example.emp354.linear.R;

public class MaccabiContentMailId extends AppCompatActivity {

    EditText et_mail;
    Button btn_next;
    String mail_text;
    boolean isValidMailId;
    MaccabiContentProvider maccabiContentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maccabi_content_mail_id);

        maccabiContentProvider=new MaccabiContentProvider();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_maccabi_back);

        et_mail=findViewById(R.id.et_mail);
        btn_next=findViewById(R.id.btn_next);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail_text=et_mail.getText().toString();
               /* boolean result=maccabiContentProvider.isMailIdExists(mail_text);*/
               String[] projection={MaccabiContentUserModel.COLUMN_MAIL_ID};
                Cursor cursor=getContentResolver().query(MaccabiContentProvider.CONTENT_URI,
                        projection,
                        MaccabiContentUserModel.COLUMN_MAIL_ID+"=?",
                        new String[]{mail_text},
                        null);
                int count=cursor.getCount();
                cursor.close();

                if(count<=0)
                {
                    isValidMailId=false;
                }  else {
                    isValidMailId = true;
                }
                nextActivity(isValidMailId);


            }
        });
    }
    private void nextActivity(boolean isValidMailId)
    {
        Intent i = new Intent(MaccabiContentMailId.this, MaccabiContentRegisterLogin.class);
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
