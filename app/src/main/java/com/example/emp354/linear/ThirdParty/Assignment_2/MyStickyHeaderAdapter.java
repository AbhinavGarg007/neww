package com.example.emp354.linear.ThirdParty.Assignment_2;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiLikeAdapter;
import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.POJO.Images;
import com.example.emp354.linear.ThirdParty.POJO.ObjectPojoThirdParty;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * it is not working
 * i have made the same assignment with name Assignment_2_again
 */
public class MyStickyHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    private List<Images> imagesList;
    private String name;
    List<String> values;
    Images images;



    public MyStickyHeaderAdapter(Context context, ObjectPojoThirdParty objectPojoThirdParty)
    {
        mcontext=context;
       if(objectPojoThirdParty!=null)
       {
           imagesList=objectPojoThirdParty.getImages();
           /*int size=imagesList.size();

           for(int i=0;i<size;i++) {
               images = imagesList.get(i);
               values = images.getValues();
               name = images.getName();
           }*/

       }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_thirdparty_2,parent,false);
        return new ImageViewHolder(view){};

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*ImageView imageView=(ImageView)holder.itemView;*/
        ImageViewHolder imageViewHolder=(ImageViewHolder)holder;
        ImageLoader imageLoader=ImageLoader.getInstance();

        int size=imagesList.size();

        for(int i=0;i<size;i++)
        {
            images=imagesList.get(i);
            values=images.getValues();
            imageLoader.displayImage(values.get(position),imageViewHolder.imageView_2);

        }


        /*Images images=imagesList.get(position);
        values=images.getValues();

        for(int i=0;i<values.size();i++) {

            imageLoader.displayImage(values.get(i), myViewHolder.imageView);
        }*/

    }

    @Override
    public long getHeaderId(int position) {
        return imagesList.get(position).getName().charAt(position);


    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.header_third_party,parent,false);
        return new TextViewHolder(view) {};

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*TextView textView=(TextView)holder.itemView;*/
        TextViewHolder textViewHolder = (TextViewHolder) holder;

        int size = imagesList.size();
        for (int j = 0; j < size; j++) {
            images = imagesList.get(j);
            name = images.getName();

            textViewHolder.textView_2.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder
    { ImageView imageView_2;
    public ImageViewHolder(View itemView) {
            super(itemView);
            imageView_2=itemView.findViewById(R.id.imageView_third_party_2);
        }
    }


    public class TextViewHolder extends RecyclerView.ViewHolder
    {TextView textView_2;
    public TextViewHolder(View itemView) {
            super(itemView);
            textView_2=itemView.findViewById(R.id.textView_third_party);
        }
    }
}
