package com.example.emp354.vshop.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.InputStream;

public class ProfileFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    TextView tvName;
    ImageView ivBlur,ivProfile;
    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    VshopSharedPreference vshopSharedPreference;
    String DATABASE_NAME;
    Bitmap bitmap=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment","onCreateView");
        View view=inflater.inflate(R.layout.layout_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment","onViewCreated");
        tvName=view.findViewById(R.id.tv_name);
        ivBlur=view.findViewById(R.id.iv_blur);
        ivProfile=view.findViewById(R.id.iv_profile);


        BlurImage.with(getActivity()).load(R.drawable.image_1).intensity(20).Async(true).into(ivBlur);

        appDatabase=AppDatabase.getAppDatabase(getActivity());
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();
        vshopSharedPreference=VshopSharedPreference.getInstance(getActivity());
        vshopSharedPreference.setPreferencesChangeListener(this);

        long id=vshopSharedPreference.fetchid();
        vshopUserModel=appDatabase.userDao().getUserInfo(id);
        tvName.setText(vshopUserModel.getFirstName()+" "+vshopUserModel.getLastName());
      /*  bitmap=MediaStore.Images.Media.getBitmap(vshopUserModel.getProfile_pic())*/


        loadImage(vshopUserModel.getProfile_pic());

    }

   public void loadImage(String path)
   {
       bitmap=BitmapFactory.decodeFile(path);
       ivProfile.setImageBitmap(bitmap);

   }

    @Override
    public void onDestroy() {
        Log.d("ProfileFragment","onDestroy");
        vshopSharedPreference.unRegisterListener(this);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.d("ProfileFragment","onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("ProfileFragment","onPause");
        super.onPause();
    }

    @Override
    public void onStart() {
        Log.d("ProfileFragment","onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d("ProfileFragment","onStop");
        super.onStop();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        switch (s)
        {
            case VshopSharedPreference.IMAGE:
                String imagePath=sharedPreferences.getString(s,"");
                loadImage(imagePath);

        }

    }
}
