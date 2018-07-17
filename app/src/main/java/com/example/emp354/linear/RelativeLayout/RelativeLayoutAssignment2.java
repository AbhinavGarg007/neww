package com.example.emp354.linear.RelativeLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class RelativeLayoutAssignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayoutassignment2);

        Button btn4 = findViewById(R.id.b4);
        Button btn5 = findViewById(R.id.b5);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RelativeLayoutAssignment2.this, RelativeLayoutAssignment1.class);
                startActivity(i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(RelativeLayoutAssignment2.this, RelativeLayoutAssignment3.class);
                startActivity(j);
            }
        });
    }

}
