package com.example.emp354.linear.Multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class Multithreading_4 extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading_4);
        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);


    }
   /* public void startTimer(View view)
    {
        final int time=Integer.valueOf(editText.getText().toString());

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<time;i++)
                {

                }

            }
        }
    }*/
}
