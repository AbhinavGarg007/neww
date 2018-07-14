package com.example.emp354.linear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class Edittext extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);

        final EditText edittext_name=findViewById(R.id.edittext_name);
        final EditText edittext_pswd=findViewById(R.id.edittext_pswd);
        final EditText edittext_phone=findViewById(R.id.edittext_phone);
        final TextView textview_name=findViewById(R.id.textview_name);
        final TextView textview_pswd=findViewById(R.id.textview_pswd);
        final TextView textView_phone=findViewById(R.id.textview_phone);

        edittext_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String et_name=edittext_name.getText().toString();
                textview_name.setText("Input name is : " + et_name);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        edittext_pswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String et_pswd=edittext_pswd.getText().toString();
                textview_pswd.setText("Input password is : " + et_pswd);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edittext_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              String et_phone=edittext_phone.getText().toString();
              textView_phone.setText("Input phone is : " + et_phone);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });









    }
}
