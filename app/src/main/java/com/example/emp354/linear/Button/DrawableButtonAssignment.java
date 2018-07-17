package com.example.emp354.linear.Button;

import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.EdittextAssignment;
import com.example.emp354.linear.R;

public class DrawableButtonAssignment extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawablebuttonassignment);

        Button left=findViewById(R.id.left_drawable_btn);
        Button right=findViewById(R.id.right_drawable_btn);

        left.setOnClickListener(this);
        right.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.right_drawable_btn:
                Intent i = new Intent(DrawableButtonAssignment.this, CompoundBtnAssignment.class);
                startActivity(i);
                break;

            case R.id.left_drawable_btn:
                Intent j=new Intent(DrawableButtonAssignment.this, EdittextAssignment.class);
                startActivity(j);
                break;

        }
    }
}
