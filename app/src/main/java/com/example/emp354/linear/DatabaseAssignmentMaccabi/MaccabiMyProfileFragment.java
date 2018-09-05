package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.linear.MaccabiContentProvider.MaccabiContentHomeActivity;
import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;
import com.example.emp354.linear.SaveSharedPreference;

public class MaccabiMyProfileFragment extends Fragment implements View.OnClickListener{

    MaccabiDataBaseHelper db;
    String mailId;
    MySharedPreferences mySharedPreferences;
    Toolbar toolbar;
    MenuItem menuItem;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_my_profile,container,false);
        /*setHasOptionsMenu(true);*/
        ((MaccabiContentHomeActivity)getActivity()).checkFragment();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tvMailData=view.findViewById(R.id.tv_mail_data);
        TextView tvFirstNameData=view.findViewById(R.id.tv_first_name_data);
        TextView tvLastNameData=view.findViewById(R.id.tv_last_name_data);
        TextView tvPhoneNo=view.findViewById(R.id.tv_phone_no_data);
        TextView tvLikes=view.findViewById(R.id.tv_myprofile_like);
        TextView tvMyProfileLike=view.findViewById(R.id.tv_myprofile_like);

        tvMyProfileLike.setOnClickListener(this);
        /*toolbar = (Toolbar) getView().findViewById(R.id.toolbar);*/
       /* AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("My Profile");*/

        mySharedPreferences=MySharedPreferences.getInstance(getContext());
        int id=(int)mySharedPreferences.fetchId();
        db=new MaccabiDataBaseHelper(getActivity());
        fragmentManager=getFragmentManager();

        int count=db.getLikeCount(id);

        if(mySharedPreferences.fetchId()==-1)
      {
          Bundle bundle = this.getArguments();
          mailId = bundle.getString("mailId");

        }

else {/*long id=MySharedPreferences.getInstance(getContext()).fetchId();*/

            /* mailId=mySharedPreferences.fetchMailId();*/
           /* mailId="a@a.a";*/
           Log.d("tag1","value of id "+id);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_myprofile_like:
                Fragment fragment=new MaccabiLikeFragment();
                ((MaccabiHomeActivity) getActivity()).loadfragment(fragment);

                break;

        }

    }
    /*public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_fragment, fragment,"addfragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }*/
}
