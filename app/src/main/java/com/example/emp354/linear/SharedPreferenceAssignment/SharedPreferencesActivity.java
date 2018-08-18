package com.example.emp354.linear.SharedPreferenceAssignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.EditTextValidation;
import com.example.emp354.linear.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    private Toolbar mTopToolbar;
    private boolean flag;

    EditText edittext_first_name, edittext_last_name, edittext_user_name, edittext_phone, edittext_mail, edittext_password, edittext_confirm_password, edittext_reference_code;

    public static final String MyPREFERENCES = "MyPreferences";
    public static final String FirstName = "firstNameKey";
    public static final String LastName = "lastNameKey";
    public static final String UserName = "userNameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String ConfirmPassword = "confirmPasswordKey";
    public static final String ReferenceCode = "referenceCodeKey";

    String s_fname, s_lname, s_uname, s_phone, s_email, s_password, s_confirm_password, s_referral_code;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences_activity);


        Button signup_btn = findViewById(R.id.signup_btn);
        edittext_first_name = findViewById(R.id.edittext_first_name);
        edittext_last_name = findViewById(R.id.edittext_last_name);
        edittext_user_name = findViewById(R.id.edittext_user_name);
        edittext_phone = findViewById(R.id.edittext_phone);
        edittext_mail = findViewById(R.id.edittext_email);
        edittext_password = findViewById(R.id.edittext_password);
        edittext_confirm_password = findViewById(R.id.edittext_confirm_password);
        edittext_reference_code = findViewById(R.id.edittext_optional_code);


        TextView signup_policy_textview = findViewById(R.id.signup_policy_textview);
        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final SpannableString text = new SpannableString("By signing up you agree to our\nPrivacy Policy & Terms of Service");
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 31, 44, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_purple)), 48, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup_policy_textview.setText(text);

        loaddata();
        update_data();




        signup_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String first_name = edittext_first_name.getText().toString();
                String last_name = edittext_last_name.getText().toString();
                String user_name = edittext_user_name.getText().toString();
                String phone = edittext_phone.getText().toString();
                String email = edittext_mail.getText().toString();
                String password = edittext_password.getText().toString();
                String confirm_password = edittext_confirm_password.getText().toString();
                String reference_code = edittext_reference_code.getText().toString();


                if (validateFormData( first_name,last_name,user_name,phone,email, password,confirm_password,reference_code)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(FirstName, first_name);
                    editor.putString(LastName, last_name);
                    editor.putString(UserName, user_name);
                    editor.putString(Phone, phone);
                    editor.putString(Email, email);
                    editor.putString(Password, password);
                    editor.putString(ConfirmPassword, confirm_password);
                    editor.putString(ReferenceCode, reference_code);
                    editor.commit();


                    Toast.makeText(SharedPreferencesActivity.this, "All enteries are valid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loaddata() {
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        s_fname = sharedPreferences.getString(FirstName, "");
        s_lname = sharedPreferences.getString(LastName, "");
        s_uname = sharedPreferences.getString(UserName, "");
        s_phone = sharedPreferences.getString(Phone, "");
        s_email = sharedPreferences.getString(Email, "");
        s_password = sharedPreferences.getString(Password, "");
        s_confirm_password = sharedPreferences.getString(ConfirmPassword, "");
        s_referral_code = sharedPreferences.getString(ReferenceCode, "");

    }

    public void update_data() {

        edittext_first_name.setText(s_fname);
        edittext_last_name.setText(s_lname);
        edittext_user_name.setText(s_uname);
        edittext_phone.setText(s_phone);
        edittext_mail.setText(s_email);
        edittext_password.setText(s_password);
        edittext_confirm_password.setText(s_confirm_password);
        edittext_reference_code.setText(s_referral_code);
    }

    private boolean validateFormData(String first_name,String last_name,String user_name,String phone,String email,  String password,String confirm_password,String reference_code) {


        if (!EditTextValidation.isValidName(first_name)) {
            edittext_first_name.setError("First letter must be capital.");
            return false;

        }
        if (!EditTextValidation.isValidName(last_name)) {
            edittext_last_name.setError("First letter must be capital.");
            return false;
        }

        if (!EditTextValidation.isValidUserName(user_name)) {
            edittext_user_name.setError("Must contain atleast 4 characters followed by atleast 2 digits.");
            return false;
        }


        if (!EditTextValidation.isValidPhoneNumber(phone)) {
            edittext_phone.setError("Must contain 10 digits.");
            return false;
        }

        if (!EditTextValidation.isValidEmail(email)) {
            edittext_mail.setError("Invalid Email");
            return false;
        }


        if (!EditTextValidation.isValidPassword(password)) {
            edittext_password.setError("Must contain atleast 6 or atmost 8 characters or digits.");
            return false;
        }


        if (!EditTextValidation.isValidPassword(confirm_password)) {
            edittext_confirm_password.setError("Rewrite Again");
            return false;
        } else if (!password.equals(confirm_password)) {
            edittext_confirm_password.setError("Rewrite Again");
            return false;
        }


        if (!EditTextValidation.isValidOptionalCode(reference_code)) {
            edittext_reference_code.setError("Invalid Optional Code");
            return false;
        }
        return true;
    }
}
