package com.example.lab430.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AnimateActivity extends AppCompatActivity {

    ListView listview;
    Button alphabutton,rotationbutton,translationxbutton,backgroundbutton;
    TextView text;
    Handler uihandler;
    int i=0;

    String [] drink =new String[]{"Juice","Tea","Water"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        processView();
        processController();

        uihandler=new Handler(getMainLooper());
        uihandler.postDelayed(runnable,5000);

    }
    final Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.d("Debug","hello");
            i++;
            text.setText("count:"+String.valueOf(i));
            uihandler.postDelayed(runnable,1000);


        }
    };


    void processView()
    {
        listview=(ListView)findViewById(R.id.listview);
        alphabutton=(Button)findViewById(R.id.alpha);
        rotationbutton=(Button)findViewById(R.id.rotation);
        translationxbutton=(Button)findViewById(R.id.translationx);
        backgroundbutton=(Button)findViewById(R.id.background);

        text=(TextView)findViewById(R.id.text);
    }
    void processController()
    {
        SetupListview();
        alphabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alphaAnimator();
            }
        });
        rotationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotationAnimator();
            }
        });
        translationxbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translationxAnimator();
            }
        });
        backgroundbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAnimator();
            }
        });

    }
    void SetupListview()
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
        this,
        android.R.layout.simple_list_item_1,
        drink
        );
        listview.setAdapter(adapter);
    }

    void alphaAnimator()
    {
        ObjectAnimator alphaanimator= ObjectAnimator.ofFloat(listview,"alpha",1.0f,0.0f);
        alphaanimator.setRepeatMode(ValueAnimator.REVERSE);
        alphaanimator.setRepeatCount(3);
        alphaanimator.setDuration(1000);
        alphaanimator.start();
    }

    void rotationAnimator()
    {
        ObjectAnimator rotationanimator=ObjectAnimator.ofFloat(listview,"rotationY",0,180,360);
        rotationanimator.setDuration(2000);
        rotationanimator.start();
    }

    void translationxAnimator()
    {
        ObjectAnimator tranloationanimator=ObjectAnimator.ofFloat(listview,"translationY",0,200,0,-200,0);
        tranloationanimator.setRepeatCount(3);
        tranloationanimator.setDuration(1000);
        tranloationanimator.start();
    }
    void backgroundAnimator()
    {
        ObjectAnimator backgroundanimator = ObjectAnimator.ofArgb(listview,"BackgroundColor",0xFFFFFFFF,0xFF000000,0xFFFFFFFF);//color(ARGB)
        backgroundanimator.setRepeatCount(3);
        backgroundanimator.setDuration(2000);
        backgroundanimator.start();

        ObjectAnimator textanimator = ObjectAnimator.ofArgb(text,"textColor",0xFF000000,0xFF0000FF,0xFFFF00FF);
        textanimator.setRepeatCount(3);
        textanimator.setDuration(2000);
        textanimator.start();
    }

}
