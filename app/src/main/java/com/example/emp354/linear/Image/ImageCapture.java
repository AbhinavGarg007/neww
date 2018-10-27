package com.example.emp354.linear.Image;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emp354.linear.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCapture extends AppCompatActivity implements View.OnClickListener {


    ImageView ivEdit,ivImage;
    String userChoosenTask;
    int REQUEST_CAMERA=0,SELECT_FILE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_image_capture);
            setUI();
            setUITEXT();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void setUITEXT()
    {

    }
    private void setUI()
    {
        ivEdit = findViewById(R.id.iv_edit);
        ivImage = findViewById(R.id.iv_image_capture);
        ivEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_edit:
                selectImage();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void selectImage()
    {
        final CharSequence[] items={"Take Photo","Choose from Library","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(ImageCapture.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean result=Utility.checkPermission(ImageCapture.this);


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
        FileOutputStream fo;
        try
        {
            destination.createNewFile();
            fo=new FileOutputStream(destination);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ivImage.setImageBitmap(thumbnail);
    }

    private void onSelectFromGalleryResult(Intent data)
    {
        Bitmap bitmap=null;
        if(data!=null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ivImage.setImageBitmap(bitmap);
    }
}
