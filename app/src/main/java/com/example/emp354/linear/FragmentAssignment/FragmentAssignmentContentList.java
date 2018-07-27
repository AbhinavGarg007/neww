package com.example.emp354.linear.FragmentAssignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.Assignment.Signup_page;
import com.example.emp354.linear.R;

public class FragmentAssignmentContentList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_assignment_content_list,container,false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),FragmentAssignmentSignup.class);
                startActivityForResult(i,1);
            }
        });

        /*Bundle bundle=getArguments();
        String strtext=bundle.getString("name");
        textview.setText(strtext);*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String result=data.getStringExtra("result");
                TextView textview= getView().findViewById(R.id.textview);

                textview.setText(result);
            }
        }
    }
}
