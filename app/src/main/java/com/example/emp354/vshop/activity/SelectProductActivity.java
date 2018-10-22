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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.example.emp354.vshop.constants.Constant.CLOTHES;
import static com.example.emp354.vshop.constants.Constant.DRAWABLE_INITIAL_PATH;
import static com.example.emp354.vshop.constants.Constant.PRICE;
import static com.example.emp354.vshop.constants.Constant.TITLE;

public class SelectProductActivity extends AppCompatActivity implements View.OnClickListener,ItemClickListener {

    //declaring variables
    Toolbar toolbar;
    TextView tvTitle, tvNewPrice,tvTitleToolbar, tvOldPrice,tvQuantity,tvAddToCart,tvLike,tvSize26,tvSize28,tvSize30,tvSize32,tvSize34,tvSize36,tvSize38;
    ImageView ivSelectProductImage,ivLike;
    LinearLayout layoutSearch,llQuantity;

    RecyclerView recyclerView;
    List<Integer> isSizeSelected;
    EditText etSearch;
    boolean isSearchOpen=false;
    SizeRecyclerAdapter sizeRecyclerAdapter;
    boolean isLiked=false;
    int likes=95;

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
        etSearch=findViewById(R.id.et_search_select_product);
        llQuantity=findViewById(R.id.ll_quantity);

        toolbar = findViewById(R.id.toolbar_select_product);
        tvTitle = findViewById(R.id.tv_select_product_title);
        tvTitleToolbar=findViewById(R.id.tv_title_select_product_activity);
        tvNewPrice = findViewById(R.id.tv_select_product_new_price);
        tvOldPrice = findViewById(R.id.tv_select_product_old_price);
        ivSelectProductImage = findViewById(R.id.iv_select_product_image);
        tvQuantity = findViewById(R.id.btn_quantity);
        tvAddToCart = findViewById(R.id.btn_add_to_cart);

        ivLike=findViewById(R.id.iv_like);
        tvLike=findViewById(R.id.tv_like);

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
        llQuantity.setOnClickListener(this);
        tvAddToCart.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        /*tvSize26.setOnClickListener(this);
        tvSize28.setOnClickListener(this);
        tvSize30.setOnClickListener(this);
        tvSize32.setOnClickListener(this);
        tvSize34.setOnClickListener(this);
        tvSize36.setOnClickListener(this);
        tvSize38.setOnClickListener(this);*/


        /*ivSelectProductImage.setImageDrawable(getResources().getDrawable(CLOTHES[pos]));*/
        ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH + CLOTHES[pos],ivSelectProductImage);

        //setting passed data to layout
       tvTitleToolbar.setText(TITLE[pos]);
        tvTitle.setText(TITLE[pos]);
        tvNewPrice.setText(PRICE[pos]);
        tvOldPrice.setText(PRICE[pos]);
        tvOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvLike.setText(likes+" Likes");


    }

    //getting height on runtime
    private int getHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height * 55 / 100;
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


            case R.id.iv_like:

                if(!isLiked)
                {
                    isLiked=true;
                    likes++;
                    ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH+R.drawable.ic_likeselected,ivLike);
                    tvLike.setText(likes+" Likes");
                }
                else
                {
                    isLiked=false;
                    likes--;

                    ImageLoader.getInstance().displayImage(DRAWABLE_INITIAL_PATH+R.drawable.ic_like,ivLike);
                    tvLike.setText(likes+" Likes");
                }

                break;
            case R.id.ll_quantity:
                PopupMenu popup = new PopupMenu(SelectProductActivity.this, llQuantity);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        tvQuantity.setText(getString(R.string.quantity) + item.getTitle());

                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;

            case R.id.btn_add_to_cart:
                Intent selectProductIntent=new Intent(SelectProductActivity.this,ShoppingBagActivity.class);
                startActivity(selectProductIntent);
                SelectProductActivity.this.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
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

