package com.example.emp354.linear.AutoComplete;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class SearchScreen extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        editText=findViewById(R.id.et_search_screen);
        button=findViewById(R.id.btn_search_screen);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_keyword=editText.getText().toString();
                boolean networkOK = SearchScreen.this.checkInternetConnection();

                if(search_keyword==null || !networkOK) {
                    Toast.makeText(SearchScreen.this, "Please enter search keyword or check network connection", Toast.LENGTH_SHORT).show();
                }
                   /* if(!networkOK) {
                        Toast.makeText(SearchScreen.this, "Please check network connection", Toast.LENGTH_SHORT).show();
                    }
                    if()
                    {
                        Toast.makeText(SearchScreen.this,"Please enter search keyword.",Toast.LENGTH_SHORT).show();
                    }*/

                   else {
                    Intent searchIntent=new Intent(SearchScreen.this,ListScreen.class);
                    searchIntent.putExtra("input",search_keyword);
                    startActivity(searchIntent);
                }
                }



        });
    }

    private boolean checkInternetConnection() {
        // Get Connectivity Manager
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Details about the currently active default data network
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }
}
