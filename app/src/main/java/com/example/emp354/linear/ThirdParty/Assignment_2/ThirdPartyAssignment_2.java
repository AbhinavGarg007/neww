package com.example.emp354.linear.ThirdParty.Assignment_2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.emp354.linear.JSONUtils;
import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.POJO.Images;
import com.example.emp354.linear.ThirdParty.POJO.ObjectPojoThirdParty;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * it is not working
 * i have made the same assignment with name Assignment_2_again
 */

public class ThirdPartyAssignment_2 extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    MyStickyHeaderAdapter myStickyHeaderAdapter;
    AsyncTaskLoadImages asyncTaskLoadImages=new AsyncTaskLoadImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_assignment_2);



        recyclerView = findViewById(R.id.recycler_view_third_party_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(myStickyHeaderAdapter));*/

        StickyRecyclerHeadersDecoration stickyRecyclerHeadersDecoration=new StickyRecyclerHeadersDecoration(myStickyHeaderAdapter);


        StickyRecyclerHeadersTouchListener touchListener=new StickyRecyclerHeadersTouchListener(recyclerView,stickyRecyclerHeadersDecoration);
        touchListener.setOnHeaderClickListener(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View header, int position, long headerId) {
                Toast.makeText(ThirdPartyAssignment_2.this, "Header position: " + position + ", id: " + headerId,
                        Toast.LENGTH_SHORT).show();
            }
        });

      /*  recyclerView.addOnItemTouchListener(touchListener);*/


        asyncTaskLoadImages.execute();


    }

    private class AsyncTaskLoadImages extends AsyncTask<Void, Void, ObjectPojoThirdParty> {

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(ThirdPartyAssignment_2.this, "Loading Images", "Please Wait..");

        }

        @Override
        protected void onPostExecute(ObjectPojoThirdParty objectPojoThirdParty) {


            myStickyHeaderAdapter=new MyStickyHeaderAdapter(ThirdPartyAssignment_2.this,objectPojoThirdParty);
            recyclerView.setAdapter(myStickyHeaderAdapter);

            dialog.dismiss();


        }


        @Override
        protected ObjectPojoThirdParty doInBackground(Void... voids) {

            return loadImages();

        }


        private ObjectPojoThirdParty loadImages() {
            String jsondata = loadJSONFromAsset();

            ObjectPojoThirdParty objectPojoThirdParty = new ObjectPojoThirdParty();

            JSONObject jsonObject = JSONUtils.getJSONObject(jsondata);


            JSONArray jsonImagesArray = JSONUtils.getJSONArray(jsonObject, "images");

            List<Images> imagesList = new ArrayList<>();

            for (int i = 0; i < JSONUtils.getLengthOfJSONArray(jsonImagesArray); i++) {
                JSONObject jsonImageObject = JSONUtils.getJSONObject(jsonImagesArray, i);

                Images images = new Images();

                images.setName(JSONUtils.getStringfromJSON(jsonImageObject, "name"));

                JSONArray jsonValuesArray = JSONUtils.getJSONArray(jsonImageObject, "values");

                List<String> valuesList = new ArrayList<>();

                for (int j = 0; j < JSONUtils.getLengthOfJSONArray(jsonValuesArray); j++) {
                    valuesList.add(JSONUtils.getStringObject(jsonValuesArray, j));
                }

                images.setValues(valuesList);

                imagesList.add(images);
            }

            objectPojoThirdParty.setImages(imagesList);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return objectPojoThirdParty;
        }


        private String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("images.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }


    }

}
