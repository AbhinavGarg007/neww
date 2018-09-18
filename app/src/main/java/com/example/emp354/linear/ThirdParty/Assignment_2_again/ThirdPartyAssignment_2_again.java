package com.example.emp354.linear.ThirdParty.Assignment_2_again;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.emp354.linear.JSONUtils;
import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.Assignment_2.MyStickyHeaderAdapter;
import com.example.emp354.linear.ThirdParty.Assignment_2.ThirdPartyAssignment_2;
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

public class ThirdPartyAssignment_2_again extends AppCompatActivity {


        RecyclerView recyclerView;
        ProgressDialog dialog;
        MyStickyHeaderAdapter_again myStickyHeaderAdapter;
        AsyncTaskLoadImages asyncTaskLoadImages=new AsyncTaskLoadImages();
        List<String> mUrlList=new ArrayList<>();
        List<String> mTitleList=new ArrayList<>();
        List<Images> imagesList;

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
                    Toast.makeText(ThirdPartyAssignment_2_again.this, "Header position: " + position + ", id: " + headerId,
                            Toast.LENGTH_SHORT).show();
                }
            });

              recyclerView.addOnItemTouchListener(touchListener);
              asyncTaskLoadImages.execute();


        }

        //async task to perform fetching google images in background
        private class AsyncTaskLoadImages extends AsyncTask<Void, Void, ObjectPojoThirdParty> {

            //in onpreExecute() showing dialog
            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(ThirdPartyAssignment_2_again.this, "Loading Images", "Please Wait..");

            }

            /**
             *  in onPostExecute ,passing titleList and urlList in adapter
             *  also dismissing the dialog
             */

            @Override
            protected void onPostExecute(ObjectPojoThirdParty objectPojoThirdParty) {

                imagesList=objectPojoThirdParty.getImages();
                int size=imagesList.size();
                for(int i=0;i<size;i++) {
                    Images images=imagesList.get(i);


                    for(int j=0;j<images.getValues().size();j++) {
                        //adding urls in list
                        mUrlList.add(images.getValues().get(j));
                        //adding titles in list
                        mTitleList.add(images.getName());

                       /*mUrlList.addAll(images.getValues());*/

                    }
                }

                myStickyHeaderAdapter=new MyStickyHeaderAdapter_again(ThirdPartyAssignment_2_again.this,mTitleList,mUrlList);
                //setting adapter
                recyclerView.setAdapter(myStickyHeaderAdapter);
                //setting decor on recyclerview
                recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(myStickyHeaderAdapter));

                dialog.dismiss();


            }



            //performing background operation
            @Override
            protected ObjectPojoThirdParty doInBackground(Void... voids) {
                return loadImages();
            }


            //parsing json file
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


            //getting string from given json file
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

