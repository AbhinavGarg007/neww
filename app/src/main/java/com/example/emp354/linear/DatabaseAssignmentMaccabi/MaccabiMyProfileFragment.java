package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;
import com.example.emp354.linear.SaveSharedPreference;

public class MaccabiMyProfileFragment extends Fragment{

    MaccabiDataBaseHelper db;
    String mailId;
    MySharedPreferences mySharedPreferences;
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
        TextView tvLikes=view.findViewById(R.id.tv_myprofile_like);
        mySharedPreferences=MySharedPreferences.getInstance(getContext());
        int id=(int)mySharedPreferences.fetchId();
        db=new MaccabiDataBaseHelper(getActivity());

        int count=db.getLikeCount(id);

        if(mySharedPreferences.fetchId()==-1)
      {
          Bundle bundle = this.getArguments();
          mailId = bundle.getString("mailId");

        }

else {/*long id=MySharedPreferences.getInstance(getContext()).fetchId();*/

            /* mailId=mySharedPreferences.fetchMailId();*/
           /* mailId="a@a.a";*/
           Log.d("tag1","value of id"+id);
           mailId=db.getMailId(id);
            Log.d("tag2",mailId);
        }




        db.getReadableDatabase();
        String[] data=db.getUserData(mailId);
        tvMailData.setText(mailId);
        tvFirstNameData.setText(data[0]);
        tvLastNameData.setText(data[1]);
        tvPhoneNo.setText(data[2]);
        tvLikes.setText(String.valueOf(count));

        db.close();

    }
}
