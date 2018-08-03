package com.example.emp354.linear.NavigationDrawer.NavigationDrawerSelf;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emp354.linear.R;

public class NavigationDrawerSelfFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_nav,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       Bundle c=this.getArguments();
       Log.d("tag","hi");
       String text=c.getString("text");
        Log.d("tag1",text);

       TextView textView=view.findViewById(R.id.textview_item);
       textView.setText(text);


    }
    public static NavigationDrawerSelfFragment newInstance(String text)
    {
       NavigationDrawerSelfFragment ndsf=new NavigationDrawerSelfFragment();
       Bundle b=new Bundle();
       b.putString("text",text);
       ndsf.setArguments(b);
       return ndsf;
    }
}
