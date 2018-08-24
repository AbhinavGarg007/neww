package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.R;

import java.util.ArrayList;
import java.util.List;

public class MaccabiAllMembersDetailsFragment extends Fragment {
    MaccabiRecyclerViewAdapter maccabiRecyclerViewAdapter;
    MaccabiDataBaseHelper db;
    ArrayList<MaccabiUserModel> mUMList ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.maccabi_all_members_details_fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        db=new MaccabiDataBaseHelper(getActivity());
        db.getReadableDatabase();
        mUMList=db.getAllUser();
        maccabiRecyclerViewAdapter=new MaccabiRecyclerViewAdapter(getContext(),mUMList);
        recyclerView.setAdapter(maccabiRecyclerViewAdapter);
        LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout_manager);
    }
}
