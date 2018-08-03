package com.example.emp354.linear.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.R;

public class AlertDialogFragment_1 extends android.support.v4.app.DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       /* return super.onCreateDialog(savedInstanceState);*/

        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.missile)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               }) ;
        return builder.create();

    }

}
