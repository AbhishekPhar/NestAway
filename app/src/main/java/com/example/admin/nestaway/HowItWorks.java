package com.example.admin.nestaway;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HowItWorks extends Fragment {
    ViewPager mPager;
    int currentPage = 0;
    Integer[] pic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    ArrayList<Integer> picArray = new ArrayList<Integer>();


    public HowItWorks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_how_it_works, container, false);

        for (int i = 0;i<pic.length;i++)
            picArray.add(pic[i]);
        mPager = view.findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getActivity(),picArray));

        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage==pic.length)
                {
                    currentPage=0;
                }
                mPager.setCurrentItem(currentPage++,true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },3000,3000);

        return view;
    }

}
