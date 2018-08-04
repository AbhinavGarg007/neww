package com.example.emp354.linear.Dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.emp354.linear.R;

public class DialogAlertClass extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fm=getSupportFragmentManager();
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_alert);



        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);
        Button button_10= findViewById(R.id.button_10);


        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_10.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button_1: AlertDialogFragment_1 first=new AlertDialogFragment_1();
                                first.show(fm,"firstdialog");
                                break;
            case R.id.button_2: AlertDialogFragment_2 second=new AlertDialogFragment_2();
                                second.show(fm,"seconddialog");
                                break;
            case R.id.button_3: AlertDialogFragment_2 third=new AlertDialogFragment_2();
                                third.show(fm,"seconddialog");
                                break;
            case R.id.button_4: SingleChoiceList fourth=new SingleChoiceList();
                                fourth.show(fm,"fourthdailog");
                                break;
            case R.id.button_5: MultipleChoiceList fifth=new MultipleChoiceList();
                                fifth.show(fm,"fifthdailog");
                                break;
            case R.id.button_6: CustomDialog sixth=new CustomDialog();
                                sixth.show(fm,"sixthdailog");
                                break;
            case R.id.button_7: TimePickerFragment seventh=new TimePickerFragment();
                                 seventh.show(fm,"seventhdialog");
                                 break;
            case R.id.button_8: DatePickerFragment eighth=new DatePickerFragment();
                                 eighth.show(fm,"eighth tag");
                                 break;
            case R.id.button_9: initiatePopUp(v);
            case R.id.button_10:fullScreenPopUp(v);
        }
    }

    private void initiatePopUp(View v)
    {
        LayoutInflater inflater=(LayoutInflater) DialogAlertClass.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.popup_layout,null,false);
        popupWindow=new PopupWindow(layout,450,450,true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);

        Button cancel_btn=layout.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(cancel_button_click_listener);
        }

        private void fullScreenPopUp(View v)
        {
         LayoutInflater inflater=(LayoutInflater)DialogAlertClass.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View layout=inflater.inflate(R.layout.fullscreen_popup_layout,null,false);
         popupWindow=new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,true);
         popupWindow.showAtLocation(layout,Gravity.CENTER,0,0);

            ImageView cancel_btn=layout.findViewById(R.id.cancel_btn);
            cancel_btn.setOnClickListener(cancel_button_click_listener);
        }

    private View.OnClickListener cancel_button_click_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    };
}
