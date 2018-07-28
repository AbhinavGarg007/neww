package com.example.emp354.linear.FragmentAssignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.AdapterView.CustomAdapter_recycler;
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
        RecyclerView recycler_view=(RecyclerView)view.findViewById(R.id.recycler_view);
        CustomAdapterFragment array_adapter=new CustomAdapterFragment();
        recycler_view.setAdapter(array_adapter);

        LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layout_manager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String name=data.getStringExtra("name");
                String mail=data.getStringExtra("mail");
                String gender=data.getStringExtra("gender");
                TextView textview= getView().findViewById(R.id.textview);
                textview.setText(name + mail + gender);
            }
        }
    }
}
