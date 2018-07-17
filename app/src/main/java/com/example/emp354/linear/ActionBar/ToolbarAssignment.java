package com.example.emp354.linear.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class ToolbarAssignment extends AppCompatActivity {
    private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_assignment);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.search:

                return true;
            case R.id.gps:
                Toast.makeText(this, R.string.gps_toast, Toast.LENGTH_LONG).show();

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
