package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignin,btnRegister;
    String name;
    VshopSharedPreference vshopSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vshopSharedPreference=VshopSharedPreference.getInstance(this);

        //checking whether the sharedpreference contains any id or not
        if(vshopSharedPreference.fetchid()!=-1)
        {
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        //setting layout and initialising member variables
        setContentView(R.layout.activity_main);
        btnSignin=findViewById(R.id.btn_main_signin);
        btnRegister=findViewById(R.id.btn_main_register);

        //setting onClickListener
        btnSignin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_main_signin:
                name=getResources().getString(R.string.sign_in);
                nextactivity(name);
                break;

            case R.id.btn_main_register:
                name=getResources().getString(R.string.register);
                nextactivity(name);
                break;
        }

    }

    //method to go on next activity
    private void nextactivity(String fragmentName)
    {
        Intent intent=new Intent(MainActivity.this,SigninRegisterActivity.class);
        intent.putExtra("fragmentName",fragmentName);
        startActivity(intent);
    }

}
