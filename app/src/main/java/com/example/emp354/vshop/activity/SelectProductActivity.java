package com.example.emp354.vshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emp354.vshop.R;

public class SelectProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvTitle,tvNewPrice,tvOldPrice;
    ImageView ivSelectProductImage;

    int[] images={R.drawable.cloth_1,R.drawable.cloth_2,
            R.drawable.cloth_3,R.drawable.cloth_4,
            R.drawable.cloth_5,R.drawable.cloth_6,
            R.drawable.cloth_7,R.drawable.cloth_8,
            R.drawable.cloth_9,R.drawable.cloth_10,};
    String title[]={"Product1","Product2","Product3","Product4","Product5","Product6","Product7","Product8","Product9","Product10"};
    String price[]={"$1.00","$2.00","$3.00","$4.00","$5.00","$6.00","$7.00","$8.00","$9.00","$10.00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        Intent selectProductIntent=getIntent();
        int pos=selectProductIntent.getIntExtra("position",-1);

        toolbar=findViewById(R.id.toolbar_select_product);

        tvTitle=findViewById(R.id.tv_select_product_title);
        tvNewPrice=findViewById(R.id.tv_select_product_new_price);
        tvOldPrice=findViewById(R.id.tv_select_product_old_price);
        ivSelectProductImage=findViewById(R.id.iv_select_product_image);
        ivSelectProductImage.getLayoutParams().height=getHeight();

        setToolbar();
        ivSelectProductImage.setImageDrawable(getResources().getDrawable(images[pos]));
        toolbar.setTitle(title[pos]);
        tvTitle.setText(title[pos]);
        tvNewPrice.setText(price[pos]);
        tvOldPrice.setText(price[pos]);



    }

    private int getHeight()
    {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels;
        return height*65/100;
    }



    private void setToolbar()
    {
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
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_bag:
                        Intent intent=new Intent(SelectProductActivity.this,ShoppingBagActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
    }
}
