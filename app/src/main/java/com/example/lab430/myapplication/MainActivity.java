package com.example.lab430.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ordertext;
    EditText editText;
    ImageView image_up;
    Button clickbutton,storebutton,usersettingbutton,loadbutton;

    final static int REQUEST_CODE_DRINKLIST_ACTIVITY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        processView();
        processController();
    }

    //在這個方法中，取得畫面元件物件後指定給變數
    void processView()
    {
        ordertext=(TextView)findViewById(R.id.ordertext);

        clickbutton = (Button)findViewById(R.id.clearbutton);
        storebutton =(Button)findViewById(R.id.storebutton);
        usersettingbutton=(Button)findViewById(R.id.usersetting_button);
        loadbutton=(Button)findViewById(R.id.loadbutton);

    }

    //在這個方法中，宣告或建立需要的監聽物件，並執行所有需要的註冊工作
    void processController()
    {
        clickbutton.setOnClickListener(this);


        storebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,StoreActivity.class);
                startActivity(intent);
            }
        });


        usersettingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,UserSettingActivity.class);
                startActivity(intent);
                finish();
            }
        });





        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=ReadFile();
                ordertext.setText(s);
            }
        });

    }

    //override 的東西分兩種:extends(繼承)的、implements(實作)要求的
    //onclick是onclicklistener 要求要實作的
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,DrinkListActivity.class);
        startActivityForResult(intent,REQUEST_CODE_DRINKLIST_ACTIVITY);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DRINKLIST_ACTIVITY){
            if(resultCode == RESULT_OK){
                String [] drink= data.getStringArrayExtra("drink");
                int [] ordernum = data.getIntArrayExtra("ordernum");
                Log.d("result","OK");
                String orders="";
                for(int i=0;i<drink.length;i++)
                {
                    if(ordernum[i]>0)
                    {
                        orders+=(drink[i]+":"+String.valueOf(ordernum[i])+"\n");
                    }
                }
                ordertext.setText(orders);
                writeFile(orders);
                writetofile(orders);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if(isTaskRoot())
        {
            Dialog();
        }
        else{
            super.onBackPressed();
        }
    }
    public void writeFile(String content)
    {

        String fileName = "test";//檔名
        //IO-Stream需要try-catch結構進行例外處理
        try
        {   //宣告FileOutputStream用openFileOutput開啟檔案準備寫入
            FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
            writer.write(content.getBytes());//write可以寫入byte資料
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    public void writetofile(String content)
    {
        File dir = getExternalFilesDir(null);//外部記憶體私有資料夾位置
        Log.d("tag",dir.getAbsolutePath());


        File outFile = new File(dir, "test.txt");//創建test.txt
        try{
            FileOutputStream writer=new FileOutputStream(outFile,false);
            writer.write(content.getBytes());
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

//    public String readtofile()
//    {
//
//
//    }



    public String ReadFile()
    {

        String fileName = "test";//取得檔名
        String content="";
        byte[] buff = new byte[256]; //input stream buffer

        //IO-Stream需要try-catch結構進行例外處理
        try{                                                 //宣告FileInputStream
            FileInputStream reader = openFileInput(fileName);//用openFileInput開啟檔案準備讀取
            while(reader.read(buff)!=-1)//read會讀取檔案，到檔案末端會回傳-1
            {
                content+=new String(buff).trim();//將每次讀取到的資料加在content後面
                                                 //trim()回傳去除首尾空白字元的string
            }
            reader.close();
        }
        catch(FileNotFoundException e){//檔案不存在
            e.printStackTrace();
        } catch (IOException e){//讀寫錯誤
            e.printStackTrace();
        }
        return content;//回傳讀寫結果content
    }

    //lesson 3 alertdialog
    public void Dialog()//the simple dialog
    {

        new AlertDialog.Builder(this)
                .setTitle("系統資訊")
                .setMessage("真的確定要離開嗎")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();



    }


    public void Dialog2()//使用 list 作爲 dialog
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

    }

    public void Dialog3()//使用自製layout 作為 dialog
    {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);//get the layoutinflater
        final View dialog_view = inflater.inflate(R.layout.alertdialog_use,null);//use inflater.inflate to get the view from layout_id

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
    }




}
