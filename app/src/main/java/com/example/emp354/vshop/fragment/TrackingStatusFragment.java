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

    //applying animation on fragment in and out
    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        final int animatorId = (enter) ? R.animator.from_left : R.animator.to_left;
        final Animator anim = AnimatorInflater.loadAnimator(getActivity(), animatorId);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                setButton(true);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setButton(false);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                setButton(false);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        return anim;
    }

    //method to make button disable when the animation is going on
    private void setButton(boolean isStart)
    {
       if(isStart)
       {
           ((TrackOrderActivity)getActivity()).findViewById(R.id.btn_order_summary).setEnabled(false);
       }
       else {
           ((TrackOrderActivity)getActivity()).findViewById(R.id.btn_order_summary).setEnabled(true);
       }
    }
}
