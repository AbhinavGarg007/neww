package com.example.emp354.linear.FragmentAssignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.AdapterView.CustomAdapter_recycler;
import com.example.emp354.linear.Assignment.Signup_page;
import com.example.emp354.linear.Model.User;
import com.example.emp354.linear.R;

import java.util.ArrayList;

public class FragmentAssignmentContentList extends Fragment {

    ArrayList<User> list=new ArrayList<User>();
    CustomAdapterFragment array_adapter;



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
      /*  RecyclerView recycler_view=view.findViewById(R.id.recycler_view);
        CustomAdapterFragment array_adapter=new CustomAdapterFragment(getContext(),list);
        recycler_view.setAdapter(array_adapter);

        LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layout_manager);*/



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode ==1 && resultCode == Activity.RESULT_OK && data !=null)
        { User user = new User();

        Bundle b=data.getExtras();
            String type=b.getString("type");

            if(type.equals("Student")) {

               /* String sname = data.getStringExtra("sname");
                String mail = data.getStringExtra("mail");
                String gender = data.getStringExtra("gender");
                String type = data.getStringExtra("type");*/
               /* TextView textview= getView().findViewById(R.id.textview);
                textview.setText(name + mail + gender);
*/
               String sname=b.getString("sname");
               String mail=b.getString("mail");
               String gender=b.getString("gender");

               //checking the data passses or not.
                Log.d("tag1",sname);
                Log.d("tag2",mail);
                Log.d("tag3",gender);


                //adding entry to the model class object
                user.setName(sname);
                user.setType(type);
                user.setGender(gender);
                user.setMail(mail);
                list.add(user);

            }
            else if(type.equals("Employee"))
            {
                /*String ename=data.getStringExtra("ename");
                String type = data.getStringExtra("type");
*/
                String ename=b.getString("ename");

                Log.d("tag4",ename);

                user.setName(ename);
                user.setType(type);
                list.add(user);

                   }
                   //adding entry to recyclerview
            RecyclerView recycler_view=getView().findViewById(R.id.recycler_view);
            array_adapter=new CustomAdapterFragment(getContext(),list);
            recycler_view.setAdapter(array_adapter);
            LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
            recycler_view.setLayoutManager(layout_manager);
            }
            }

   /* @Override
    public void onItemClick(int position) {
        User user=list.get(position);
        FragmentAssignmentDescriptionList descriptionList = new FragmentAssignmentDescriptionList();

        Bundle bundle = new Bundle();
        bundle.putString("username",user.getName());

        descriptionList.setArguments(bundle);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.detail_body,descriptionList);

        fragmentTransaction.commit();

    }

    // To delete an entry from the list
    @Override
    public void onDeleteClick(int position){
        list.remove(position);
        array_adapter.notifyItemRemoved(position);

       *//* if(list.size()==0){
            textView.setVisibility(View.VISIBLE);
        }*/
    }

