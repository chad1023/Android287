package com.example.lab430.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener{

    FragmentManager fragmentManager;
    MenuFragment menufragment;
    AboutFragment aboutfragment;
    StoreFragment storefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
     Log.d("Fragment_Activity","create");


        fragmentManager=getFragmentManager();
        menufragment=new MenuFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment,menufragment,"menufragment_tag")
                .commit();
        Button aboutbutton=(Button)findViewById(R.id.aboutbutton);
        Button storebutton=(Button)findViewById(R.id.storebutton);
        Button menubutton=(Button)findViewById(R.id.menubutton);
        aboutbutton.setOnClickListener(this);
        storebutton.setOnClickListener(this);
        menubutton.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Fragment_Activity","start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Fragment_Activity","resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Fragment_Activity","pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Fragment_Activity","stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Fragment_Activity","destroy");
    }

    @Override
    public void onClick(View view) {
//        DetailFragment detailfragment=(DetailFragment)fragmentManager.findFragmentByTag("DetailFragment");
        FragmentTransaction ft=fragmentManager.beginTransaction();
//        if (detailfragment!=null)
//        {
//            ft.remove(detailfragment);
//        }
        Fragment fragment=new Fragment();
        switch (view.getId())
        {
            case R.id.aboutbutton:
                fragment=new AboutFragment();
                break;
            case R.id.menubutton:
                fragment=new MenuFragment();
                break;
            case R.id.storebutton:
                fragment=new StoreFragment();
                break;
        }
        ft.replace(R.id.fragment,fragment);
//        ft.addToBackStack(null);
        ft.commit();

    }
    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getFragmentManager();

        if (fm.getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            fm.popBackStack();
        }
    }
}
