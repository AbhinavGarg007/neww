package com.example.emp354.vshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;
import com.jackandphantom.blurimage.BlurImage;

public class ProfileFragment extends Fragment {
    TextView tvName;
    ImageView ivBlur;
    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    VshopSharedPreference vshopSharedPreference;
    String DATABASE_NAME;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvName=view.findViewById(R.id.tv_name);
        ivBlur=view.findViewById(R.id.iv_blur);


        BlurImage.with(getActivity()).load(R.drawable.image_1).intensity(20).Async(true).into(ivBlur);

        appDatabase=AppDatabase.getAppDatabase(getActivity());
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();
        vshopSharedPreference=VshopSharedPreference.getInstance(getActivity());

        long id=vshopSharedPreference.fetchid();
        vshopUserModel=appDatabase.userDao().getUserInfo(id);
        tvName.setText(vshopUserModel.getFirstName()+" "+vshopUserModel.getLastName());

    }
}
