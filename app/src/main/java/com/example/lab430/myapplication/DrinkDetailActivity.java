package com.example.lab430.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkDetailActivity extends AppCompatActivity {
    TextView name;
    TextView calories;
    TextView sugar;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        name=(TextView)findViewById(R.id.drinkname);
        calories=(TextView)findViewById(R.id.caloriestext);
        sugar=(TextView)findViewById(R.id.sugartext);
        image=(ImageView)findViewById(R.id.drinkimage);

        Intent intent=getIntent();
        DrinkInfo drinkInfo=intent.getParcelableExtra("drinkinfo");
        
        name.setText(drinkInfo.getName());
        calories.setText("Calories:"+ String.valueOf(drinkInfo.getHeat()));
        sugar.setText("Carbohydrate:"+ String.valueOf(drinkInfo.getSugar()));

        image.setImageResource(drinkInfo.getImgId());


    }
}
