package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText text= findViewById(R.id.editText);
        Button write = findViewById(R.id.button1);
        Button read = findViewById(R.id.button2);
        Button clear = findViewById(R.id.button3);

        write.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               String data = text.getText().toString();

               try {
                   File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                   String FILENAME = "user_details.txt";

                   File file = new File(folder, FILENAME);
                   file.createNewFile();

                   //get a file streamer
                   //pass file and append =true means new data will be appended
                   FileOutputStream fout = new FileOutputStream(file, true);

                   //write the data
                   fout.write(data.getBytes());

                   //close the file
                   fout.close();
                   //make notification
                   Toast.makeText(MainActivity.this, "DATA IS ADDED"+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
               }
               catch(Exception e){
                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
               }
           }
        });

        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                String data, buff = "";
                try{
//                    //Open the file
//                    File file = new File("/sdcard/mayfile.txt");
//
//                    //Open Input Streamer
//                    FileInputStream fin = new FileInputStream(file);
//
//                    //Open Stream reader
//                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
//
//                    while((data = br.readLine()) != null){
//                        buff += data;
//                    }
//
//                    //add the text to display
//
//                    text.setText(buff);
//
//                    //close the readers
//                    br.close();
//                    fin.close();

                    String FILENAME = "user_details";
                    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file = new File(folder, FILENAME);
                    FileInputStream fstream = new FileInputStream(file);
                    StringBuffer sbuffer = new StringBuffer();
                    int i;
                    while ((i = fstream.read())!= -1){
                        sbuffer.append((char)i);
                    }
                    fstream.close();

                    //make notification
                    Toast.makeText(MainActivity.this,"DATA IS READ"+file.getAbsolutePath(), Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                text.setText(" ");
            }
        });
    }
}