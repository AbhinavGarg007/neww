package com.example.emp354.linear.FragmentAssignment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.emp354.linear.R;

public class FragmentAssignmentSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assignment_signup);

        final RadioButton radiobutton_student = findViewById(R.id.radiobutton_student);
        final RadioButton radiobutton_employee = findViewById(R.id.radiobutton_employee);
        EditText edittext_name = findViewById(R.id.edittext_name);
        Button save_button=findViewById(R.id.save_button);


        final String name=edittext_name.getText().toString();

        radiobutton_student.setOnClickListener(new View.OnClickListener() {
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
        });
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();*/

               /* if(radiobutton_student.isChecked()) {
                    bundle.putString("name", name);
                }*/

                Intent j=new Intent();
                j.putExtra("result",name);
                setResult(Activity.RESULT_OK,j);
                finish();

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


    private void loadfragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_second, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }
}
