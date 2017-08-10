package com.example.lab430.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinkListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);

        ListView listView = (ListView)findViewById(R.id.listView);

//      Simple ListView
        final String [] drink = new String[]{"juice","blacktea","greentea"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                                              // Activity can be treated as "Context"
                android.R.layout.simple_list_item_1,               //Layout
                drink                                              //data
        );
        listView.setAdapter(adapter);

//      Complex ListView (Use SimpleAdapter)
        List<Map<String, Object>> items = new ArrayList< Map<String,Object> >();

        int [] drinkimg = new int[]{R.drawable.juice,R.drawable.blacktea,R.drawable.greentea};
        int [] drinkprice = new int[]{15,25,30};


        for (int i=0;i<drink.length;i++)
        {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image",drinkimg[i]);
            item.put("name",drink[i]);
            item.put("price",drinkprice[i]);
            items.add(item);
        }

        SimpleAdapter simpleadapter = new SimpleAdapter(
                this,
                items,
                R.layout.raw_drink,
                new String[]{"image","name","price"},
                new int []{R.id.raw_image,R.id.raw_text,R.id.raw_price}
        );

        listView.setAdapter(simpleadapter);

//      Complex ListView (Use CustomAdapter)

        ArrayList<DrinkInfo>drinkmenu=new ArrayList<DrinkInfo>();
        for (int i=0;i<drink.length;i++)
        {
            DrinkInfo tmp = new DrinkInfo();
            tmp.imgId=drinkimg[i];
            tmp.name=drink[i];
            tmp.price=drinkprice[i];
            drinkmenu.add(tmp);
        }

        DrinkInfoAdapter customadapter=new DrinkInfoAdapter(
                this,
                R.layout.raw_drink,
                drinkmenu
        );
        listView.setAdapter(customadapter);

//      setOnClickItemListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("click","click"+drink[i]);
                Toast.makeText(getApplicationContext(),"order:"+drink[i],Toast.LENGTH_SHORT).show();
            }
        });
    }



}
