package com.example.lab430.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinkListActivity extends AppCompatActivity{

    ListView listView;
    TextView OrderText;
    Button clearbutton;


    final int [] ordernum = new int[3];
    final String [] drink = new String[]{"juice","blacktea","greentea"};//Simple ListView 的 data

    //Complex ListView 所需的其他資料
    final int [] drinkimg = new int[]{R.drawable.juice,R.drawable.blacktea,R.drawable.greentea};
    final int [] drinkprice = new int[]{15,25,30};
    final int [] heat=new int[]{54,10,10};
    final float [] sugar=new float[]{2.0f,0.5f,0.3f};

    List<Map<String, Object>> items;//Complex ListView (Use SimpleAdapter) 的 data

    ArrayList<DrinkInfo>drinkmenu=new ArrayList<DrinkInfo>();//Complex ListView (Use CustomAdapter) 的data

    DrinkInfoAdapter customadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);

        Setupdata();//準備 ListView 需要的資料
        processView();

        //利用 ordernum 計算 list 中每個 item 被點擊的次數作為點單
        for(int a :ordernum)
        {
            a=0;
        }


        processController();




    }

    public void processView()
    {
        listView = (ListView)findViewById(R.id.listView);
        OrderText = (TextView)findViewById(R.id.OrderList);
        clearbutton=(Button)findViewById(R.id.clearbutton);

    }

    void processController()
    {

        SetupListView();

        //      setOn"ItemClick"ItemListener:設定listview的item點擊後的反應
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("click","click"+drink[i]);
                ordernum[i]++;
                OrderText.setText("");
                for(int j =0;j<drink.length;j++)
                {
                    String t =OrderText.getText().toString()+"\n";
                    OrderText.setText(t+drink[j]+":"+ordernum[j]+" ");
                }

            }
        });

//        setOn"ItemLongClick"Listener:設定listview的item長點擊後的反應
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(),"order:"+drink[pos],Toast.LENGTH_SHORT).show();
                DrinkInfo drinkinfo=  customadapter.getItem(pos);
                StartDetail(drinkinfo);
                return true;
            }
        });


        //設定點擊submit button後的反應:回傳點單結果給MainActivity
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("ordernum",ordernum);
                intent.putExtra("drink",drink);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    void Setupdata()
    {
        //Simple ListView 的 data 是 String 陣列 drink

        //Complex ListView (Use SimpleAdapter) data : map<String,Object>組成的 arraylist
        items= new ArrayList< Map<String,Object> >();

        for (int i=0;i<drink.length;i++)
        {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image",drinkimg[i]);
            item.put("name",drink[i]);
            item.put("price",drinkprice[i]);
            items.add(item);
        }

        //Complex ListView (Use CustomAdapter) 的data : <DrinkInfo>組成的 arraylist


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

    void SetupListView(){//包括宣告好 adapter 以及 setadapter

        //Simple ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                                              // Activity can be treated as "Context"
                android.R.layout.simple_list_item_1,               //Layout
                drink                                              //data
        );
        listView.setAdapter(adapter);


        //Complex ListView (Use SimpleAdapter)                  //因為都用同一個ListView 所以前面的會被取代
        SimpleAdapter simpleadapter = new SimpleAdapter(
                this,
                items,
                R.layout.raw_drink,
                new String[]{"image","name","price"},
                new int []{R.id.raw_image,R.id.raw_text,R.id.raw_price}
        );

        listView.setAdapter(simpleadapter);


        //Complex ListView (Use CustomAdapter：DrinkInfoAdapter)
        customadapter=new DrinkInfoAdapter(
                this,
                R.layout.raw_drink,
                drinkmenu
        );
        listView.setAdapter(customadapter);
    }



    public void StartDetail(DrinkInfo drinkinfo)//呼叫 DrinkDetail 顯示飲料詳細資訊
    {
        Intent detailintent=new Intent();
        detailintent.putExtra("drinkinfo",drinkinfo);
        detailintent.setClass(this,DrinkDetailActivity.class);
        startActivity(detailintent);
    }


}
