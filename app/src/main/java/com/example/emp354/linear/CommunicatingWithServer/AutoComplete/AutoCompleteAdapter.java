package com.example.emp354.linear.CommunicatingWithServer.AutoComplete;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.PlaceModel;

import com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO.Predictions;
import com.example.emp354.linear.Listener.ItemClickListener;
import com.example.emp354.linear.R;

import java.util.List;


public class AutoCompleteAdapter extends RecyclerView.Adapter{

    private Context mcontext;
    private PlaceModel placeModel;
    private List<Predictions> predictionsList;
    private ItemClickListener mItemClickListener;

    //creating constructor of adapter
    public AutoCompleteAdapter(Context context,PlaceModel model,ItemClickListener itemClickListener)
    {
        //getting passed values from constructor
        mcontext=context;
        mItemClickListener=itemClickListener;

        //checking whether model is not null
        if(model!=null)
        {
            placeModel=model;
            predictionsList=model.getPredictions();
            Log.d("predictionList.size",String.valueOf(predictionsList.size()));


        }
    }

    @NonNull
    @Override
    public AutoCompleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //setting view to holder
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.autocomplete_item_layout,parent,false);
        return new AutoCompleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AutoCompleteViewHolder viewHolder=(AutoCompleteViewHolder) holder;
        viewHolder.textViewMain.setText(placeModel.getPredictions().get(position).getStructured_formatting().getMain_text());

        //checking whether secondary text has a valid entry
        if(placeModel.getPredictions().get(position).getStructured_formatting().getSecondary_text()!=null) {
            viewHolder.textViewSecondary.setText(placeModel.getPredictions().get(position).getStructured_formatting().getSecondary_text());

        }
        else
        {
            viewHolder.linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(mcontext,R.drawable.red_stroke_corner_square));

        }




    }

    @Override
    public int getItemCount() {
        return predictionsList.size();
    }

    public class AutoCompleteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //declaring variables
        TextView textViewMain,textViewSecondary;
        LinearLayout linearLayout;
        public AutoCompleteViewHolder(View itemView) {
            super(itemView);

            //initialising variables
            textViewMain=itemView.findViewById(R.id.tv_main);
            textViewSecondary=itemView.findViewById(R.id.tv_secondary);
            linearLayout=itemView.findViewById(R.id.place_layout);
            linearLayout.setOnClickListener(this);
        }


        //implementig method of listener
        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
