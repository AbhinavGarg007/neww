package com.example.emp354.linear.ContentProviderExample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.emp354.linear.FragmentAssignment.CustomAdapterFragment;
import com.example.emp354.linear.R;

public class ContentProviderActivity extends AppCompatActivity {
   EditText editText2,editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);

    }

    public void onClickAddName(View view)
    {
        ContentValues values=new ContentValues();
        values.put(StudentsProvider.NAME,editText2.getText().toString());
        values.put(StudentsProvider.GRADE,editText3.getText().toString());
        Uri uri=getContentResolver().insert(StudentsProvider.CONTENT_URI,values);
        Toast.makeText(getBaseContext(), uri.toString(),Toast.LENGTH_SHORT).show();

    }

    public void onClickUpdate(View view)
    {
        ContentValues values=new ContentValues();
        String name= editText2.getText().toString();
        String grade=editText3.getText().toString();

        values.put(StudentsProvider.NAME,name);
        values.put(StudentsProvider.GRADE,grade);
        int count=getContentResolver().update(StudentsProvider.CONTENT_URI,values,StudentsProvider.NAME+"=?",new String[]{name});
        Toast.makeText(getBaseContext(), "Rows"+count+"updated",Toast.LENGTH_SHORT).show();
    }
    public void onClickRetrieveStudents(View view) {
        String URL = "content://com.example.emp354.linear.ContentProviderExample";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID))+
                " , "+c.getString(c.getColumnIndex(StudentsProvider.NAME))+
                        " , "+c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                Toast.LENGTH_SHORT).show();
            }while (c.moveToNext());
        }
    }
}
