package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter {
    //decaring variables
    Context mContext;
    String[] mType;
    String[] mDiscounts;
    int[] mImage;
    int mHeight;


    // constructor of adapter
    public CategoriesRecyclerAdapter(Context context,String[] type,String[] discount,int[] image,int height)
    {
        mContext=context;
        mType=type;
        mDiscounts=discount;
        mImage=image;
        mHeight=height;
    }


    //inflating layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_categories,viewGroup,false);
        CategoriesHolder categoriesHolder=new CategoriesHolder(view);
        return categoriesHolder;
    }

    //binding layout to the data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        CategoriesHolder categoriesHolder=(CategoriesHolder) viewHolder;
        categoriesHolder.tvType.setText(mType[i]);
        categoriesHolder.tvDiscount.setText(mDiscounts[i]);
       /* categoriesHolder.ivCategory.setImageResource(mImage[i]);*/
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + mImage[i],categoriesHolder.ivCategory);
    }

    //getting count for number of items
    @Override
    public int getItemCount() {
        return 6;
    }


    //holder for the adapter
    public class CategoriesHolder extends RecyclerView.ViewHolder {

        //declaring variables
        TextView tvType,tvDiscount;
        ImageView ivCategory;
        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);

            //initialising variables
            tvType=itemView.findViewById(R.id.tv_type_category);
            tvDiscount=itemView.findViewById(R.id.tv_discount_category);
            ivCategory=itemView.findViewById(R.id.iv_category);
            ivCategory.getLayoutParams().height=mHeight;
        }
    }
}
