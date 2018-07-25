package com.example.emp354.linear.Fragment3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.linear.R;

public class FragmentThirdMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentthirdmain);

        Button btn_1=findViewById(R.id.btn_1);
        Button btn_2=findViewById(R.id.btn_2);
        Button btn_3=findViewById(R.id.btn_3);
        Button btn_4=findViewById(R.id.btn_4);


        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadhalf1(new FragmentThirdOne());
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfull(new FragmentThirdOne());

            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadhalf2(new FragmentThirdOne());
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadfull(new FragmentThirdTwo());
            }
        });

    }

    private void loadhalf1(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.layout_first,fragment);
        ft.commit();
    }
    private void loadhalf2(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.layout_second,fragment);
        ft.commit();
    }
    private void loadfull(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.main_layout,fragment);
        ft.commit();
    }

}
