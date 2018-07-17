package com.example.emp354.linear.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emp354.linear.EdittextAssignment;
import com.example.emp354.linear.R;

public class TextviewAssignment2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textviewassignment2);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);

        String s1 = tv1.getText().toString();
        Integer n = s1.length();

        tv2.setText(String.format(getString(R.string.length_format), n.toString()));


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_1:
                Intent i=new Intent(TextviewAssignment2.this,TextviewAssignment1.class);
                startActivity(i);
                break;
            case R.id.btn_2:
                Intent j=new Intent(TextviewAssignment2.this, EdittextAssignment.class);
                startActivity(j);
                break;
        }

    }




}
