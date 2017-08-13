package com.example.lab430.myapplication;

import android.app.FragmentManager;
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
    ArrayList<DrinkInfo>drinkmenu=new ArrayList<DrinkInfo>();

    final int [] ordernum = new int[3];
    final String [] drink = new String[]{"juice","blacktea","greentea"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);


        listView = (ListView)findViewById(R.id.listView);
        OrderText = (TextView)findViewById(R.id.OrderList);

//      Simple ListView

        for(int a :ordernum)
        {
            a=0;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                                              // Activity can be treated as "Context"
                android.R.layout.simple_list_item_1,               //Layout
                drink                                              //data
        );
        listView.setAdapter(adapter);

//      Complex ListView (Use SimpleAdapter)
        List<Map<String, Object>> items = new ArrayList< Map<String,Object> >();

        final int [] drinkimg = new int[]{R.drawable.juice,R.drawable.blacktea,R.drawable.greentea};
        final int [] drinkprice = new int[]{15,25,30};
        final int [] heat=new int[]{54,10,10};
        final float [] sugar=new float[]{2.0f,0.5f,0.3f};

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


        for (int i=0;i<drink.length;i++)
        {
            DrinkInfo tmp = new DrinkInfo();
            tmp.imgId=drinkimg[i];
            tmp.name=drink[i];
            tmp.price=drinkprice[i];
            tmp.heat=heat[i];
            tmp.sugar=sugar[i];
            drinkmenu.add(tmp);
        }

        final DrinkInfoAdapter customadapter=new DrinkInfoAdapter(
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
                ordernum[i]++;
                OrderText.setText("");
                for(int j =0;j<drink.length;j++)
                {
                    String t =OrderText.getText().toString()+"\n";
                    OrderText.setText(t+drink[j]+":"+ordernum[j]+" ");
                }

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(),"order:"+drink[pos],Toast.LENGTH_SHORT).show();
                DrinkInfo drinkinfo=customadapter.getItem(pos);
                StartDetail(drinkinfo);
                return true;
            }
        });


        Button b = (Button)findViewById(R.id.clickbutton);
        b.setOnClickListener(new View.OnClickListener() {
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


    public void StartDetail(DrinkInfo drinkinfo)
    {
        Intent detailintent=new Intent();
        detailintent.putExtra("drinkinfo",drinkinfo);
        detailintent.setClass(this,DrinkDetailActivity.class);
        startActivity(detailintent);
    }


}
