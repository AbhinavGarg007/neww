package com.example.emp354.vshop.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;
import com.jackandphantom.blurimage.BlurImage;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivEdit,ivBlur;
    CircleImageView ivImage;
    RadioGroup rgGender;
    TextView tvResetPassword,tvDob,tvCancel,tvDone,tvName;
    RadioButton rbMale,rbFemale;
    Calendar calendar;
    int currentYear,currentMonth,currentDay;
    ProgressDialog dialog;
    long id;
    String gender,dob;
    Dialog customDialog;

    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    String DATABASE_NAME;
    VshopSharedPreference vshopSharedPreference;

    android.support.v7.widget.Toolbar editToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //initialing variables



        ivImage=findViewById(R.id.iv_image);
        ivEdit=findViewById(R.id.iv_edit);
        ivBlur=findViewById(R.id.iv_blur_edit);
        rgGender=findViewById(R.id.rg_gender_edit);
        tvDob=findViewById(R.id.tv_dob);
        tvResetPassword=findViewById(R.id.tv_reset_password);
        rbMale=findViewById(R.id.rb_male_edit);
        rbFemale=findViewById(R.id.rb_female_edit);
        editToolbar=findViewById(R.id.edit_toolbar);
        tvCancel=findViewById(R.id.tv_cancel);
        tvDone=findViewById(R.id.tv_done);
        tvName=findViewById(R.id.tv_name_edit);


        BlurImage.with(this).load(R.drawable.image_1).intensity(20).Async(true).into(ivBlur);

        appDatabase=AppDatabase.getAppDatabase(this);
        DATABASE_NAME="user_db";
        vshopUserModel=new VshopUserModel();
        vshopSharedPreference=VshopSharedPreference.getInstance(this);


        id=vshopSharedPreference.fetchid();
        vshopUserModel=appDatabase.userDao().getUserInfo(id);

        if(vshopUserModel.getGender()!=null)
        {
            if(vshopUserModel.getGender().equals(getResources().getString(R.string.female)))
            {
                rbFemale.setChecked(true); }
            else {
                rbMale.setChecked(true); }
        }
        tvName.setText(vshopUserModel.getFirstName()+" "+vshopUserModel.getLastName());
        tvDob.setText(vshopUserModel.getDob());
        gender=vshopUserModel.getGender();


        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton checkedRadioButton=radioGroup.findViewById(i);
                if(checkedRadioButton.getText().toString()!=null) {
                    gender =checkedRadioButton.getText().toString();
                }
            }
        });




        //apply onClickListeners
        tvResetPassword.setOnClickListener(this);
        tvDob.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvDone.setOnClickListener(this);
        ivEdit.setOnClickListener(this);

        calendar=Calendar.getInstance();
        currentYear=calendar.get(Calendar.YEAR);
        currentMonth=calendar.get(Calendar.MONTH);
        currentDay=calendar.get(Calendar.DAY_OF_MONTH);

        /*editToolbar.setNavigationContentDescription("Cancel");
       editToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent=new Intent(EditProfileActivity.this,HomeActivity.class);
                startActivity(editIntent);
            }
        });

        editToolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.done:
                        Intent editIntent=new Intent(EditProfileActivity.this,HomeActivity.class);
                        startActivity(editIntent);
                }
                return true;
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_reset_password:
                Intent resetPasswordIntent=new Intent(this,ResetPasswordActivity.class);
                startActivity(resetPasswordIntent);
                break;

            case R.id.tv_dob:
                DatePickerDialog dialog=new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        populateSetDate(year,month+1,day);
                    }
                },currentYear,currentMonth,currentDay);
                dialog.setCancelable(false);
                dialog.show();
                break;

            case R.id.tv_cancel:
                Intent cancelIntent=new Intent(this,HomeActivity.class);
                startActivity(cancelIntent);
                break;


            case R.id.tv_done:
                Intent doneIntent=new Intent(this,HomeActivity.class);

                if(tvDob.getText().toString()!=null && !tvDob.getText().toString().equals("")) {
                    dob = tvDob.getText().toString();
                }
                new UpdateInfoAsyncTask().execute();
                startActivity(doneIntent);
                break;



            case R.id.iv_edit:
                customDialog=new Dialog(this);
                Button galleryButton,cameraButton;

                customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);// used to remove the title of dialog
                customDialog.setContentView(R.layout.custom_dialog_camera);

                galleryButton=customDialog.findViewById(R.id.btn_gallery);
                cameraButton=customDialog.findViewById(R.id.btn_camera);

                customDialog.setCancelable(true);//dialog does not close on clicking outside

                galleryButton.setOnClickListener(this);
                cameraButton.setOnClickListener(this);

                customDialog.show();// shows the dialog
                break;


            case R.id.btn_camera:
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code

                break;

            case R.id.btn_gallery:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code


                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    ivImage.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    ivImage.setImageURI(selectedImage);
                }
                break;
        }
    }

    private class UpdateInfoAsyncTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
        dialog=ProgressDialog.show(EditProfileActivity.this,"Updating data","Please Wait..");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.userDao().updateInfo(gender,dob,id);
            return null;
        }
    }

    private void populateSetDate(int year,int month,int day)
    {
       /* if(day<10)
        {
            String dobDay="0"+day;
        }*/

        String dob=day+"."+month+"."+year;
        tvDob.setText(dob);
    }


}
