package com.example.emp354.linear.FragmentAssignment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.emp354.linear.R;

public class FragmentAssignmentSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assignment_signup);

        final RadioButton radiobutton_student = findViewById(R.id.radiobutton_student);
        final RadioButton radiobutton_employee = findViewById(R.id.radiobutton_employee);
        final EditText edittext_name = findViewById(R.id.edittext_name);
        final EditText edittext_mail=findViewById(R.id.edittext_mail);
        final RadioGroup radiogroup_gender=findViewById(R.id.radiogroup_gender);
        final RadioGroup radiogroup_profession=findViewById(R.id.radiogroup_profession);


        Button save_button=findViewById(R.id.save_button);
        final LinearLayout layout_student=findViewById(R.id.layout_student);

       /* radiobutton_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_student.isChecked()) {

                    loadfragment(new FragmentAssignmentInfo2());
                }
            }
        });
        radiobutton_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_employee.isChecked()) {

                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(FragmentAssignmentInfo2.class.getSimpleName());
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        });*/

        radiobutton_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_student.isChecked()) {
                  layout_student.setVisibility(View.VISIBLE);
                }
            }
        });
        radiobutton_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_employee.isChecked()) {
                    layout_student.setVisibility(View.GONE);
                    }
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();*/

                String name=edittext_name.getText().toString();
                String type=((RadioButton)findViewById(radiogroup_profession.getCheckedRadioButtonId())).getText().toString();
                String mail=edittext_mail.getText().toString();
                String gender=((RadioButton)findViewById(radiogroup_gender.getCheckedRadioButtonId())).getText().toString();
                if(radiobutton_student.isChecked())
                {
                    Intent j=new Intent();
                /*j.putExtra("sname",name);
                j.putExtra("type",type);
                j.putExtra("mail",mail);
                j.putExtra("gender",gender);*/
                    Bundle bundle_1=new Bundle();
                bundle_1.putString("sname",name);
                bundle_1.putString("type",type);
                bundle_1.putString("mail",mail);
                bundle_1.putString("gender",gender);
                j.putExtras(bundle_1);

                setResult(Activity.RESULT_OK,j);
                finish();
                }
                else
                {
                    Intent j=new Intent();

                    /*j.putExtra("ename",name);
                    j.putExtra("type",type);*/
                    Bundle bundle_1=new Bundle();
                    bundle_1.putString("ename",name);
                    bundle_1.putString("type",type);
                   /* FragmentAssignmentContentList myfragment=new FragmentAssignmentContentList();
                    myfragment.setArguments(bundle);*/
                   j.putExtras(bundle_1);
                    setResult(Activity.RESULT_OK,j);
                    finish();
                }

              /* Bundle bundle = new Bundle();
                bundle.putString("name", name);
                // set Fragmentclass Arguments
                FragmentAssignmentContentList fragobj = new FragmentAssignmentContentList();
                fragobj.setArguments(bundle);*/
                /*ft.replace(R.id.framelayout_1,fragobj);
                ft.commit();*/
            }
        });




    }


   /* private void loadfragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_second, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }*/
}
