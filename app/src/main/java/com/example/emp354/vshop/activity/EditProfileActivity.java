package com.example.emp354.vshop.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.Utility;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;
import com.jackandphantom.blurimage.BlurImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    String gender,dob,userChoosenTask,imageLocation;
    Dialog customDialog;
    int REQUEST_CAMERA=0,SELECT_FILE=1;
    Bitmap bitmap;

    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    String DATABASE_NAME;
    VshopSharedPreference vshopSharedPreference;

    android.support.v7.widget.Toolbar editToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Log.d("EditProfileActivity","onCreate");

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
        //for dob
            dob=String.valueOf(vshopUserModel.getDob());
            tvDob.setText(vshopUserModel.getDob());

        //for profile pic
        bitmap=BitmapFactory.decodeFile(vshopUserModel.getProfile_pic());
        imageLocation=String.valueOf(vshopUserModel.getProfile_pic());
        ivImage.setImageBitmap(bitmap);

        //for gender
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
                /*Intent cancelIntent=new Intent(this,HomeActivity.class);
                startActivity(cancelIntent);*/
                finish();
                break;


            case R.id.tv_done:
                /*Intent doneIntent=new Intent(this,HomeActivity.class);*/

                if(tvDob.getText().toString()!=null && !tvDob.getText().toString().equals("")) {
                    dob = tvDob.getText().toString();
                }
                new UpdateInfoAsyncTask().execute();
                /*startActivity(doneIntent);*/
                finish();
                break;



            case R.id.iv_edit:
               /* customDialog=new Dialog(this);
                Button galleryButton,cameraButton;

                customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);// used to remove the title of dialog
                customDialog.setContentView(R.layout.custom_dialog_camera);

                galleryButton=customDialog.findViewById(R.id.btn_gallery);
                cameraButton=customDialog.findViewById(R.id.btn_camera);

                customDialog.setCancelable(true);//dialog does not close on clicking outside

                galleryButton.setOnClickListener(this);
                cameraButton.setOnClickListener(this);

                customDialog.show();// shows the dialog*/

                selectImage();
                break;


            /*case R.id.btn_camera:
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code
                break;

            case R.id.btn_gallery:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
                break;*/

        }
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
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
    }*/

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
            if(!gender.equals("") && !dob.equals("") && !imageLocation.equals("")) {
                vshopSharedPreference.saveImage(imageLocation);
                appDatabase.userDao().updateInfo(gender, dob, imageLocation, id);
            }
            return null;
        }
    }

    private void populateSetDate(int year,int month,int day)
    {

        String dob=day+"."+month+"."+year;
        tvDob.setText(dob);
    }

    private void selectImage()
    {
        final CharSequence[] items={"Take Photo","Choose from Library","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean result=Utility.checkPermission(EditProfileActivity.this);


                if(items[which].equals("Take Photo"))
                {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                }
                else if(items[which].equals("Choose from Library"))
                {
                    userChoosenTask="Choose From Library";
                    if(result)
                        galleryIntent();
                }
                else if(items[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select File"),SELECT_FILE);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                }
                else {
                    Toast.makeText(this,"You do not have permissions.",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==Activity.RESULT_OK)
        {
            if(requestCode==SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if(requestCode==REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data)
    {
        Bitmap thumbnail=(Bitmap)data.getExtras().get("data");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);

        /* File destination=new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");*/
        File dir=new File(Environment.getExternalStorageDirectory(),"vshop_images");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File destination=new File(dir,System.currentTimeMillis()+".jpg");
        /*imageLocation=destination.getAbsolutePath();*/
        /*Log.d("location",String.valueOf(imageLocation));*/
        FileOutputStream fo;
        try
        {
            destination.createNewFile();
            Log.d("location",String.valueOf(destination));
           /* Uri uri=Uri.fromFile(new File(destination.getAbsolutePath()));
            imageLocation=String.valueOf(uri);*/
           imageLocation=destination.getAbsolutePath();
            Log.d("location",String.valueOf(imageLocation));
            fo=new FileOutputStream(destination);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();
        }
        catch (FileNotFoundException e)
        { e.printStackTrace(); }
        catch (IOException e)
        { e.printStackTrace(); }
        ivImage.setImageBitmap(thumbnail);
    }

    private void onSelectFromGalleryResult(Intent data)
    { Bitmap bitmap=null;
        if(data!=null) {
            try {
                Uri fileUri=data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
               /* imageLocation=String.valueOf(fileUri.getPath());*/
                imageLocation=getRealPathFromURI(this,fileUri);
                Log.d("location",String.valueOf(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }ivImage.setImageBitmap(bitmap);
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

    @Override
    protected void onPause() {
        Log.d("EditProfileActivity","onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("EditProfileActivity","onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("EditProfileActivity","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("EditProfileActivity","onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("EditProfileActivity","onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("EditProfileActivity","onStop");
        super.onStop();
    }
}
