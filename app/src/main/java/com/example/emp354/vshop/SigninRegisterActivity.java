package com.example.emp354.vshop;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.emp354.vshop.fragment.RegisterFragment;
import com.example.emp354.vshop.fragment.SigninFragment;

public class SigninRegisterActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    String fragmentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_register);
        frameLayout=findViewById(R.id.layout_fragment);

        //loading fragment
        Intent intent=getIntent();
        fragmentName=intent.getStringExtra("fragmentName");
        if(fragmentName.equals(getResources().getString(R.string.sign_in)))
            loadFragment(new SigninFragment());
        else
            loadFragment(new RegisterFragment());
    }

    //method to load fragment
    public void loadFragment(Fragment fragment)
    {
        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_fragment, fragment);
            fragmentTransaction.commit();
        }
    }
}
