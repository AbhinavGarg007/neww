package com.example.emp354.linear.ThirdParty.Assignment_1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emp354.linear.JSONUtils;
import com.example.emp354.linear.R;
import com.example.emp354.linear.ThirdParty.POJO.Images;
import com.example.emp354.linear.ThirdParty.POJO.ObjectPojoThirdParty;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThirdPartyAssignment_1 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ThirdPartyViewPagerAdapter thirdPartyViewPagerAdapter;
    ProgressDialog dialog;
    AsyncTaskLoadImages asyncTaskLoadImages=new AsyncTaskLoadImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_assignment_1);

        tabLayout=findViewById(R.id.tablayout_third_party);
        viewPager=findViewById(R.id.viewpager_third_party);

        asyncTaskLoadImages.execute();


        tabLayout.setupWithViewPager(viewPager);
    }

    private class AsyncTaskLoadImages extends AsyncTask<Void,Void,ObjectPojoThirdParty>
    {

        @Override
        protected void onPreExecute() {
            dialog=ProgressDialog.show(ThirdPartyAssignment_1.this,"Loading Images","Please Wait..");

        }

        @Override
        protected void onPostExecute(ObjectPojoThirdParty objectPojoThirdParty) {


            thirdPartyViewPagerAdapter =new ThirdPartyViewPagerAdapter(ThirdPartyAssignment_1.this,getSupportFragmentManager(),objectPojoThirdParty);
            viewPager.setAdapter(thirdPartyViewPagerAdapter);
            dialog.dismiss();


        }


        @Override
        protected ObjectPojoThirdParty doInBackground(Void... voids) {

            return loadImages();

        }



        private ObjectPojoThirdParty loadImages()
        {
            String jsondata=loadJSONFromAsset();

            ObjectPojoThirdParty objectPojoThirdParty=new ObjectPojoThirdParty();

            JSONObject jsonObject= JSONUtils.getJSONObject(jsondata);


            JSONArray jsonImagesArray=JSONUtils.getJSONArray(jsonObject,"images");

            List<Images> imagesList=new ArrayList<>();

            for(int i=0;i<JSONUtils.getLengthOfJSONArray(jsonImagesArray);i++)
            {
                JSONObject jsonImageObject=JSONUtils.getJSONObject(jsonImagesArray,i);

                Images images=new Images();

                images.setName(JSONUtils.getStringfromJSON(jsonImageObject,"name"));

                JSONArray jsonValuesArray=JSONUtils.getJSONArray(jsonImageObject,"values");

                List<String> valuesList=new ArrayList<>();

                for(int j=0;j<JSONUtils.getLengthOfJSONArray(jsonValuesArray);j++)
                {
                    valuesList.add(JSONUtils.getStringObject(jsonValuesArray,j));
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



        private String loadJSONFromAsset()
        {
            String json=null;
            try
            {
                InputStream is=getAssets().open("images.json");
                int size=is.available();
                byte[] buffer=new byte[size];
                is.read(buffer);
                is.close();
                json=new String(buffer,"UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }


    }


}
