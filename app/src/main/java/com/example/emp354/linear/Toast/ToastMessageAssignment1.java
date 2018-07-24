package com.example.emp354.linear.Toast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.emp354.linear.ActionBar.ToolbarAssignment;
import com.example.emp354.linear.AdapterView.CustomAdapter_recycler;
import com.example.emp354.linear.R;

public class ToastMessageAssignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toastmessageassignment1);

        Button back=findViewById(R.id.back);
        Button next=findViewById( R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i=new Intent(ToastMessageAssignment1.this, ToolbarAssignment.class);
                                        startActivity(i);
                                    }
                                });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(ToastMessageAssignment1.this, CustomToastAssignment.class);
                startActivity(j);
            }
        });

                Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        toast.setGravity(100,0,250);





    }
}
