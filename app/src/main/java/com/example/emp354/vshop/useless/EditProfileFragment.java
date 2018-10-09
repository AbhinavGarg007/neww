package com.example.emp354.vshop.useless;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.fragment.UpdatePasswordFragment;
import com.jackandphantom.blurimage.BlurImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment implements View.OnClickListener{
    ImageView ivEdit;
    CircleImageView ivImage;
    RadioGroup rgGender;
    EditText et_dob;
    TextView tv_reset_password;
    RadioButton rbMale,rbFemale;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_edit_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       /* BlurImage.with(getActivity()).load(R.drawable.image_1).intensity(20).Async(true).into(ivBlur);*/

        ivImage=view.findViewById(R.id.iv_image);
        ivEdit=view.findViewById(R.id.iv_edit);
        rgGender=view.findViewById(R.id.rg_gender_edit);
        et_dob=view.findViewById(R.id.et_dob);
        tv_reset_password=view.findViewById(R.id.tv_reset_password);
        rbMale=view.findViewById(R.id.rb_male_edit);
        rbFemale=view.findViewById(R.id.rb_female_edit);

        tv_reset_password.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_reset_password:
                ((HomeActivity)getActivity()).loadFragment(new UpdatePasswordFragment());
                break;
        }

    }
}
