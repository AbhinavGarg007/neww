package com.example.emp354.linear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.emp354.linear.Button.CompoundBtnAssignment;

public class ImageViewAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewassignment);

        ImageView image_centercrop=findViewById(R.id.imageview_centercrop);
        ImageView image_matrix=findViewById(R.id.imageview_matrix);

        image_centercrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ImageViewAssignment.this, CompoundBtnAssignment.class);
                startActivity(i);
            }
        });
        image_matrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(ImageViewAssignment.this,DrawablesFigureAssignment.class);
                startActivity(j);
            }
        });
    }


}
