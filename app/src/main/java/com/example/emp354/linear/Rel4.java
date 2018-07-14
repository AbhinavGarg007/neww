package com.example.emp354.linear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rel4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel4);

        Button apple=findViewById(R.id.apple);
        Button grapes=findViewById(R.id.grapes);

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Rel4.this,Rel3.class);
                startActivity(i);
            }
        });
        grapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Rel4.this,Scroll.class);
                startActivity(i);
            }
        });
    }
}
