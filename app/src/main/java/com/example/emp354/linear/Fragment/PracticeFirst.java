package com.example.emp354.linear.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.emp354.linear.Fragment3.FragmentThirdOne;
import com.example.emp354.linear.Fragment3.FragmentThirdTwo;
import com.example.emp354.linear.R;

public class PracticeFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicefirst);

        Button first_btn=findViewById(R.id.first_button);
        Button second_btn=findViewById(R.id.second_button);

        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfragment(new FirstFragment());
            }
        });
        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfragment(new SecondFragment());
            }
        });
    }
    private void loadfragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.commit();
    }
}
