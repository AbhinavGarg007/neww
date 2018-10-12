package com.example.emp354.vshop;

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

public class Utility {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    public static boolean checkPermission(final Context context) {

        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
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
                return false;
            } else {
                return true;
            }
        }
        else {
            return true;

                }
        }
    }



