package com.example.emp354.vshop.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.vshop.AppDatabase;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.VshopSharedPreference;
import com.example.emp354.vshop.VshopUserModel;
import com.example.emp354.vshop.utility.Utility2;
import com.jackandphantom.blurimage.BlurImage;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivityCropWithLibrary extends AppCompatActivity implements View.OnClickListener {

    //declaring variables
    ImageView ivEdit, ivBlur;
    CircleImageView ivImage;
    RadioGroup rgGender;
    TextView tvResetPassword, tvDob, tvCancel, tvDone, tvName;
    RadioButton rbMale, rbFemale;
    Calendar calendar;
    int currentYear, currentMonth, currentDay;
    ProgressDialog updateDialog;
    long id;
    String gender, dob, imageLocation;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1, PIC_CROP=2;
    Bitmap bitmap;

    AppDatabase appDatabase;
    VshopUserModel vshopUserModel;
    String DATABASE_NAME;
    VshopSharedPreference vshopSharedPreference;


    Toolbar editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Log.d("EditProfileActivity", "onCreate");

        //initialising variables
        ivImage = findViewById(R.id.iv_image);
        ivEdit = findViewById(R.id.iv_edit);
        ivBlur = findViewById(R.id.iv_blur_edit);
        rgGender = findViewById(R.id.rg_gender_edit);
        tvDob = findViewById(R.id.tv_dob);
        tvResetPassword = findViewById(R.id.tv_reset_password);
        rbMale = findViewById(R.id.rb_male_edit);
        rbFemale = findViewById(R.id.rb_female_edit);
        editToolbar = findViewById(R.id.edit_toolbar);
        tvCancel = findViewById(R.id.tv_cancel);
        tvDone = findViewById(R.id.tv_done);
        tvName = findViewById(R.id.tv_name_edit);

        //apply onClickListeners
        tvResetPassword.setOnClickListener(this);
        tvDob.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvDone.setOnClickListener(this);
        ivEdit.setOnClickListener(this);

        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);


        //database related information
        appDatabase = AppDatabase.getAppDatabase(this);
        DATABASE_NAME = "user_db";
        vshopUserModel = new VshopUserModel();
        vshopSharedPreference = VshopSharedPreference.getInstance(this);


        //getting id from sharedpreference and accordingly getting model data
        id = vshopSharedPreference.fetchid();
        vshopUserModel = appDatabase.userDao().getUserInfo(id);

        setData();


        //setting listener on radio button
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = radioGroup.findViewById(i);
                if (checkedRadioButton.getText().toString() != null) {
                    gender = checkedRadioButton.getText().toString();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //click to reset password
            case R.id.tv_reset_password:
                Intent resetPasswordIntent = new Intent(this, ResetPasswordActivity.class);
                startActivity(resetPasswordIntent);
                break;

            //click to set dob
            case R.id.tv_dob:
                DatePickerDialog dialog = new DatePickerDialog(EditProfileActivityCropWithLibrary.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        populateSetDate(year, month + 1, day);
                        dob = tvDob.getText().toString();
                    }
                }, currentYear, currentMonth, currentDay);
                dialog.setCancelable(false);
                dialog.show();
                break;

            //click to finish the activity
            case R.id.tv_cancel:
                finish();
                break;


            //click to update data in db
            case R.id.tv_done:
                new UpdateInfoAsyncTask().execute();

                break;

            case R.id.iv_edit:
                /* new EditAsyncTask().execute();*/
                selectImage();
                break;
        }
    }


    //async task to update info
    private class UpdateInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            updateDialog = ProgressDialog.show(EditProfileActivityCropWithLibrary.this, "Updating data", "Please Wait..");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateDialog.dismiss();
            finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            vshopSharedPreference.saveImage(imageLocation);
            appDatabase.userDao().updateInfo(gender, dob, imageLocation, id);

            return null;
        }
    }

    //method to set date on textview
    private void populateSetDate(int year, int month, int day) {
        String dob = day + "." + month + "." + year;
        tvDob.setText(dob);
    }

    //method to provide options to user to select image
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivityCropWithLibrary.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*boolean result=Utility.checkPermission(EditProfileActivity.this);*/
                if (items[which].equals("Take Photo")) {
                    if (Utility2.checkCameraPermission(EditProfileActivityCropWithLibrary.this))
                        cameraIntent();
                } else if (items[which].equals("Choose from Library")) {
                    if (Utility2.checkGalleryPermission(EditProfileActivityCropWithLibrary.this))
                        galleryIntent();
                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    //method for the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Utility2.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent();
                } else {
                    Toast.makeText(this, "You do not have external storage permissions.", Toast.LENGTH_SHORT).show();
                }
                break;


            case Utility2.MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraIntent();
                } else {
                    Toast.makeText(this, "You do not have camera permissions.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    //method to be called after getting data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Log.d("req", ""+requestCode);
            if (requestCode == SELECT_FILE) {

                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File destination = new File(dir, System.currentTimeMillis() + ".jpg");
                try {
                    destination.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri uri=Uri.fromFile(new File(destination.getAbsolutePath()));

                UCrop.of(data.getData(), uri)
                        .withAspectRatio(16, 9)
                        .withMaxResultSize(25, 25)
                        .start(this);

                /*onSelectFromGalleryResult(data);*/
            }
            else if (requestCode == REQUEST_CAMERA) {


                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File destination = new File(dir, System.currentTimeMillis() + ".jpg");
                try {
                    destination.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri uri=Uri.fromFile(new File(destination.getAbsolutePath()));


                UCrop.of(data.getData(), uri)
                        .withAspectRatio(16, 9)
                        .withMaxResultSize(25, 25)
                        .start(this);
               /* onCaptureImageResult(data);*/
            }

            else if (requestCode == UCrop.REQUEST_CROP)
            {
                final Uri resultUri = UCrop.getOutput(data);
                onCropImageResult(resultUri);
            }
            else if (resultCode == UCrop.RESULT_ERROR) {
                final Throwable cropError = UCrop.getError(data);
                Log.d("error", cropError.toString());
            }
        }



    }


    //method to open camera
    private void cameraIntent() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support capturing images!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //method to open gallery
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }




    //method to perform operation on captured image
    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (!dir.exists()) {
            dir.mkdir();
        }
        File destination = new File(dir, System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            imageLocation = destination.getAbsolutePath();

            fo = new FileOutputStream(destination);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImage(thumbnail);
    }

    //method to get image from gallery
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                Uri contentUri = data.getData();
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                /* imageLocation=String.valueOf(fileUri.getPath());*/
                imageLocation = getRealPathFromURI(this, contentUri);
                Log.d("location", String.valueOf(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setImage(bm);
    }

    private void onCropImageResult(Uri uri)
    {


        Bitmap bm = null;
        if (uri != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                /* imageLocation=String.valueOf(fileUri.getPath());*/
                imageLocation = getRealPathFromURI(this, uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setImage(bm);


        /*Uri contentUri = data.getData();
        //get the returned data
        Bundle extras = data.getExtras();
        //get the cropped bitmap
        Bitmap cropBitmap = (Bitmap) extras.get("data");
        *//* Bitmap cropBitmap =  extras.getParcelable("data");*//*

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        cropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        //for folder in just next to internal storage
        *//* File dir = new File(Environment.getExternalStorageDirectory(), "vshop_images");*//*

        //for folder in app specific folder
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File destination = new File(dir, System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            //to convert string filepath into uri
           *//* Uri uri=Uri.fromFile(new File(destination.getAbsolutePath()));
            imageLocation=String.valueOf(uri);*//*
            imageLocation = destination.getAbsolutePath();
            fo = new FileOutputStream(destination);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImage(cropBitmap);*/
    }

    //method to get string file path from uri
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //method to set image into imageview
    private void setImage(Bitmap bm) {
        ivImage.setImageBitmap(bm);
        BlurImage.with(this).load(bm).intensity(20).Async(true).into(ivBlur);
    }


    private void setData() {
        //checking whether gender entry is not null
        if (vshopUserModel.getGender() != null) {
            //for gender
            gender = vshopUserModel.getGender();
            if (vshopUserModel.getGender().equals(getResources().getString(R.string.female))) {
                rbFemale.setChecked(true);
            } else {
                rbMale.setChecked(true);
            }
        }
        tvName.setText(vshopUserModel.getFirstName() + " " + vshopUserModel.getLastName());
        //for dob
        dob = String.valueOf(vshopUserModel.getDob());
        tvDob.setText(vshopUserModel.getDob());

        //for profile pic
        if (vshopUserModel.getProfile_pic() == null || vshopUserModel.getProfile_pic().equals("")) {
            ivImage.setImageDrawable(getResources().getDrawable(R.drawable.imageview_placeholder));
            BlurImage.with(this).load(R.drawable.imageview_placeholder).intensity(20).Async(true).into(ivBlur);
        } else {
            imageLocation = String.valueOf(vshopUserModel.getProfile_pic());
            bitmap = BitmapFactory.decodeFile(vshopUserModel.getProfile_pic());
            setImage(bitmap);
        }

    }

    @Override
    protected void onPause() {
        Log.d("EditProfileActivity", "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("EditProfileActivity", "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("EditProfileActivity", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("EditProfileActivity", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("EditProfileActivity", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("EditProfileActivity", "onStop");
        super.onStop();
    }
}





