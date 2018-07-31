package com.example.emp354.linear.ViewPager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emp354.linear.FragmentAssignment.CustomAdapterFragment;
import com.example.emp354.linear.R;

public class ViewPagerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.viewpager_recycler_view,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //adding entry to recyclerview
        RecyclerView recycler_view=getView().findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter();
        recycler_view.setAdapter(adapter);
        LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layout_manager);
    }

   /* public static ViewPagerFragment newInstance(String text, int image) {

        ViewPagerFragment f = new ViewPagerFragment();
        Bundle b = new Bundle();
        b.putString("text", text);
        b.putInt("img", image);

        f.setArguments(b);

        return f;
    }*/
}
