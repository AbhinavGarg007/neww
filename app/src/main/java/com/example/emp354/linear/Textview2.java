package com.example.emp354.linear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Textview2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview2);

        TextView tv1=findViewById(R.id.tv1);
        TextView tv2=findViewById(R.id.tv2);

        String s1=tv1.getText().toString();
        Integer n=s1.length();

        String s2=n.toString();

        tv2.setText("Length is " + s2);
    }
}
