package com.example.emp354.linear.Multithreading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class Multithreading extends AppCompatActivity {

    Handler handler = new Handler();
    Button btnClick, btnSubmit;
    EditText etTimer;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading);
        etTimer = findViewById(R.id.tv_timer);
        btnClick = findViewById(R.id.btn_click);
        tvDisplay = findViewById(R.id.tv_display);
        btnSubmit = findViewById(R.id.btn_submit);


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Multithreading.this, "You have clicked on click me button", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("You have to wait for " + etTimer.getText().toString() + " seconds.");
                handler.postDelayed(run, 1000);

            }
        });
    }




    Runnable run = new Runnable() {
        @Override
        public void run() {
            updateTime();
        }
    };


    public void updateTime()
    { String text=(""+(Integer.parseInt(etTimer.getText().toString())-1));
        etTimer.setText(text);
        if(Integer.parseInt(etTimer.getText().toString())==0)
        {
            btnClick.setVisibility(View.VISIBLE);
        }
        else {
            handler.postDelayed(run,1000);
        }
    }
}
