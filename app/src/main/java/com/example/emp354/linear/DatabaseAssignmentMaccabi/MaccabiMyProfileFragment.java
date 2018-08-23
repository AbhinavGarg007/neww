package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class MaccabiMyProfileFragment extends Fragment{

    MaccabiDataBaseHelper db;
    String mailId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_my_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tvMailData=view.findViewById(R.id.tv_mail_data);
        TextView tvFirstNameData=view.findViewById(R.id.tv_first_name_data);
        TextView tvLastNameData=view.findViewById(R.id.tv_last_name_data);
        TextView tvPhoneNo=view.findViewById(R.id.tv_phone_no_data);

        Bundle bundle=this.getArguments();
        mailId=bundle.getString("mailId");


        db=new MaccabiDataBaseHelper(getActivity());
        db.getReadableDatabase();
        String[] data=db.getUserData(mailId);
        tvMailData.setText(mailId);
        tvFirstNameData.setText(data[0]);
        tvLastNameData.setText(data[1]);
        tvPhoneNo.setText(data[2]);

        db.close();

    }
}
