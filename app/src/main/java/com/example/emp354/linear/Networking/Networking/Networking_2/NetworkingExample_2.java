package com.example.emp354.linear.Networking.Networking.Networking_2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emp354.linear.R;

public class NetworkingExample_2 extends AppCompatActivity {



    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking_assignment_2);

        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        this.textView = (TextView) this.findViewById(R.id.textView);
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

    // When user click on the "Download Image".
    public void downloadAndShowImage(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        String imageUrl = "https://o7planning.org/download/static/default/demo-data/logo.png";

        // Create a task to download and display image.
        DownloadImageTask task = new DownloadImageTask(this.imageView);

        // Execute task (Pass imageUrl).
        task.execute(imageUrl);
    }

    // When user click on the "Download Json".
    public void downloadAndShowJson(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        String jsonUrl = "https://o7planning.org/download/static/default/demo-data/company.json";

        // Create a task to download and display json content.
        DownloadJsonTask task = new DownloadJsonTask(this.textView);

        // Execute task (Pass jsonUrl).
        task.execute(jsonUrl);
    }
}
