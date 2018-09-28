package com.example.emp354.linear.CommunicatingWithServer.ImageSearch;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

public class DownloadIntentService extends IntentService {

    Bitmap bitmap;
    String message;


    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getStringExtra("url") != null) {

            String url = intent.getStringExtra("url");

           String message=downloadImage(url);

            Intent intent1=new Intent("image_data");
            intent1.putExtra("message",message);
            intent1.putExtra("pos",intent.getStringExtra("pos"));
            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent1);
        }

    }

    private String  downloadImage(String url) {

        try {
            URL imageUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            if (bitmap != null) {

                File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "search_images");
                if (!dir.exists()) {
                    dir.mkdir();
                }

                /*String fileName=getFileName(searchedText,imagePosition);*/
                /*String fileName="gygbhgh.jpg";*/
                /*int index=url.indexOf("q=");
                Log.d("tag",String.valueOf(index));
                fileName=url.substring((index+2),url.length());
                Log.d("tag",fileName);

*/
                String fileName = url.substring(url.length() - 10);
                File file = new File(dir, fileName + ".jpg");


                    try {
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);

                        boolean flag = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Thread.sleep(1000);
                        /*if (flag) {
                            message = "DOWNLOAD_SUCCESS_MESSAGE";
                        }*/
                        Log.d("ImageSearch", "Image Downloaded");
                        message="Image Downloaded.";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            } catch(Exception e){
                e.printStackTrace();

            }



               /* File root = Environment.getExternalStorageDirectory();
                File myDir = new File(root.getAbsoluteFile(), ".search_images");

                if (!myDir.exists()) {
                    myDir.mkdir();
                    Log.d("tag", "Directory created");

                }


                String name = "a1";
                File myFile = new File(myDir.getAbsoluteFile(), name + ".jpg");
                myFile.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(myFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(getBaseContext(), "Image is downloaded.", Toast.LENGTH_SHORT).show();


            }
        }catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }*/


/*-------------------------------------------------------------------------------------------*/
        /*Picasso.with(this)
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try
                        {
                            String root= Environment.getExternalStorageDirectory().toString();
                            File myDir=new File(root+"/search_images");

                            if(!myDir.exists())
                            {
                                myDir.mkdirs();
                                Log.d("tag","Directory created");

                            }

                            String name=new Date().toString()+".jpg";
                            myDir=new File(myDir,name);
                            FileOutputStream outputStream = new FileOutputStream(myDir);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(getBaseContext(),"Image is downloaded.",Toast.LENGTH_SHORT).show();

                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        Log.d("tag","onBitMapFailed");
                        Toast.makeText(getBaseContext(),"Downloading Failed.",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.d("tag","onPrepareLoad");
                        *//**//* Toast.makeText(ImageSearchScreen.this,"Preparing to download.",Toast.LENGTH_SHORT).show();*//**//*

                    }
                });
                */

return message;
    }
}

