package com.example.emp354.linear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorage extends AppCompatActivity implements View.OnClickListener {

    EditText edittext_file_name,edittext_directory_name,edittext_text;
    Button button_create_file,button_delete_file,button_create_directory,button_delete_directory;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_storage);

        edittext_directory_name=findViewById(R.id.edittext_directory_name);
        edittext_file_name=findViewById(R.id.edittext_file_name);
        edittext_text=findViewById(R.id.edittext_name);

        button_create_file=findViewById(R.id.button_create_file);
        button_create_directory=findViewById(R.id.button_create_directory);
        button_delete_file=findViewById(R.id.button_delete_file);
        button_delete_directory=findViewById(R.id.button_delete_directory);

        button_create_directory.setOnClickListener(this);
        button_delete_directory.setOnClickListener(this);
        button_create_file.setOnClickListener(this);
        button_delete_file.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        String dirname=edittext_directory_name.getText().toString();
        File dir=new File(FileStorage.this.getFilesDir(),dirname);

        switch (v.getId())
        {
            case R.id.button_create_directory:

                if(!dir.exists() && !dir.isDirectory())
                {
                    if(dir.mkdirs())
                    {
                        Toast.makeText(FileStorage.this,"Directory created.",Toast.LENGTH_SHORT).show();
                        }
                    else
                    {
                        Toast.makeText(FileStorage.this,"Problem in creating directory.",Toast.LENGTH_SHORT).show();
                        }
                }
                else
                {
                    Toast.makeText(FileStorage.this,"Directory already exists.",Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.button_delete_directory:
                if(dir.exists() && dir.isDirectory())
                {
                   if (dir.listFiles().length==0) {
                       dir.delete();

                       Toast.makeText(FileStorage.this, "Directory deleted.", Toast.LENGTH_SHORT).show();

                   }
                   else
                   {
                       Toast.makeText(FileStorage.this,"Directory is not empty.",Toast.LENGTH_SHORT).show();
                   }
                }
                else
                {
                    Toast.makeText(FileStorage.this,"Directory is not present.",Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.button_create_file:
                try {
                        /*if (dir.mkdirs()) {*/
                           /* Toast.makeText(FileStorage.this, "Directory created.", Toast.LENGTH_SHORT).show();*/
                            String filename = edittext_file_name.getText().toString();

                            file = new File(dir + "/" + filename);
                             int i=1;
                            while (file.exists()) {

                                Toast.makeText(FileStorage.this, "File with same name is already present.", Toast.LENGTH_SHORT).show();
                                String filename_1 =i+"_" +filename;
                                i++;
                                file = new File(dir + "/" + filename_1);
                            }

                            Toast.makeText(FileStorage.this, "File is created.", Toast.LENGTH_SHORT).show();
                            file.createNewFile();

                            String data = edittext_text.getText().toString();
                            if (data.length() != 0) {

                                FileOutputStream file_data;
                                file_data = openFileOutput(filename, MODE_PRIVATE);
                                file_data.write(data.getBytes());
                                file_data.close();
                                Toast.makeText(FileStorage.this, "Data is written in file.", Toast.LENGTH_SHORT).show();
                            }



                        /*else {
                            Toast.makeText(FileStorage.this, "Problem in creating directory.", Toast.LENGTH_SHORT).show();
                        }*/

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;


            case R.id.button_delete_file:

                String filename = edittext_file_name.getText().toString();
                File file = new File(dir + "/" + filename);

                    if (file.exists() && file.length()==0) {
                        file.delete();
                        Toast.makeText(FileStorage.this, "File deleted.", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(FileStorage.this, "Either file not exist or not empty.", Toast.LENGTH_SHORT).show();
                    }
                break;


        }



    }
}
