package com.example.emp354.linear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

    FrameLayout f1=findViewById(R.id.f1);
    FrameLayout f2=findViewById(R.id.f2);

    f1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Main5Activity.this,Main6Activity.class);
            startActivity(i);
        }
    });
    f2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Main5Activity.this,Main4Activity.class);
            startActivity(i);
        }
    });




    }

}
