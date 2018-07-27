package com.example.emp354.linear.FragmentAssignment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emp354.linear.R;

public class FragmentAssignmentMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assignment_main);


       /* FrameLayout framelayout_1=findViewById(R.id.framelayout_1);
        FrameLayout framelayout_2=findViewById(R.id.framelayout_2);*/

        /*ImageView imageview_add=findViewById(R.id.imageview_add);*/
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadfragment1(new FragmentAssignmentContentList());
        loadfragment2(new FragmentAssignmentDescriptionList());



        /*imageview_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FragmentAssignmentMain.this,FragmentAssignmentSignup.class);
                startActivity(i);
            }
        });*/
    }
    private void loadfragment1(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.framelayout_1,fragment);
        ft.commit();
    }
    private void loadfragment2(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.framelayout_2,fragment);
        ft.commit();
    }


}
