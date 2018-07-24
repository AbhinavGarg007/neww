package com.example.emp354.linear.Toast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.emp354.linear.R;
import com.example.emp354.linear.SnackbarAssignment;

public class CustomToastAssignment extends AppCompatActivity {
    private Button btnDefaultToast,btnCustomToast,back,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customtoastassignment);

        btnDefaultToast = findViewById(R.id.btnDefaultToast);
        btnCustomToast = findViewById(R.id.btnCustomToast);
        back=findViewById(R.id.back);
        next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CustomToastAssignment.this,ToastMessageAssignment1.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(CustomToastAssignment.this, SnackbarAssignment.class);
                startActivity(j);
            }
        });


    }

    public void onClick (View view){
        if (view.equals(btnDefaultToast)) {
            Toast.makeText(CustomToastAssignment.this, "This is default toast message", Toast.LENGTH_SHORT).show();
        } else if (view.equals(btnCustomToast)) {
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.content_toast, (ViewGroup) findViewById(R.id.llcustom));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();
        }
    }}

