package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.emp354.linear.R;

public class FullViewActivity extends AppCompatActivity {
    ImageView ivFullScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        ivFullScreenView = findViewById(R.id.iv_full_view);

        Intent getIntent = getIntent();
        String getPath = getIntent.getStringExtra("path");

        if (getPath != null) { //returning image from internal storage
            Bitmap b = BitmapFactory.decodeFile(getPath);
            ivFullScreenView.setImageBitmap(b);
        }
    }
}
