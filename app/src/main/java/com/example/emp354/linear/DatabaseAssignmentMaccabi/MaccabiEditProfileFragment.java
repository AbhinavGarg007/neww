package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

public class MaccabiEditProfileFragment extends Fragment {

    EditText etMailId,etFirstName,etLastName,etPhoneNo;
    TextView tv_dob,tv_age;
    Button btnUpdate;
    MaccabiDataBaseHelper db;
    MySharedPreferences mySharedPreferences;
    String mailId,dob,age;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_edit_profile,container,false);
        ((MaccabiHomeActivity)getActivity()).checkFragment();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etMailId=view.findViewById(R.id.et_mail_id);
        etFirstName=view.findViewById(R.id.et_first_name);
        etLastName=view.findViewById(R.id.et_last_name);
        etPhoneNo=view.findViewById(R.id.et_phone_no);
        btnUpdate=view.findViewById(R.id.btn_update);
        tv_dob=view.findViewById(R.id.tv_dob);
        tv_age=view.findViewById(R.id.tv_age);
        db=new MaccabiDataBaseHelper(getActivity());
        mySharedPreferences=MySharedPreferences.getInstance(getContext());

        if(mySharedPreferences.fetchId()==-1)
        {
            Bundle bundle = this.getArguments();
            mailId = bundle.getString("mailId");

        }
        else{
            long id=mySharedPreferences.fetchId();
            mailId=db.getMailId(id);

        }




        db.getReadableDatabase();
       /* Bundle bundle=this.getArguments();
        dob=bundle.getString("dob");
        age=bundle.getString("age");
*/
        String[] data=db.getUserData(mailId);
        etMailId.setText(mailId);
        etFirstName.setText(data[0]);
        etLastName.setText(data[1]);
        etPhoneNo.setText(data[2]);
        /*tv_dob.setText(dob);
        tv_age.setText(age);
*/

        db.close();




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getWritableDatabase();
                String firstName=etFirstName.getText().toString();
                String lastName=etLastName.getText().toString();
                int phoneNo=Integer.valueOf(etPhoneNo.getText().toString());
                dob=tv_dob.getText().toString();
                age=tv_age.getText().toString();

                db.updateUser(mailId,firstName,lastName,phoneNo,dob,age);
                Toast.makeText(getActivity(),"Your profile updated",Toast.LENGTH_SHORT).show();




            }
        });


    }
}
