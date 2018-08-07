package com.example.emp354.linear.Dialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.emp354.linear.R;

public class DialogAlertClass extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager mFragmentManager;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_alert);

        mFragmentManager = getSupportFragmentManager();
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);
        Button button_10 = findViewById(R.id.button_10);


        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        DialogFragment fragment = null;
        String tag = null;
        switch (v.getId()) {
            case R.id.button_1:
                fragment = new AlertDialogFragment_1();
                tag = "firstdialog";

                break;
            case R.id.button_2:
                fragment = new AlertDialogFragment_2();
                tag = "seconddialog";
                break;
            case R.id.button_3:
                fragment = new ChoiceList();
                tag ="thirddialog";
                break;
           /* case R.id.button_4:
                SingleChoiceList fourth = new SingleChoiceList();
                fourth.show(mFragmentManager, "fourthdailog");
                break;*/
            case R.id.button_4:
                fragment=new SingleChoiceList();
                tag="fourthdialog";
                break;
            case R.id.button_5:
                fragment = new MultipleChoiceList();
                tag = "fifthdailog";
                break;
            case R.id.button_6:
                fragment = new CustomDialog();
                tag = "sixthdailog";
                break;
            case R.id.button_7:
                fragment = new TimePickerFragment();
                tag = "seventhdialog";
                break;
            case R.id.button_8:
                fragment = new DatePickerFragment();
                tag = "eighth tag";
                break;
            case R.id.button_9:
                initiatePopUp(v);
                break;
            case R.id.button_10:
                fullScreenPopUp(v);
                break;
        }

        if (!(v.getId() == R.id.button_9 || v.getId() == R.id.button_10))

            fragment.show(mFragmentManager, tag);
    }

    private void initiatePopUp(View v) {
        LayoutInflater inflater = (LayoutInflater) DialogAlertClass.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_layout, null, false);
        mPopupWindow = new PopupWindow(layout, 450, 450, true);
        mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        Button cancel_btn = layout.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(cancel_button_click_listener);
    }

    private void fullScreenPopUp(View v) {
        LayoutInflater inflater = (LayoutInflater) DialogAlertClass.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.fullscreen_popup_layout, null, false);
        mPopupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        ImageView cancel_btn = layout.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(cancel_button_click_listener);
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPopupWindow.dismiss();
        }
    };
}
