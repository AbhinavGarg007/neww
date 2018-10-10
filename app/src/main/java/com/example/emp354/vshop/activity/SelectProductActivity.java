package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.emp354.vshop.R;
import com.example.emp354.vshop.adapter.SizeRecyclerAdapter;
import com.example.emp354.vshop.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SelectProductActivity extends AppCompatActivity implements View.OnClickListener,ItemClickListener {

    //declaring variables
    Toolbar toolbar;
    TextView tvTitle, tvNewPrice,tvTitleToolbar, tvOldPrice,tvSize26,tvSize28,tvSize30,tvSize32,tvSize34,tvSize36,tvSize38;
    ImageView ivSelectProductImage;
    LinearLayout layoutSearch;
    Button btnQuantity, btnAddToCart;
    RecyclerView recyclerView;
    List<Integer> isSizeSelected;
    EditText etSearch;
    boolean isSearchOpen=false;
    SizeRecyclerAdapter sizeRecyclerAdapter;

    //declaring static data to perform operation
    int[] images = {R.drawable.cloth_1, R.drawable.cloth_2,
            R.drawable.cloth_3, R.drawable.cloth_4,
            R.drawable.cloth_5, R.drawable.cloth_6,
            R.drawable.cloth_7, R.drawable.cloth_8,
            R.drawable.cloth_9, R.drawable.cloth_10,};
    String title[] = {"Product1", "Product2", "Product3", "Product4", "Product5", "Product6", "Product7", "Product8", "Product9", "Product10"};
    String price[] = {"$1.00", "$2.00", "$3.00", "$4.00", "$5.00", "$6.00", "$7.00", "$8.00", "$9.00", "$10.00"};

    //hashmap to check whether size is available or not
    HashMap<String,String> isSizeAvailable=new HashMap<>();
    /*String size[]={"26","28","30","32","34","36","38"};*/
    List<String> size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        //getting data from intent
        Intent selectProductIntent = getIntent();
        int pos = selectProductIntent.getIntExtra("position", -1);

        //initialising variables
        recyclerView=findViewById(R.id.recyclerview_size);
        layoutSearch=findViewById(R.id.layout_search);
        etSearch=findViewById(R.id.et_search);

        toolbar = findViewById(R.id.toolbar_select_product);
        tvTitle = findViewById(R.id.tv_select_product_title);
        tvTitleToolbar=findViewById(R.id.tv_title_select_product_activity);
        tvNewPrice = findViewById(R.id.tv_select_product_new_price);
        tvOldPrice = findViewById(R.id.tv_select_product_old_price);
        ivSelectProductImage = findViewById(R.id.iv_select_product_image);
        btnQuantity = findViewById(R.id.btn_quantity);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);

        //calling methods
        setToolbar();
        getavailability();




        isSizeSelected=Arrays.asList(-1);
        size=new ArrayList<String>(isSizeAvailable.keySet());
        ivSelectProductImage.getLayoutParams().height = getHeight();


        //setting adapter to recyclerview
        sizeRecyclerAdapter=new SizeRecyclerAdapter(this,size,this,isSizeSelected,isSizeAvailable);
        recyclerView.setAdapter(sizeRecyclerAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

       /* tvSize26=findViewById(R.id.tv_size_26);
        tvSize28=findViewById(R.id.tv_size_28);
        tvSize30=findViewById(R.id.tv_size_30);
        tvSize32=findViewById(R.id.tv_size_32);
        tvSize34=findViewById(R.id.tv_size_34);
        tvSize36=findViewById(R.id.tv_size_36);
        tvSize38=findViewById(R.id.tv_size_38);*/
       /* setSelector();*/



        //setting onClickListener
        btnQuantity.setOnClickListener(this);
        btnAddToCart.setOnClickListener(this);
        /*tvSize26.setOnClickListener(this);
        tvSize28.setOnClickListener(this);
        tvSize30.setOnClickListener(this);
        tvSize32.setOnClickListener(this);
        tvSize34.setOnClickListener(this);
        tvSize36.setOnClickListener(this);
        tvSize38.setOnClickListener(this);*/


        ivSelectProductImage.setImageDrawable(getResources().getDrawable(images[pos]));

        //setting passed data to layout
       tvTitleToolbar.setText(title[pos]);
        tvTitle.setText(title[pos]);
        tvNewPrice.setText(price[pos]);
        tvOldPrice.setText(price[pos]);
        tvOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);


    }

    //getting height on runtime
    private int getHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height * 65 / 100;
    }


    //methos to set toolbar
    private void setToolbar() {
        toolbar.inflateMenu(R.menu.menu_icon);

        toolbar.getMenu().findItem(R.id.edit).setVisible(false);
        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_bag:
                        Intent intent = new Intent(SelectProductActivity.this, EmptyShoppingBagActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.navigation_search:
                        isSearchOpen=true;
                        toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(false);
                        toolbar.getMenu().findItem(R.id.navigation_search).setVisible(false);
                        tvTitleToolbar.setVisibility(View.GONE);

                        layoutSearch.setVisibility(View.VISIBLE);
                        break;

                }
                return true;
            }
        });
    }

    //method to perform operation on onClick
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_quantity:
                PopupMenu popup = new PopupMenu(SelectProductActivity.this, btnQuantity);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        btnQuantity.setText(getString(R.string.quantity) + item.getTitle());

                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;

            case R.id.btn_add_to_cart:
                Intent selectProductIntent=new Intent(SelectProductActivity.this,ShoppingBagActivity.class);
                startActivity(selectProductIntent);

            /*case R.id.tv_size_26:
                tvSize26.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize26.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_28:
                tvSize28.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize28.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_30:
                tvSize30.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize30.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_32:
                tvSize32.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize32.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_34:
                tvSize34.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize34.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_36:
                tvSize36.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize36.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
            case R.id.tv_size_38:
                tvSize38.setTextColor(getResources().getColorStateList(R.color.size_color_selector));
                tvSize38.setBackground(getResources().getDrawable(R.drawable.size_selector));
                break;
*/


        }

    }

    //method to check whether list contains position or not
    // on that basis we are setting background nad text color
    @Override
    public void onItemClick(View view, int position) {
        if(!isSizeSelected.contains(position))
        {
            isSizeSelected.set(0,position);
            sizeRecyclerAdapter.notifyDataSetChanged();
        }
    }

    //onBackPressed method
    @Override
    public void onBackPressed() {
        searchStateCheck();

    }


    //method to put data in hashmap
    public void getavailability()
    {
        isSizeAvailable.put("26","available");
        isSizeAvailable.put("28","unavailable");
        isSizeAvailable.put("30","available");
        isSizeAvailable.put("32","unavailable");
        isSizeAvailable.put("34","available");
        isSizeAvailable.put("36","unavailable");
        isSizeAvailable.put("38","available");
    }

    //method to check whether search bar is open or not
    public void searchStateCheck()
    {
        if(isSearchOpen)
        {isSearchOpen=false;
            layoutSearch.setVisibility(View.GONE);
            toolbar.getMenu().findItem(R.id.navigation_bag).setVisible(true);
            toolbar.getMenu().findItem(R.id.navigation_search).setVisible(true);
            tvTitleToolbar.setVisibility(View.VISIBLE);

        }
        else { finish();
        }
    }



    private void setSelector()
    {

        tvSize26.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize26.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize28.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize28.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize30.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize30.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize32.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize32.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize34.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize34.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize36.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize36.setBackground(getResources().getDrawable(R.drawable.size_selector));
        tvSize38.setTextColor(getResources().getColor(R.color.size_color_selector));
        tvSize38.setBackground(getResources().getDrawable(R.drawable.size_selector));
    }
}

