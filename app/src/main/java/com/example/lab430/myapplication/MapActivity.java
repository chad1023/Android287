package com.example.lab430.myapplication;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    String [] storeinfo;
    String [] store;
    SupportMapFragment mapFragment;//地圖 fragment
    LatLng[]latlngs;
    int state=0;

    Button changebutton;

    private GoogleMap map;//地圖資訊

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);//取得 SupportFragmentManager 並透過 id 找到地圖的 SupportFragment
        mapFragment.getMapAsync(this);// 同步地圖

        changebutton=(Button)findViewById(R.id.changebutton);


        Resources resources=getResources();//取得 res
        storeinfo=resources.getStringArray(R.array.storeinfos);//透過 res 取得 array.xml 裡的 storeinfos
        latlngs=new LatLng[storeinfo.length];
        store=new String[storeinfo.length];
        for(int i=0;i<storeinfo.length;i++)
        {
            store[i]=storeinfo[i].split(",")[0];
            storeinfo[i]=storeinfo[i].split(",")[1];//split是字串分割的function，參數是用來分割的字元: 1,122,22-> 1 122 22
            (new GeoCodingTask()).execute(i);

        }

        Log.d("Debug","storeinfo");


        changebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                state++;
                if(state>=storeinfo.length)
                {
                    state=0;
                }
                Toast.makeText(MapActivity.this,storeinfo[state],Toast.LENGTH_SHORT).show();
                moveMap(latlngs[state]);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {//getMapAsync 會呼叫的 callback
        map=googleMap;

    }

    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(17)
                        .build();
        // 使用動畫的效果移動地圖
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    //使用 AsynTask 處理透過地址取得經緯度
    public class GeoCodingTask extends AsyncTask<Integer, Void, double[]> {

        int state_address;

        @Override
        protected double[] doInBackground(Integer... params) {//在背景執行的 function
            state_address = params[0];
            Log.d("Debug","doinback");
            String address=storeinfo[state_address];
            double[] latlng = Utils.getLatLngFromGoogleMapAPI(address);//getLatLngFromGoogleMapAPI:透過地址向 server 取得經緯度的 function
            if (latlng != null) {
                Log.d("Debug", String.valueOf(latlng[0]));
                Log.d("Debug", String.valueOf(latlng[1]));

                return latlng;
            }
            return null;
        }

        @Override
        protected void onPostExecute(double[] latlng ) {//取得經緯度後，透過這經緯度設立 Marker
            super.onPostExecute(latlng);
            if (latlng!=null){
                final LatLng place=new LatLng(latlng[0],latlng[1]);
                mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                latlngs[state_address]=new LatLng(latlng[0],latlng[1]);

                map.addMarker(
                        new MarkerOptions()
                                .position(place)
                                .title(store[state_address])
                                .snippet(storeinfo[state_address]));


            }
        }
    }
}
