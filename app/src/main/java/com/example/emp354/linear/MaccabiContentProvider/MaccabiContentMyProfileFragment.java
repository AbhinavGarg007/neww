package com.example.emp354.linear.MaccabiContentProvider;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.MaccabiContentSharedPreference;
import com.example.emp354.linear.R;

public class MaccabiContentMyProfileFragment extends Fragment implements View.OnClickListener {

    String mailId;
    MaccabiContentSharedPreference maccabiContentSharedPreference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_my_profile,container,false);
        ((MaccabiContentHomeActivity)getActivity()).checkFragment();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        maccabiContentSharedPreference=MaccabiContentSharedPreference.getInstance(getActivity());
        int id=(int)maccabiContentSharedPreference.fetchId();

        TextView tvMailData=view.findViewById(R.id.tv_mail_data);
        TextView tvFirstNameData=view.findViewById(R.id.tv_first_name_data);
        TextView tvLastNameData=view.findViewById(R.id.tv_last_name_data);
        TextView tvPhoneNo=view.findViewById(R.id.tv_phone_no_data);
        TextView tvLikes=view.findViewById(R.id.tv_myprofile_like);
        TextView tvMyProfileLike=view.findViewById(R.id.tv_myprofile_like);



        tvMyProfileLike.setOnClickListener(this);

       /* mailId=db.getMailId(id);

        db.getReadableDatabase();
        String[] data=db.getUserData(mailId);
        tvMailData.setText(mailId);
        tvFirstNameData.setText(data[0]);
        tvLastNameData.setText(data[1]);
        tvPhoneNo.setText(data[2]);
        tvLikes.setText(String.valueOf(count));

        db.close();*/
       String[] projection={MaccabiContentUserModel.COLUMN_MAIL_ID,MaccabiContentUserModel.COLUMN_FIRST_NAME
               ,MaccabiContentUserModel.COLUMN_LAST_NAME,MaccabiContentUserModel.COLUMN_PHONE_NO};
        Cursor cursor=getActivity().getContentResolver().query(MaccabiContentProvider.CONTENT_URI,
                projection,
                MaccabiContentUserModel.COLUMN_ID+"=?",
                new String[] {String.valueOf(id)},
                null);

        if(cursor.moveToFirst())
        {
            String mailId=cursor.getString(cursor.getColumnIndex(MaccabiContentUserModel.COLUMN_MAIL_ID));
            String firstName=cursor.getString(cursor.getColumnIndex(MaccabiContentUserModel.COLUMN_FIRST_NAME));
            String lastName=cursor.getString(cursor.getColumnIndex(MaccabiContentUserModel.COLUMN_LAST_NAME));
            String phoneNo=cursor.getString(cursor.getColumnIndex(MaccabiContentUserModel.COLUMN_PHONE_NO));

            tvMailData.setText(mailId);
            tvFirstNameData.setText(firstName);
            tvLastNameData.setText(lastName);
            tvPhoneNo.setText(phoneNo);

        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_myprofile_like:
                Fragment fragment=new MaccabiContentLikeFragment();
                ((MaccabiContentHomeActivity) getActivity()).loadfragment(fragment);

                break;

        }
    }
}
