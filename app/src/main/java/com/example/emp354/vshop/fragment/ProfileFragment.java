package com.example.emp354.vshop.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.ImagesRecyclerAdapter;
import com.example.emp354.vshop.adapter.ShopsRecyclerAdapter;
import com.jackandphantom.blurimage.BlurImage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;
import static com.example.emp354.vshop.constants.Constant.FILE_INITIAL_PATH;
import static com.example.emp354.vshop.constants.Constant.PRODUCTS;
import static com.example.emp354.vshop.constants.Constant.SHOPS;

public class ProfileFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener {

    //declaring variables
    TextView tvName;
    ImageView ivBlur, ivProfile;
    LinearLayout layoutProductLeft, layoutProductRight, layoutShopLeft, layoutShopRight;
    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    VshopSharedPreference vshopSharedPreference;
    String DATABASE_NAME;
    Bitmap bitmap = null;
    LinearLayoutManager productLayoutManager, shopsLayoutManager;
    RecyclerView recyclerViewProducts, recyclerViewShops;
    ImagesRecyclerAdapter imagesRecyclerAdapter;
    ShopsRecyclerAdapter shopsRecyclerAdapter;


    //inflating layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment", "onCreateView");
        View view = inflater.inflate(R.layout.layout_profile, container, false);
        return view;
    }

    //performing operation after layout is inflated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("ProfileFragment", "onViewCreated");
        //method to check which fragment is this and inflate toolbar layout according to that
        ((HomeActivity) getActivity()).checkFragment();
        tvName = view.findViewById(R.id.tv_name);
        ivBlur = view.findViewById(R.id.iv_blur);
        ivProfile = view.findViewById(R.id.iv_profile);

        layoutProductLeft = view.findViewById(R.id.layout_product_left);
        layoutProductRight = view.findViewById(R.id.layout_product_right);
        layoutShopLeft = view.findViewById(R.id.layout_shop_left);
        layoutShopRight = view.findViewById(R.id.layout_shop_right);
        recyclerViewProducts = view.findViewById(R.id.recyclerview_products);
        recyclerViewShops = view.findViewById(R.id.recyclerview_shops);


        //setting listener on views
        layoutProductLeft.setOnClickListener(this);
        layoutProductRight.setOnClickListener(this);
        layoutShopLeft.setOnClickListener(this);
        layoutShopRight.setOnClickListener(this);


        //initialising adapter
        imagesRecyclerAdapter = new ImagesRecyclerAdapter(getActivity(), PRODUCTS);
        shopsRecyclerAdapter = new ShopsRecyclerAdapter(getActivity(), SHOPS);
        productLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        shopsLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);

        //setting adapter and layout manager to recycler view
        recyclerViewProducts.setLayoutManager(productLayoutManager);
        recyclerViewShops.setLayoutManager(shopsLayoutManager);
        recyclerViewProducts.setAdapter(imagesRecyclerAdapter);
        recyclerViewShops.setAdapter(shopsRecyclerAdapter);


        //initialising variables to perform db operation
        appDatabase = AppDatabase.getAppDatabase(getActivity());
        DATABASE_NAME = "user_db";
        vshopUserModel = new VshopUserModel();
        vshopSharedPreference = VshopSharedPreference.getInstance(getActivity());


        long id = vshopSharedPreference.fetchid();
        vshopUserModel = appDatabase.userDao().getUserInfo(id);
        tvName.setText(String.format("%s %s", vshopUserModel.getFirstName(), vshopUserModel.getLastName()));
        /*  bitmap=MediaStore.Images.Media.getBitmap(vshopUserModel.getProfile_pic())*/


        if (vshopUserModel.getProfile_pic() != null && !vshopUserModel.getProfile_pic().equals("")) {
            loadImage(vshopUserModel.getProfile_pic());
        } else {
            ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.imageview_placeholder));
            BlurImage.with(getActivity()).load(R.drawable.imageview_placeholder).intensity(20).Async(true).into(ivBlur);
        }
    }

    //method to load image
    public void loadImage(String path) {
        bitmap = BitmapFactory.decodeFile(path);
        /* ivProfile.setImageBitmap(bitmap);*/
        ImageLoader.getInstance().displayImage(FILE_INITIAL_PATH + path, ivProfile);
        BlurImage.with(getActivity()).load(bitmap).intensity(20).Async(true).into(ivBlur);
    }


    //method to call when there is a change in sharedpreference listener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        switch (s) {
            case VshopSharedPreference.IMAGE:
                String imagePath = sharedPreferences.getString(s, "");
                loadImage(imagePath);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_product_left:
                if (productLayoutManager.findFirstCompletelyVisibleItemPosition() < (imagesRecyclerAdapter.getItemCount() - 1)) {
                    productLayoutManager.scrollToPosition(productLayoutManager.findFirstCompletelyVisibleItemPosition() - 1);
                }
                break;

            case R.id.layout_product_right:
                if (productLayoutManager.findLastCompletelyVisibleItemPosition() < (imagesRecyclerAdapter.getItemCount() - 1)) {
                    productLayoutManager.scrollToPosition(productLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }
                break;

            case R.id.layout_shop_left:
                if (shopsLayoutManager.findFirstCompletelyVisibleItemPosition() < (shopsRecyclerAdapter.getItemCount() - 1)) {
                    shopsLayoutManager.scrollToPosition(shopsLayoutManager.findFirstCompletelyVisibleItemPosition() - 1);
                }
                break;

            case R.id.layout_shop_right:
                if (shopsLayoutManager.findLastCompletelyVisibleItemPosition() < (shopsRecyclerAdapter.getItemCount() - 1)) {
                    shopsLayoutManager.scrollToPosition(shopsLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }
                break;
        }
    }


    @Override
    public void onDestroy() {
        Log.d("ProfileFragment", "onDestroy");
        vshopSharedPreference.unRegisterListener(this);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.d("ProfileFragment", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("ProfileFragment", "onPause");
        super.onPause();
    }

    @Override
    public void onStart() {
        vshopSharedPreference.setPreferencesChangeListener(this);
        Log.d("ProfileFragment", "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d("ProfileFragment", "onStop");
        super.onStop();
    }
}




