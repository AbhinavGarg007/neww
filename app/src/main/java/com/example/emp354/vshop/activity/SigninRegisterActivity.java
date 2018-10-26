package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.fragment.RegisterFragment;
import com.example.emp354.vshop.fragment.SigninFragment;

public class SigninRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout frameLayout;
    String fragmentName,btnName;
    Button btnSignIn,btnRegister;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_register);
        frameLayout=findViewById(R.id.layout_frame_signin_register);
        btnRegister=findViewById(R.id.btn_register);
        btnSignIn=findViewById(R.id.btn_signin);

        btnSignIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


        //checking which fragment to load and then loading fragment
        Intent intent=getIntent();
        fragmentName=intent.getStringExtra("fragmentName");
        if(fragmentName.equals(getResources().getString(R.string.sign_in)))
        { fragment=new SigninFragment(); }
        else
        { fragment=new RegisterFragment(); }
        loadFirstFragment(fragment);
        setButtonBackground(fragment);

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_signin:
                fragment=new SigninFragment();
               break;

            case R.id.btn_register:
                fragment=new RegisterFragment();
                break;
        }
        loadFragment(fragment);
        setButtonBackground(fragment);
    }

    //method to set button background
    private void setButtonBackground(Fragment fragment)
    {
        if(fragment instanceof SigninFragment)
        {
            btnSignIn.setBackground(getResources().getDrawable(R.drawable.drawable_btn_bg));
            btnRegister.setBackground(getResources().getDrawable(android.R.color.transparent));
        }
        else
        {
            btnRegister.setBackground(getResources().getDrawable(R.drawable.drawable_btn_bg));
            btnSignIn.setBackground(getResources().getDrawable(android.R.color.transparent));
        }
    }

    //method to load fragment
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment currentFragment = fragmentManager.findFragmentById(R.id.layout_frame_signin_register);
            String currentFragmentName = currentFragment.getClass().getName();

            if (!backStateName.equals(currentFragmentName)) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (fragment instanceof SigninFragment)
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                else {
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                }
                fragmentTransaction.replace(R.id.layout_frame_signin_register, fragment, "addFragment");
                fragmentTransaction.commit();
            }
        }
    }

    //method to load only first fragment
    public void loadFirstFragment(Fragment firstFragment)
    {
        String backStateName = firstFragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame_signin_register, firstFragment, "addFragment");
        /*if(firstFragment instanceof SigninFragment) {
            fragmentTransaction.addToBackStack(backStateName);
        }
        else
        {
            fragmentTransaction.addToBackStack(SigninFragment.class.getName());
            fragmentTransaction.addToBackStack(RegisterFragment.class.getName());
        }*/
        fragmentTransaction.commit();
    }

    /*private void loadRegisterFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.layout_frame_signin_register, new RegisterFragment(), "addFragment");
        fragmentTransaction.commit();
    }*/

    //method to load fragment
   /* public void loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {//to get the name of fragment
            String backStateName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment currentFragment = fragmentManager.findFragmentById(R.id.layout_frame_signin_register);
            String currentFragmentName = currentFragment.getClass().getName();
            //to check whether the loaded fragment and going to load fragment is same or not
            if (!backStateName.equals(currentFragmentName))
            {
                //frgament is not same then pop up the fragment from backstack
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
                if (!fragmentPopped) {

                    //if fragment is not popped up then replace it.
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.replace(R.id.layout_frame_signin_register, fragment, "addFragment");
                    fragmentTransaction.addToBackStack(backStateName);
                    fragmentTransaction.commit();
                }
            }
        }
    }*/



}