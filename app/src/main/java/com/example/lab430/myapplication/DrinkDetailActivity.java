package com.example.lab430.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        
        name.setText(drinkInfo.name);
        calories.setText("Calories:"+ String.valueOf(drinkInfo.heat));
        sugar.setText("Carbohydrate:"+ String.valueOf(drinkInfo.sugar));

        image.setImageResource(drinkInfo.imgId);


    }
}
