package com.example.lab430.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserSettingActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText nameedit,phoneedit;
    TextView nametext,phonetext;
    Button confirmbutton,backbutton,clearbutton;

    String name;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);


//
        preferences = getSharedPreferences(Application.class.getName(),MODE_PRIVATE);
//        //get the preferences with Applications name as the key
//
        nametext=(TextView)findViewById(R.id.nametext);
        phonetext=(TextView)findViewById(R.id.phonetext);

        nameedit=(EditText)findViewById(R.id.nameedit);
        phoneedit=(EditText)findViewById(R.id.phoneedit);
//
        confirmbutton = (Button)findViewById(R.id.comfirmbutton);
        backbutton = (Button)findViewById(R.id.backbutton);
        clearbutton=(Button)findViewById(R.id.clearbutton);



//
        name=preferences.getString("name",name);
        phone=preferences.getString("phone",phone);


        if(name!=null)
        {
            Log.d("tag",name);
            Log.d("tag",phone);
            showText();
        }
//
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                showText();
            }
        });
//
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showEdit();

            }
        });
//
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(UserSettingActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void saveData()
    {



        name=nameedit.getText().toString();
        phone=phoneedit.getText().toString();


        preferences.edit().putString("name",name).commit();
        preferences.edit().putString("phone",phone).commit();
        Toast.makeText(this,name+phone,Toast.LENGTH_SHORT).show();



    }

    public void showEdit(){

        nametext.setVisibility(View.INVISIBLE);
        nameedit.setVisibility(View.VISIBLE);
        phonetext.setVisibility(View.INVISIBLE);
        phoneedit.setVisibility(View.VISIBLE);
        confirmbutton.setEnabled(true);
    }
    public void showText()
    {

        nametext.setVisibility(View.VISIBLE);
        nameedit.setVisibility(View.INVISIBLE);
        phonetext.setVisibility(View.VISIBLE);
        phoneedit.setVisibility(View.INVISIBLE);
        nametext.setText(name);
        phonetext.setText(phone);
        confirmbutton.setEnabled(false);

    }

}
