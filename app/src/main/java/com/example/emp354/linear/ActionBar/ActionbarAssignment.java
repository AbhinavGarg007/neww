package com.example.emp354.linear.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class ActionbarAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbarassignment);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.search:

                return true;
            case R.id.gps:
                TextView count=(TextView)findViewById(R.id.textview);
                count.setText("Looking for your location");
                return (true);
            case R.id.refresh:
                Toast.makeText(this, R.string.refresh_toast, Toast.LENGTH_LONG).show();
                return(true);
            case R.id.help:
                Toast.makeText(this, R.string.help_toast, Toast.LENGTH_LONG).show();
                return (true);
            case R.id.check_for_updates:
                Toast.makeText(this, R.string.check_for_updates_toast, Toast.LENGTH_LONG).show();
                return (true);

        }
        return super.onOptionsItemSelected(item);
    }
}
