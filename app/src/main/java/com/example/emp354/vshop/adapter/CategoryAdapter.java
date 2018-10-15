package com.example.emp354.vshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emp354.vshop.ProductModel;
import com.example.emp354.vshop.R;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.HashMap;
import java.util.List;

import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;

public class CategoryAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ProductModel> mProduct;
    private LayoutInflater mInflater;
    private HashMap<String,List<String>> mChildList;
    private int mHeight;

    //creating adapter constructor
    public CategoryAdapter(Context context, List<ProductModel> productModelArrayList, HashMap<String,List<String>> childList,int height)
    {
        mContext=context;
        mProduct=productModelArrayList;
        mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChildList=childList;
        mHeight=height;
    }

    //method to get group count
    @Override
    public int getGroupCount() {
        return mProduct.size();
    }

    //method to get child count
    @Override
    public int getChildrenCount(int i) {
        return mChildList.get(mProduct.get(i).getName()).size();
    }

    //method to get group
    @Override
    public List<ProductModel> getGroup(int i) {
        return mProduct;
    }

    //to get a single product
    @Override
    public String getChild(int gpos, int cpos) {
        return mChildList.get(mProduct.get(gpos).getName()).get(cpos);
    }

    //method to get group id
    @Override
    public long getGroupId(int i) {
        return i;
    }


    //method to get child id
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //method to get group view
    @Override
    public View getGroupView(int groupPos, boolean b, View view, ViewGroup viewGroup) {
        //checking whether view is inflated or not already
        if(view==null)
        {
            view=mInflater.inflate(R.layout.layout_item_categories,viewGroup,false);
        }

        //getting data
        String name=getGroup(groupPos).get(groupPos).getName();
        String discount=getGroup(groupPos).get(groupPos).getDiscount();
        int image=getGroup(groupPos).get(groupPos).getImage();
        int background=getGroup(groupPos).get(groupPos).getBackground();


        //initialising view
        TextView tvName=view.findViewById(R.id.tv_type_category);
        TextView tvDiscount=view.findViewById(R.id.tv_discount_category);
        ImageView ivCategory=view.findViewById(R.id.iv_category);
        ivCategory.getLayoutParams().height=mHeight;
        LinearLayout llCategory=view.findViewById(R.id.layout_inner);

        //setting values to the views
        tvName.setText(name);
        tvDiscount.setText(discount);
        /*ivCategory.setImageResource(image);*/
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + image,ivCategory);
        llCategory.setBackgroundColor(background);

        return view;
    }

    //method to get child vew
    @Override
    public View getChildView(int groupPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=mInflater.inflate(R.layout.layout_item_categories_child,viewGroup,false);
        }

        //getting data
        String variety=getChild(groupPos,childPos);

        //initialising views
        TextView tvVariety=view.findViewById(R.id.tv_product_variety);
        LinearLayout llVariety=view.findViewById(R.id.layout_product_variety);
        tvVariety.setText(variety);

        //checking position to set different backgrounds
        if(childPos%2==0)
        {
            llVariety.setBackgroundColor(mContext.getResources().getColor(R.color.color_list));
        }
        else
        {
            llVariety.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }



        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
