package com.example.lab430.myapplication;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    private static MenuFragment instance;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<DrinkInfo> drinkmenu=new ArrayList<DrinkInfo>();

    final int [] drinkimg = new int[]{R.drawable.juice,R.drawable.blacktea,R.drawable.greentea};
    final int [] drinkprice = new int[]{15,25,30};
    final int [] heat=new int[]{54,10,10};
    final float [] sugar=new float[]{2.0f,0.5f,0.3f};
    String [] drink = new String[]{"juice","blacktea","greentea"};




    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        if (instance == null) {
            instance = new MenuFragment();
        }
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("Fragment","MenuFragment Attach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        Log.d("Fragment","MenuFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment","MenuFragment onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView listView=(ListView)view.findViewById(R.id.menulistview);
        final DrinkInfoAdapter drinkInfoAdapter=new DrinkInfoAdapter(
                getActivity(),
                R.layout.raw_drink,
                drinkmenu
        );
        listView.setAdapter(drinkInfoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FragmentManager fragmentManager=getFragmentManager();
                DrinkInfo tmp=drinkInfoAdapter.getItem(i);
                DetailFragment detailfragment=DetailFragment.newInstance(tmp);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment,detailfragment,"DetailFragment")
//                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("Fragment","MenuFragment onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment","MenuFragment onStart");

        
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment","MenuFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment","MenuFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment","MenuFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment","MenuFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment","MenuFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment","MenuFragment onDetach");
    }
}
