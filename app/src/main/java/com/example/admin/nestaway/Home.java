package com.example.admin.nestaway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/aaglagaduga.appspot.com/o/download%20(1).jpg?alt=media&token=4a3ad773-3a8d-4cc0-afba-44406ac7fd06");
        mNames.add("Banglore");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Delhi");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Gurgaon");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Noida");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Ghaziabad");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Faridabad");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Greater Noida");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Hyderabad");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Pune");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Mumbai");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Navi Mumbai");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Thane");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);

        return v;
    }
}