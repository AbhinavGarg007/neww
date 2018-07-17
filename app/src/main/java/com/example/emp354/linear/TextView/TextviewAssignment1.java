package com.example.emp354.linear.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.emp354.linear.R;
import com.example.emp354.linear.Scrollbar_Seekbar.SeekbarAssignment;

public class TextviewAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textviewassignment1);
    }
    public void next_activity(View view)
    {
        Intent i=new Intent(TextviewAssignment1.this,TextviewAssignment2.class);
        startActivity(i);
    }
    public void back_activity(View view)
    {
        Intent i=new Intent(TextviewAssignment1.this,SeekbarAssignment.class);
        startActivity(i);
    }
}
