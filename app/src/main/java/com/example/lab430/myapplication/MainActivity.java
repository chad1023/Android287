package com.example.lab430.myapplication;

//import android.content.DialogInterface;
//import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.TextView;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questext;
    EditText editText;
    ImageView image_up;

    //    RadioGroup radioGroup;
//
//    String drink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
        setContentView(R.layout.layout_test);

        questext = (TextView) findViewById(R.id.questiontext);

        image_up = (ImageView)findViewById(R.id.image01);
//        image_up.setImageDrawable(getDrawable(R.drawable.blackteaicon));
        Picasso.with(image_up.getContext()).load(R.drawable.blackteaicon).into(image_up);


        EditText input=(EditText)findViewById(R.id.input);
        String inputtext = input.getText().toString();
        questext.setText(inputtext);

        Log.d("tag","hello");

        Button clickbutton = (Button)findViewById(R.id.clickbutton);
        clickbutton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        //click3
        questext.setText("hello!!!");
//        image_up.setImageResource(R.drawable.greenteaicon);
        Picasso.with(image_up.getContext()).load(R.drawable.greenteaicon).into(image_up);
        Toast.makeText(this,"hello!!!!",Toast.LENGTH_SHORT).show();
        Dialog3();

    }

    public void Dialog()
    {

        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("message")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


    }

    public void Dialog2()
    {
        final String[] dinner = {"腿庫","雞蛋糕","沙威瑪","澳美客","麵線","麵疙瘩"};

        new AlertDialog.Builder(this)
                .setTitle("Dinner")
                .setItems(dinner, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,dinner[i],Toast.LENGTH_LONG).show();
                    }
                }).show();

//        AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this);
//        dialog_list.setTitle("利用List呈現");
//        dialog_list.setItems(dinner, new DialogInterface.OnClickListener(){
//            @Override
//
//            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
//            public void onClick(DialogInterface dialog, int which) {
//
//                Toast.makeText(MainActivity.this, "你選的是" + dinner[which], Toast.LENGTH_SHORT).show();
//            }
//        });
//        dialog_list.show();
    }

    public void Dialog3()
    {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialog_view = inflater.inflate(R.layout.alertdialog_use,null);

        new AlertDialog.Builder(this)
                .setTitle("請輸入名字")
                .setView(dialog_view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText input=(EditText)(dialog_view.findViewById(R.id.editText1));
                        String s=input.getText().toString();
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    }
                }).show();
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        final View v = inflater.inflate(R.layout.alertdialog_use, null);
//
//
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("請輸入你的id")
//                .setView(v)
//                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        EditText editText = (EditText) (v.findViewById(R.id.editText1));
//                        Toast.makeText(getApplicationContext(), "你的id是" +
//                                editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .show();
    }
//    public void click(View view)
//    {
//        String text = editText.getText().toString();
//        text = text + "  Order: " + drink;
//        textView.setText(text);
//    }



}
