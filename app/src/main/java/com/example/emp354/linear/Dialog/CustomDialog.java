package com.example.emp354.linear.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emp354.linear.R;

import java.util.concurrent.Callable;

public class CustomDialog extends android.support.v4.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.custom_dialog,null,false);
        final EditText et_mail=(EditText)v.findViewById(R.id.et_mail);
        final EditText et_pswd=v.findViewById(R.id.et_pswd);
        Button login_btn=v.findViewById(R.id.login_btn);
        Button cancel_btn=v.findViewById(R.id.cancel_btn);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());


        builder.setView(v);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_mail.getText().toString().isEmpty() && !et_pswd.getText().toString().isEmpty()) {


                    String text = ("Email : " + et_mail.getText().toString() + "\n" + "Password : " + et_pswd.getText().toString());
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getContext(), " Please enter \n     all the \n    required \n     entries. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.this.getDialog().cancel();
            }
        });




        return builder.create();
    }


}
