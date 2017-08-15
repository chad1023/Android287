package com.example.lab430.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    EditText edit;
    TextView textview;
    Button savebutton, loadbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        edit = (EditText) findViewById(R.id.edit);
        textview = (TextView) findViewById(R.id.display);

        savebutton = (Button) findViewById(R.id.savebutton);
        loadbutton = (Button) findViewById(R.id.loadbutton);

        final String filename = "test";
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = edit.getText().toString();
                writefile(filename, content);
            }
        });

        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = readfile(filename);
                textview.setText(content);
            }
        });
    }


    class clicklistenter implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            view.getId();

            //click
        }
    }


    public void writefile(String filename, String content) {
        FileOutputStream writer = null;
        try {
            writer = openFileOutput(filename, MODE_PRIVATE);
            writer.write(content.getBytes());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readfile(String filename) {
        String content = "";

        byte [] buffer=new byte[1024];

        FileInputStream reader;

        try {
            reader=openFileInput(filename);
            while(reader.read(buffer)!=-1)
            {
                content+=new String(buffer).trim();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return content;
    }

}
