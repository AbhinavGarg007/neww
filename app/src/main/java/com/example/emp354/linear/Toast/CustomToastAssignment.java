package com.example.emp354.linear.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class CustomToastAssignment extends AppCompatActivity {
    private Button btnDefaultToast,btnCustomToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customtoastassignment);

        btnDefaultToast = findViewById(R.id.btnDefaultToast);
        btnCustomToast = findViewById(R.id.btnCustomToast);


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

