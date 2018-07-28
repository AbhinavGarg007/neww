package com.example.emp354.linear.FragmentAssignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.emp354.linear.R;

public class FragmentAssignmentInfo2 extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_assignment_info_2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edittext_mail=view.findViewById(R.id.edittext_mail);
        String email=edittext_mail.getText().toString();

        RadioGroup radiogroup=view.findViewById(R.id.radiogroup);
        String gender=((RadioButton)view.findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();
    }
}
