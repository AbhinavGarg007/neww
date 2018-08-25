package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MaccabiDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    MaccabiEditProfileFragment maccabiEditProfileFragment;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        maccabiEditProfileFragment=new MaccabiEditProfileFragment();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String text = ("Date is : " + dayOfMonth + "-" + (month + 1) + "-" + year);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
         String age=calculateAge(year,month,dayOfMonth);

         maccabiEditProfileFragment.tv_age.setText(age);
         maccabiEditProfileFragment.tv_dob.setText(text);

    }
    private String calculateAge(int year,int month,int day)
    {
      Calendar dob=Calendar.getInstance();
      Calendar today=Calendar.getInstance();
      dob.set(year,month,day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
}
