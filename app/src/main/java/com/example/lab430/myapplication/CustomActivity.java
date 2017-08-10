package com.example.lab430.myapplication;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lab430 on 2017/8/10.
 */

public class CustomActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        if(isTaskRoot())
        {
            moveTaskToBack(true);
        }
        else{
            super.onBackPressed();
        }
    }
}
