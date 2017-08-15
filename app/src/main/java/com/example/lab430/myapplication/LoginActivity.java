package com.example.lab430.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    final String [] drink = new String[]{"juice","blacktea","greentea"};
    final int [] drinkimg = new int[]{R.drawable.juice,R.drawable.blacktea,R.drawable.greentea};
    final int [] drinkprice = new int[]{15,25,30};
    final int [] heat=new int[]{54,10,10};
    final float [] sugar=new float[]{2.0f,0.5f,0.3f};

    ArrayList<DrinkInfo>drinkmenu=new ArrayList<DrinkInfo>();


    ListView remotelistview;
    DrinkInfoAdapter customadapter;

    Button initbutton,syncbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initbutton=(Button)findViewById(R.id.initbutton);
        syncbutton=(Button)findViewById(R.id.syncbutton);


        remotelistview=(ListView)findViewById(R.id.remotelistview);





        SetupData();
        SetupListview();
        Log.d("sync!",String.valueOf(drinkmenu.size()));

        remotelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrinkInfo tmp =DrinkInfo.newInstance(customadapter.getItem(i));
                drinkmenu.add(tmp);
                Log.d("sync!",String.valueOf(drinkmenu.size()));
                SetupListview();
//                customadapter.notifyDataSetChanged();
            }
        });



        ParseObject testobject=new ParseObject("TestObject");//創建一個ParseObject("TestObject")
        testobject.put("foo","bar");//key,value
        testobject.saveInBackground();//存到資料庫(背景執行)
        testobject.saveEventually();//存到資料庫(離線會等到有網路)



        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Object");//創建一個ParseQuery準備放TestObject
        query.findInBackground(new FindCallback<ParseObject>() {              //用query find資料庫的東西
            @Override                                                         //後面定義FindCallback 在找到東西的時候執行
            public void done(List<ParseObject> objects, ParseException e) {   //done 會在找到東西之後執行
                if(e == null)                                                 //ParseException 處理例外
                {
                    for (ParseObject object : objects)
                    {
                    Toast.makeText(LoginActivity.this, object.getString("foo"), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Log.d("sync",DrinkInfo.class.toString());





        initbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Init!",Toast.LENGTH_SHORT).show();
                initList();

            }
        });

        syncbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synctoDB();
                Log.d("sync!",String.valueOf(drinkmenu.size()));
                Toast.makeText(LoginActivity.this,"sync!",Toast.LENGTH_SHORT).show();
            }
        });






    }

    public void SetupData()
    {
        for (int i=0;i<drink.length;i++)
        {
            DrinkInfo tmp = new DrinkInfo();
            tmp.setImgId(drinkimg[i]);
            tmp.setName(drink[i]);
            tmp.setPrice(drinkprice[i]);
            tmp.setHeat(heat[i]);
            tmp.setSugar(sugar[i]);
            drinkmenu.add(tmp);
        }
    }
    public void SetupListview()
    {
         customadapter=new DrinkInfoAdapter(
                this,
                R.layout.raw_drink,
                drinkmenu
        );
        remotelistview.setAdapter(customadapter);
    }

    public void synctoDB()
    {
        int i=0;
        for (DrinkInfo object:drinkmenu)
        {

            Log.d("sync",object.getName());
            object.saveEventually();


            //上傳到DB，沒有網路的話等待網路
            Log.d("sync",String.valueOf(i));
            i++;
        }

    }

    public void initList()
    {
        drinkmenu.clear();
        DrinkInfo.getQuery().findInBackground(new FindCallback<DrinkInfo>() {
            @Override
            public void done(List<DrinkInfo> objects, ParseException e) {
                if(e==null){
                    for (DrinkInfo object:objects)
                    {
                        drinkmenu.add(object);
                        Log.d("sync","initadd");


                    }


                    SetupListview();
                }
            }
        });


    }


}
