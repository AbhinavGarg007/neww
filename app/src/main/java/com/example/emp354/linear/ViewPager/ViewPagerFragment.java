package com.example.emp354.linear.ViewPager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.FragmentAssignment.CustomAdapterFragment;
import com.example.emp354.linear.R;

public class ViewPagerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager_recycler_view,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


         Bundle c=this.getArguments();
       /* TextView tv = (TextView) view.findViewById(R.id.textview_title);
        tv.setText(c.getString("text"));

        TextView tv2 = (TextView) view.findViewById(R.id.textview_date);
        tv2.setText(c.getString("text2"));*/

       String title=c.getString("title");
       String text=c.getString("text");
       int image=c.getInt("image");
       String[] date=c.getStringArray("date");

        //adding entry to recyclerview
        RecyclerView recycler_view=getView().findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(title,text,image,date);
        recycler_view.setAdapter(adapter);
        LinearLayoutManager layout_manager=new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layout_manager);
    }

    public static ViewPagerFragment newInstance(String title,String text,int image,String[] date) {

        ViewPagerFragment f = new ViewPagerFragment();
        Bundle b = new Bundle();

        b.putString("title",title);
        b.putString("text", text);
        b.putInt("image", image);
        b.putStringArray("date",date);

        f.setArguments(b);

        return f;
    }
}
