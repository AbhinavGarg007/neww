package com.example.emp354.vshop.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.TrackOrderActivity;

public class TrackingStatusFragment extends Fragment {
    boolean isStart=false;
    @Nullable
    @Override

    //inflating layout
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tracking_status, container, false);
        return view;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        final int animatorId = (enter) ? R.anim.enter_from_left : R.anim.exit_to_left;
        final Animator anim = AnimatorInflater.loadAnimator(getActivity(), animatorId);
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                setButton(true);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                setButton(false);
            }
        });

        return null;
    }

    private void setButton(boolean isStart)
    {
       if(isStart)
       {
           ((TrackOrderActivity)getActivity()).findViewById(R.id.btn_tracking_status).setEnabled(false);
       }
       else {
           ((TrackOrderActivity)getActivity()).findViewById(R.id.btn_tracking_status).setEnabled(true);
       }
    }
}
