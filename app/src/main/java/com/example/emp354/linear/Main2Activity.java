package com.example.emp354.linear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b5:
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.b6:
                Intent j = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(j);
                break;
        }
    }
}
