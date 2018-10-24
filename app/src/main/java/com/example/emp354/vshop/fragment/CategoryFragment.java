package com.example.emp354.vshop.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.emp354.vshop.ProductModel;
import com.example.emp354.vshop.R;
import com.example.emp354.vshop.activity.HomeActivity;
import com.example.emp354.vshop.adapter.CategoryAdapter;
import com.example.emp354.vshop.constants.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryFragment extends Fragment {
    //declaring member variables
    ProductModel productModel;
    CategoryAdapter categoryAdapter;
    List<ProductModel> productModels;
    HashMap<String, List<String>> childList;
    ExpandableListView expandableListView;
    int requiredHeight;

    @Nullable
    @Override

    //inflating layout
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_category, container, false);
        return view;
    }

    //after view created operation to be performed will be written here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((HomeActivity) getActivity()).checkFragment();

        //getting height on run time
        if (((HomeActivity) getActivity()).getHeight() != 0) {
            requiredHeight = ((HomeActivity) getActivity()).getHeight() * 14 / 100;
        }
        expandableListView = view.findViewById(R.id.expandable_listview);

        childList = new HashMap<>();
        productModels = new ArrayList<>();
        initData();
        categoryAdapter = new CategoryAdapter(getActivity(), productModels, childList, requiredHeight);
        expandableListView.setAdapter(categoryAdapter);


    }

    //initialising data set
    private void initData() {
        //list containing product name
        List<String> productName = new ArrayList<>();
        productName.add("Hats");
        productName.add("Clocks");
        productName.add("Shorts");
        productName.add("Bags");
        productName.add("Jeans");
        productName.add("Shoes");


        //hashmap containing discount w.r.t to the product name
        HashMap<String, String> productDiscount = new HashMap<>();
        productDiscount.put("Hats", "20% discount for Ladies Hats");
        productDiscount.put("Clocks", "15% discount for Ladies Clocks");
        productDiscount.put("Shorts", "");
        productDiscount.put("Bags", "");
        productDiscount.put("Jeans", "");
        productDiscount.put("Shoes", "");

        //hashmap containing image w.r.t to the product name
        HashMap<String, Integer> productImage = new HashMap<>();
        productImage.put("Hats", R.drawable.category1);
        productImage.put("Clocks", R.drawable.category2);
        productImage.put("Shorts", R.drawable.category3);
        productImage.put("Bags", R.drawable.category4);
        productImage.put("Jeans", R.drawable.category5);
        productImage.put("Shoes", R.drawable.category6);

        //hashmap containing background color w.r.t to the product name
        HashMap<String, Integer> productBackground = new HashMap<>();
        productBackground.put("Hats", Constant.SEL_BROWN);
        productBackground.put("Clocks", Constant.SEL_PINK);
        productBackground.put("Shorts", Constant.SEL_GREEN);
        productBackground.put("Bags", Constant.SEL_WHITE);
        productBackground.put("Jeans", Constant.SEL_PINK);
        productBackground.put("Shoes", Constant.SEL_BROWN);

        //inserting data into model and then model into list of model class
        for (int i = 0; i < productName.size(); i++) {
            productModel = new ProductModel();
            productModel.setName(productName.get(i));
            productModel.setDiscount(productDiscount.get(productName.get(i)));
            productModel.setImage(productImage.get(productName.get(i)));
            productModel.setBackground(productBackground.get(productName.get(i)));

            productModels.add(productModel);
        }


        //list containing category child hats entries
        List<String> hats = new ArrayList<>();
        hats.add("Ascoot Cap");
        hats.add("Beanie Cap");
        hats.add("Capotain Cap");
        hats.add("Draped Turban");
        hats.add("Fedora Cap");
        hats.add("Gaung Paung Cap");

        //list containing category child clocks entries
        List<String> clocks = new ArrayList<>();
        clocks.add("Hand clock");
        clocks.add("Wall clock");

        //list containing category child shorts entries
        List<String> shorts = new ArrayList<>();
        shorts.add("one");
        shorts.add("two");
        shorts.add("three");

        //list containing category child bags entries
        List<String> bags = new ArrayList<>();
        bags.add("one");
        bags.add("two");

        //list containing category child jeans entries
        List<String> jeans = new ArrayList<>();
        jeans.add("one");
        jeans.add("two");
        jeans.add("jeans");

        //list containing category child shoes entries
        List<String> shoes = new ArrayList<>();
        shoes.add("shoes");
        shoes.add("shoes");


        //putting each list into hashmap mapping with the title of the product
        childList.put(productName.get(0), hats);
        childList.put(productName.get(1), clocks);
        childList.put(productName.get(2), shorts);
        childList.put(productName.get(3), bags);
        childList.put(productName.get(4), jeans);
        childList.put(productName.get(5), shoes);

    }
}
