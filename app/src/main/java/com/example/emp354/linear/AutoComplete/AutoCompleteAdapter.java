package com.example.emp354.linear.AutoComplete;

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

import com.example.emp354.linear.AutoComplete.POJO.PlaceModel;

import com.example.emp354.linear.AutoComplete.POJO.Predictions;
import com.example.emp354.linear.R;

import java.util.List;


public class AutoCompleteAdapter extends RecyclerView.Adapter{

    private Context mcontext;
    private PlaceModel placeModel;
    private List<Predictions> predictionsList;

    public AutoCompleteAdapter(Context context,PlaceModel model)
    {
        mcontext=context;

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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.autocomplete_item_layout,parent,false);
        return new AutoCompleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AutoCompleteViewHolder viewHolder=(AutoCompleteViewHolder) holder;
        if(placeModel.getPredictions().get(position).getStructured_formatting().getSecondary_text()!="secondary_text") {
            viewHolder.textViewMain.setText(placeModel.getPredictions().get(position).getStructured_formatting().getMain_text());
        }
        else
        {
            viewHolder.linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(mcontext,R.drawable.red_stroke_corner_square));
            viewHolder.textViewMain.setText(placeModel.getPredictions().get(position).getStructured_formatting().getMain_text());
        }
        viewHolder.textViewSecondary.setText(placeModel.getPredictions().get(position).getStructured_formatting().getSecondary_text());



    }

    @Override
    public int getItemCount() {
        return predictionsList.size();
    }

    public class AutoCompleteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMain,textViewSecondary;
        LinearLayout linearLayout;
        public AutoCompleteViewHolder(View itemView) {
            super(itemView);

            textViewMain=itemView.findViewById(R.id.tv_main);
            textViewSecondary=itemView.findViewById(R.id.tv_secondary);
            linearLayout=itemView.findViewById(R.id.place_layout);
        }
    }
}
