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
import android.widget.Toast;

import com.example.emp354.linear.R;

public class MaccabiEditProfileFragment extends Fragment {

    EditText etMailId,etFirstName,etLastName,etPhoneNo;
    Button btnUpdate;
    MaccabiDataBaseHelper db;
    String mailId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_edit_profile,container,false);
                return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etMailId=view.findViewById(R.id.et_mail_id);
        etFirstName=view.findViewById(R.id.et_first_name);
        etLastName=view.findViewById(R.id.et_last_name);
        etPhoneNo=view.findViewById(R.id.et_phone_no);
        btnUpdate=view.findViewById(R.id.btn_update);

        Bundle bundle=this.getArguments();
        mailId=bundle.getString("mailId");

        db=new MaccabiDataBaseHelper(getActivity());
        db.getReadableDatabase();
        String[] data=db.getUserData(mailId);
        etMailId.setText(mailId);
        etFirstName.setText(data[0]);
        etLastName.setText(data[1]);
        etPhoneNo.setText(data[2]);
        db.close();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getWritableDatabase();
                String firstName=etFirstName.getText().toString();
                String lastName=etLastName.getText().toString();
                int phoneNo=Integer.valueOf(etPhoneNo.getText().toString());
                db.updateUser(mailId,firstName,lastName,phoneNo);
                Toast.makeText(getActivity(),"Your profile updated",Toast.LENGTH_SHORT).show();




            }
        });


    }
}
