package com.example.emp354.vshop.utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class Utility2 {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 124;

    public static boolean message = false;


    /* @TargetApi(Build.VERSION_CODES.JELLY_BEAN)*/

    public static boolean checkGalleryPermission(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //for gallery permission
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Permission Necessary");
                    builder.setMessage("External storage permission is necessary");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]
                                    {
                                            Manifest.permission.READ_EXTERNAL_STORAGE
                                    }, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }

            } else {
//                Toast.makeText((Activity) context, "External storage permission granted.", Toast.LENGTH_SHORT).show();
                message = true;
            }
        } else {
            message = true;
        }
        return message;
    }


    //for camera permission

    public static boolean checkCameraPermission(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Permission Necessary");
                    builder.setMessage("Camera permission is necessary");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                        }

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);

                }
            } else {
//                Toast.makeText((Activity) context, "Camera storage permission granted.", Toast.LENGTH_SHORT).show();
                message = true;
            }
        } else {
            message = true;
        }
        return message;
    }
}





