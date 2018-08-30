package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.MySharedPreferences;
import com.example.emp354.linear.R;

import java.util.ArrayList;

public class MaccabiLikeFragment extends Fragment {

    MaccabiLikeAdapter maccabiLikeAdapter;
    MaccabiDataBaseHelper db;
    ArrayList<MaccabiUserModel> mUserLikedByList;
    MySharedPreferences mySharedPreferences;
    Menu menu;
    MenuItem menuItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_all_members_details_fragment_layout,container,false);
        ((MaccabiHomeActivity)getActivity()).checkFragment();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {




        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mySharedPreferences=MySharedPreferences.getInstance(getActivity());
        int id=(int)mySharedPreferences.fetchId();
        db = new MaccabiDataBaseHelper(getActivity());
        db.getReadableDatabase();
        mUserLikedByList=db.getLikedBy(id);
        maccabiLikeAdapter=new MaccabiLikeAdapter(getContext(),mUserLikedByList);
        recyclerView.setAdapter(maccabiLikeAdapter);

        LinearLayoutManager layout_manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout_manager);



        }

   /* @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menuItem=menu.findItem(R.id.home_icon_edit).setVisible(false);
        menuItem.setVisible(false);


    }*/

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu=menu;
        inflater.inflate(R.menu.home_icon,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}
